package in.texasreview.gre.fragments;

import android.app.AlertDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.FeedBackBodyModel;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.R;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class FeedBackForm extends Fragment implements View.OnClickListener {

    @BindView(R.id.etcomments)
    EditText etcomments;

    //@BindView(R.id.scrollview)
    //ScrollView scrollview;

    @BindView(R.id.ll)
    LinearLayout ll;

    @BindView(R.id.llUnsatisfied)
    LinearLayout llUnsatisfied;

    @BindView(R.id.llAverage)
    LinearLayout llAverage;

    @BindView(R.id.llSatisfied)
    LinearLayout llSatisfied;

    @BindView(R.id.btnSuggetions)
    Button btnSuggetions;

    @BindView(R.id.btnCompliments)
    Button btnCompliments;

    @BindView(R.id.btnNotRignt)
    Button btnNotRignt;

    @BindView(R.id.tvUnsatisfied)
    TextView tvUnsatisfied;

    @BindView(R.id.tvAverage)
    TextView tvAverage;

    @BindView(R.id.tvSatisfied)
    TextView tvSatisfied;

    @BindView(R.id.imvUnsatisfied)
    ImageView imvUnsatisfied;

    @BindView(R.id.imvAverage)
    ImageView imvAverage;

    @BindView(R.id.imvSatisfied)
    ImageView imvSatisfied;

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.tvRemaingCount)
    TextView tvRemaingCount;

    SessionManager sessionManager;

    FeedBackBodyModel feedBackBodyModel;

    AlertDialog alertDialog;

    @BindView(R.id.llsmiles)
    LinearLayout llsmiles;

    @BindView(R.id.llbuttons)
    LinearLayout llbuttons;

    public FeedBackForm() {
        // Required empty public constructor
    }

    public static FeedBackForm newInstance() {
        return new FeedBackForm();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getContext());
        feedBackBodyModel = new FeedBackBodyModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back_form, container, false);
        ButterKnife.bind(this, view);
        setOnclicls();

        etcomments.setImeOptions(EditorInfo.IME_ACTION_DONE);
        etcomments.setRawInputType(InputType.TYPE_CLASS_TEXT);

        etcomments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvRemaingCount.setText(255 - s.toString().length() + " characters remaining");
                etcomments.setBackground(getResources().getDrawable(R.drawable.create_question_edittext_border));

            }
        });

        return view;
    }

    private void setOnclicls() {

        llUnsatisfied.setOnClickListener(this);
        llAverage.setOnClickListener(this);
        llSatisfied.setOnClickListener(this);

        btnSuggetions.setOnClickListener(this);
        btnCompliments.setOnClickListener(this);
        btnNotRignt.setOnClickListener(this);

        btnSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llUnsatisfied:

                DrawableCompat.setTint(imvUnsatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorRed));
                tvUnsatisfied.setTextColor(getResources().getColor(R.color.colorRed));

                DrawableCompat.setTint(imvAverage.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvAverage.setTextColor(getResources().getColor(R.color.colorBlack));

                DrawableCompat.setTint(imvSatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvSatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

                feedBackBodyModel.setExpressionper("30");

                llsmiles.setBackground(null);

                break;
            case R.id.llAverage:

                DrawableCompat.setTint(imvAverage.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorMath2));
                tvAverage.setTextColor(getResources().getColor(R.color.colorMath2));

                DrawableCompat.setTint(imvUnsatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvUnsatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

                DrawableCompat.setTint(imvSatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvSatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

                feedBackBodyModel.setExpressionper("50");

                llsmiles.setBackground(null);

                break;
            case R.id.llSatisfied:

                DrawableCompat.setTint(imvSatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorCreateNewQuestionButton));
                tvSatisfied.setTextColor(getResources().getColor(R.color.colorCreateNewQuestionButton));

                DrawableCompat.setTint(imvUnsatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvUnsatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

                DrawableCompat.setTint(imvAverage.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
                tvAverage.setTextColor(getResources().getColor(R.color.colorBlack));

                feedBackBodyModel.setExpressionper("80");

                llsmiles.setBackground(null);

                break;
            case R.id.btnSuggetions:

                GradientDrawable drawable1 = (GradientDrawable) btnSuggetions.getBackground();
                drawable1.setColor(getResources().getColor(R.color.colorfeedbackbtnbg));

                GradientDrawable drawable2 = (GradientDrawable) btnCompliments.getBackground();
                drawable2.setColor(getResources().getColor(R.color.colorWhite));

                GradientDrawable drawable3 = (GradientDrawable) btnNotRignt.getBackground();
                drawable3.setColor(getResources().getColor(R.color.colorWhite));

                feedBackBodyModel.setSubjectVal("Suggetions");

                llbuttons.setBackground(null);

                break;
            case R.id.btnCompliments:

                GradientDrawable drawable4 = (GradientDrawable) btnCompliments.getBackground();
                drawable4.setColor(getResources().getColor(R.color.colorfeedbackbtnbg));

                GradientDrawable drawable5 = (GradientDrawable) btnSuggetions.getBackground();
                drawable5.setColor(getResources().getColor(R.color.colorWhite));

                GradientDrawable drawable6 = (GradientDrawable) btnNotRignt.getBackground();
                drawable6.setColor(getResources().getColor(R.color.colorWhite));

                feedBackBodyModel.setSubjectVal("Compliments");

                llbuttons.setBackground(null);

                break;
            case R.id.btnNotRignt:

                GradientDrawable drawable7 = (GradientDrawable) btnNotRignt.getBackground();
                drawable7.setColor(getResources().getColor(R.color.colorfeedbackbtnbg));

                GradientDrawable drawable8 = (GradientDrawable) btnSuggetions.getBackground();
                drawable8.setColor(getResources().getColor(R.color.colorWhite));

                GradientDrawable drawable9 = (GradientDrawable) btnCompliments.getBackground();
                drawable9.setColor(getResources().getColor(R.color.colorWhite));

                feedBackBodyModel.setSubjectVal("Somethig is not quite rignt");

                llbuttons.setBackground(null);

                break;

            case R.id.btnSend:

                feedBackBodyModel.setMessageVal(etcomments.getText().toString());

                if (feedBackBodyModel.getExpressionper() == null) {

                    displayToast("please select one simle");
                    llsmiles.setBackground(getResources().getDrawable(R.drawable.background_red));

                } else if (feedBackBodyModel.getSubjectVal() == null) {

                    displayToast("please select one siggetion type");
                    llbuttons.setBackground(getResources().getDrawable(R.drawable.background_red));

                } else if (feedBackBodyModel.getMessageVal() == null || feedBackBodyModel.getMessageVal().equalsIgnoreCase("")) {

                    displayToast("please enter comments");
                    etcomments.setBackground(getResources().getDrawable(R.drawable.background_red));

                } else {

                    createFeedBack();
                    btnSend.setText("Sending....");
                }


                break;

            default:


        }
    }

    private void createFeedBack() {

        feedBackBodyModel.setAction("1001");
        feedBackBodyModel.setUseridVal(sessionManager.getUSER_Id());
        feedBackBodyModel.setEmailVal(sessionManager.getEmail());
        feedBackBodyModel.setNameVal(sessionManager.getFULLNAME());

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<LikesResModel>> call = service.createFeedBack(feedBackBodyModel);
        call.enqueue(new Callback<List<LikesResModel>>() {
            @Override
            public void onResponse(Call<List<LikesResModel>> call, Response<List<LikesResModel>> response) {

                List<LikesResModel> likesResModels = response.body();
                if (response.code() == 200 && likesResModels.get(0).getResponse().equalsIgnoreCase("success")) {

                    //displayToast("Successfully");
                    showSuccessPopup();
                    clearSelections();

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

    private void clearSelections() {

        feedBackBodyModel.setMessageVal(null);
        feedBackBodyModel.setSubjectVal(null);
        feedBackBodyModel.setExpressionper(null);
        etcomments.setText(null);
        btnSend.setText("send");

        DrawableCompat.setTint(imvSatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
        tvSatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

        DrawableCompat.setTint(imvUnsatisfied.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
        tvUnsatisfied.setTextColor(getResources().getColor(R.color.colorBlack));

        DrawableCompat.setTint(imvAverage.getDrawable(), ContextCompat.getColor(getContext(), R.color.colorBlack));
        tvAverage.setTextColor(getResources().getColor(R.color.colorBlack));


        GradientDrawable drawable1 = (GradientDrawable) btnSuggetions.getBackground();
        drawable1.setColor(getResources().getColor(R.color.colorWhite));

        GradientDrawable drawable2 = (GradientDrawable) btnCompliments.getBackground();
        drawable2.setColor(getResources().getColor(R.color.colorWhite));

        GradientDrawable drawable3 = (GradientDrawable) btnNotRignt.getBackground();
        drawable3.setColor(getResources().getColor(R.color.colorWhite));

        tvRemaingCount.setText(getResources().getString(R.string.characters_remaing));


    }

    private void showSuccessPopup() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.success_popup, null);
        TextView tvCancel = mView.findViewById(R.id.tvCancel);
        TextView tvDashBoard = mView.findViewById(R.id.tvDashBoard);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.hide();
            }
        });
        tvDashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
                alertDialog.hide();
            }
        });
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);
        alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }
}
