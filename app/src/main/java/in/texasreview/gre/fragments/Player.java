package in.texasreview.gre.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.CreateQusBodyModel;
import in.texasreview.gre.Models.CreateQusResModel;
import in.texasreview.gre.Models.CreatedQuestionsModel;
import in.texasreview.gre.Models.LikesBodyModel;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.R;
import in.texasreview.gre.activities.MyYouTubePlayer;
import in.texasreview.gre.adapters.CreateQusAdapter;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.LikeOrUnlikeOnclick;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AppConstants;
import in.texasreview.gre.utils.QusLikesSorter;
import in.texasreview.gre.utils.MyProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Player extends Fragment implements LikeOrUnlikeOnclick {


    private String videoid; //for videos
    private String subcatid; //for questions
    private String catid;  //for questions

    private YouTubePlayer YPlayer;

    @BindView(R.id.youtubethumbnail)
    YouTubeThumbnailView youtubethumbnail;

    @BindView(R.id.imvBack)
    ImageView imvBack;

    @BindView(R.id.btnCreateNewQuestion)
    Button btnCreateNewQuestion;

    @BindView(R.id.rvCreateQustions)
    RecyclerView rvCreateQustions;

    @BindView(R.id.tvTotalNumOfQus)
    TextView tvTotalNumOfQus;

    EditText etQustion;
    AlertDialog alertDialogAndroid;

    @BindView(R.id.pbthumbnail)
    ProgressBar pbthumbnail;

    MyProgressBar myProgressBar;
    SessionManager sessionManager;


    @BindView(R.id.imvPlay)
    ImageView imvPlay;

    ArrayList<CreatedQuestionsModel.QuestionsBean> questionsBeans = new ArrayList<>();

    public Player() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            videoid = getArguments().getString(AppConstants.VIDEOURLKEY);
            videoid = getYoutubeVideoIdFromUrl(videoid);
            subcatid = getArguments().getString(AppConstants.SUBCATIDKEY);
            catid = getArguments().getString(AppConstants.CATIDKEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, view);
        pbthumbnail.setVisibility(View.VISIBLE);


        youtubethumbnail.initialize(AppConstants.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
                                                final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoid);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {

                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //need to release the loader!!!
                        youTubeThumbnailLoader.release();
                        pbthumbnail.setVisibility(View.GONE);
                        imvPlay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView,
                                                 YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //need to release the loader!!!
                        youTubeThumbnailLoader.release();
                        Log.e("InitializationResult", errorReason.name());
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView,
                                                YouTubeInitializationResult youTubeInitializationResult) {

                Log.e("InitializationResult", youTubeInitializationResult.name());
                //handle error here
            }
        });

        // for questions
        myProgressBar = new MyProgressBar(getContext());
        myProgressBar.show();

        sessionManager = new SessionManager(getContext());
        getCreatedQuestionsList(catid, sessionManager.getUSER_Id(), subcatid);

        btnCreateNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCreateNewQuestionPopup();
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();

            }
        });

        imvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyYouTubePlayer.class);
                intent.putExtra(AppConstants.VIDEOKEY, videoid);
                startActivity(intent);

            }
        });


        return view;
    }

    public static String getYoutubeVideoIdFromUrl(String inUrl) {
        if (inUrl.toLowerCase().contains("youtu.be")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1);
        }
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(inUrl);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    private void showCreateNewQuestionPopup() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.create_question_popup, null);
        TextView tvDialogTitle = mView.findViewById(R.id.tvDialogTitle);
        TextView tvQusOrAns = mView.findViewById(R.id.tvQusOrAns);
        tvDialogTitle.setText(getString(R.string.Create_New_Question));
        tvQusOrAns.setText(getString(R.string.Question));
        etQustion = mView.findViewById(R.id.etQustion);
        etQustion.setHint(getString(R.string.Enter_your_qus));
        etQustion = mView.findViewById(R.id.etQustion);
        Button btnpost = mView.findViewById(R.id.btnPost);
        Button btnCancel = mView.findViewById(R.id.btnCancel);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);
        alertDialogAndroid = alertDialogBuilderUserInput.create();
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etQustion.getText().toString().length() < 4) {

                    displayToast("please enter question correctly");

                } else {
                    alertDialogAndroid.hide();
                    SessionManager sessionManager = new SessionManager(getContext());
                    postQus(subcatid, sessionManager.getUSER_Id(), etQustion.getText().toString());
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

    private void postQus(String subcatId, String userid, String question) {

        final CreateQusBodyModel createQusBodyModel = new CreateQusBodyModel();
        createQusBodyModel.setSubcatid(subcatId);
        createQusBodyModel.setUserid(userid);
        createQusBodyModel.setQuestion(question);

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<CreateQusResModel>> call = service.createQustion(createQusBodyModel);
        call.enqueue(new Callback<List<CreateQusResModel>>() {
            @Override
            public void onResponse(Call<List<CreateQusResModel>> call, Response<List<CreateQusResModel>> response) {

                List<CreateQusResModel> createQusResModel = response.body();
                if (response.code() == 200 && createQusResModel.get(0).getResponse().equalsIgnoreCase("success")) {


                    displayToast("Qustion Created Successfully");
                    getCreatedQuestionsList(catid, sessionManager.getUSER_Id(), subcatid);

                } else

                {
                    displayToast("error");

                }
            }

            @Override
            public void onFailure(Call<List<CreateQusResModel>> call, Throwable t) {


                displayToast("Something went wrong...Please try later!");
            }
        });
    }

    private void getCreatedQuestionsList(String catid, String UserId, String id) {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<CreatedQuestionsModel> call = service.getQuestionsList(catid, UserId, id);
        call.enqueue(new Callback<CreatedQuestionsModel>() {
            @Override
            public void onResponse(Call<CreatedQuestionsModel> call, Response<CreatedQuestionsModel> response) {

                myProgressBar.hide();
                CreatedQuestionsModel createdQuestionsModel = response.body();
                questionsBeans = createdQuestionsModel.getQuestions();
                tvTotalNumOfQus.setText("" + getString(R.string.Total_Questions) + String.valueOf(createdQuestionsModel.getQuestions().size()));
                rvCreateQustions.setHasFixedSize(true);
                rvCreateQustions.setLayoutManager(new LinearLayoutManager(getContext()));//Linear Items
                QusLikesSorter likesSorter = new QusLikesSorter(createdQuestionsModel.getQuestions());//this line for sorting
                CreateQusAdapter adapter = new CreateQusAdapter(getContext(), likesSorter.getSortedByLike(), Player.this);
                rvCreateQustions.setAdapter(adapter);
                rvCreateQustions.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<CreatedQuestionsModel> call, Throwable t) {

                myProgressBar.hide();
                displayToast("Something went wrong...Please try later!");

            }
        });

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

        final LikesBodyModel likesBodyModel = new LikesBodyModel();
        likesBodyModel.setQue(questionsBeans.get(position).getId());
        likesBodyModel.setUserid(questionsBeans.get(position).getUserid());
        likesBodyModel.setLikes(String.valueOf(like));
        likesBodyModel.setUnlikes(String.valueOf(unline));

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<LikesResModel>> call = service.likeOrUnlike(likesBodyModel);
        call.enqueue(new Callback<List<LikesResModel>>() {
            @Override
            public void onResponse(Call<List<LikesResModel>> call, Response<List<LikesResModel>> response) {

                List<LikesResModel> likesResModels = response.body();
                if (response.code() == 200 && likesResModels.get(0).getResponse().equalsIgnoreCase("success")) {


                    displayToast("Successfully");
                    getCreatedQuestionsList(catid, sessionManager.getUSER_Id(), subcatid);

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

        Toast.makeText(getActivity(), "" + value, Toast.LENGTH_SHORT).show();

    }
}
