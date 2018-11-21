package in.texasreview.gre.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.AnsLikeBodyModel;
import in.texasreview.gre.Models.AnswersModel;
import in.texasreview.gre.Models.CreateAnsBodyModel;
import in.texasreview.gre.Models.CreateAnsResModel;
import in.texasreview.gre.Models.CreateQusBodyModel;
import in.texasreview.gre.Models.CreateQusResModel;
import in.texasreview.gre.Models.CreatedQuestionsModel;
import in.texasreview.gre.Models.LikesBodyModel;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.R;
import in.texasreview.gre.adapters.AnswersAdapter;
import in.texasreview.gre.adapters.CreateQusAdapter;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.LikeOrUnlikeOnclick;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AnsLikeSorter;
import in.texasreview.gre.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Answers extends AppCompatActivity implements LikeOrUnlikeOnclick {

    @BindView(R.id.rvAnswres)
    RecyclerView rvAnswres;

    @BindView(R.id.imvBack)
    ImageView imvBack;

    @BindView(R.id.tvqusCount)
    TextView tvqusCount;

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.tvNumOfLikes)
    TextView tvNumOfLikes;

    @BindView(R.id.tvNumOfDisLikes)
    TextView tvNumOfDisLikes;

    @BindView(R.id.tvMailid)
    TextView tvMailid;

    @BindView(R.id.tvTime)
    TextView tvTime;

    @BindView(R.id.tvNumOfAns)
    TextView tvNumOfAns;

    @BindView(R.id.btnCreateAns)
    Button btnCreateAns;

    Context context;

    String qusid;

    AlertDialog alertDialogAndroid;
    EditText etQustion;

    ArrayList<AnswersModel.AnsBean> ansBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        ButterKnife.bind(this);
        context = Answers.this;
        qusid = getIntent().getStringExtra(AppConstants.QUSTIONIDKEY);
        if (qusid != null) {
            getAnsList(qusid);
        }

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnCreateAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCreateNewAnsPopup();

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void getAnsList(String qusId) {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<AnswersModel> call = service.getVideoAnsByQusId(qusId);
        call.enqueue(new Callback<AnswersModel>() {
            @Override
            public void onResponse(Call<AnswersModel> call, Response<AnswersModel> response) {

                AnswersModel answersModel = response.body();
                ansBeans = answersModel.getAns();
                bindata(answersModel);
                rvAnswres.setHasFixedSize(true);
                rvAnswres.setLayoutManager(new LinearLayoutManager(Answers.this));
                AnsLikeSorter ansLikeSorter = new AnsLikeSorter(answersModel.getAns());//this line for sorting
                AnswersAdapter adapter = new AnswersAdapter(Answers.this, ansLikeSorter.getSortedByLike(), Answers.this);
                rvAnswres.setAdapter(adapter);
                rvAnswres.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<AnswersModel> call, Throwable t) {

                displayToast("Something went wrong...Please try later!");
            }
        });

    }

    private void showCreateNewAnsPopup() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.create_question_popup, null);
        TextView tvDialogTitle = mView.findViewById(R.id.tvDialogTitle);
        TextView tvQusOrAns = mView.findViewById(R.id.tvQusOrAns);
        tvDialogTitle.setText(getString(R.string.Create_New_Answer));
        tvQusOrAns.setText(getString(R.string.Answer));
        etQustion = mView.findViewById(R.id.etQustion);
        etQustion.setHint(getString(R.string.Enter_your_ans));
        Button btnpost = mView.findViewById(R.id.btnPost);
        Button btnCancel = mView.findViewById(R.id.btnCancel);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(mView);
        alertDialogAndroid = alertDialogBuilderUserInput.create();
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etQustion.getText().toString().length() < 4) {

                    displayToast("please enter question correctly");

                } else {
                    alertDialogAndroid.hide();
                    SessionManager sessionManager = new SessionManager(context);
                    postQus(qusid, sessionManager.getUSER_Id(), etQustion.getText().toString());
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogAndroid.hide();
            }
        });

        alertDialogAndroid.show();
    }

    private void postQus(String qusId, String userid, String answer) {

        final CreateAnsBodyModel createAnsBodyModel = new CreateAnsBodyModel();
        createAnsBodyModel.setQueid(qusId);
        createAnsBodyModel.setUserid(userid);
        createAnsBodyModel.setAns(answer);

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<CreateAnsResModel>> call = service.createAnswer(createAnsBodyModel);
        call.enqueue(new Callback<List<CreateAnsResModel>>() {
            @Override
            public void onResponse(Call<List<CreateAnsResModel>> call, Response<List<CreateAnsResModel>> response) {

                List<CreateAnsResModel> createAnsResModel = response.body();
                if (response.code() == 200 && createAnsResModel.get(0).getResponse().equalsIgnoreCase("success")) {

                    displayToast("Answer Created Successfully");
                    getAnsList(qusid);


                } else

                {

                    displayToast("error");
                }
            }

            @Override
            public void onFailure(Call<List<CreateAnsResModel>> call, Throwable t) {

                displayToast("Something went wrong...Please try later!");
            }
        });
    }

    private void bindata(AnswersModel answersModel) {

        tvQuestion.setText(answersModel.getQuetion());
        tvNumOfLikes.setText(String.valueOf(answersModel.getLike()));
        tvNumOfDisLikes.setText(String.valueOf(answersModel.getUnlike()));
        tvMailid.setText(answersModel.getUserdata().getFullname());
        tvTime.setText(answersModel.getQdate());
        tvNumOfAns.setText(answersModel.getAns().size() + "Answers");


    }

    @Override
    public void onLikeClicked(int position) {

        likeOrUnlike(position, 1, 0);

    }

    @Override
    public void onUnlikeClicked(int position) {

        likeOrUnlike(position, 0, 1);

    }

    private void likeOrUnlike(int position, int like, int unline) {

        final AnsLikeBodyModel ansLikeBodyModel = new AnsLikeBodyModel();
        ansLikeBodyModel.setAns(ansBeans.get(position).getId());
        ansLikeBodyModel.setUserid(ansBeans.get(position).getAuser());
        ansLikeBodyModel.setLikes(String.valueOf(like));
        ansLikeBodyModel.setUnlikes(String.valueOf(unline));

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<LikesResModel>> call = service.ansLikeOrUnlike(ansLikeBodyModel);
        call.enqueue(new Callback<List<LikesResModel>>() {
            @Override
            public void onResponse(Call<List<LikesResModel>> call, Response<List<LikesResModel>> response) {

                List<LikesResModel> likesResModels = response.body();
                if (response.code() == 200 && likesResModels.get(0).getResponse().equalsIgnoreCase("success")) {


                    displayToast("Successfully");
                    getAnsList(qusid);

                } else

                {
                    displayToast("error");

                }
            }

            @Override
            public void onFailure(Call<List<LikesResModel>> call, Throwable t) {

                displayToast("Something went wrong...Please try later!");
            }
        });
    }

    private void displayToast(String value) {

        Toast.makeText(this, "" + value, Toast.LENGTH_SHORT).show();

    }


}
