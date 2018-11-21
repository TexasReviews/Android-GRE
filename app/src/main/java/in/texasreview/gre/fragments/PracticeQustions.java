package in.texasreview.gre.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.util.Log;
import android.util.TimeUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.Models.PractUserQuaDataBodyModel;
import in.texasreview.gre.Models.PracticeTestQusModel;
import in.texasreview.gre.R;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.FragmentUpdate;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AppConstants;
import in.texasreview.gre.utils.MyProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.MEASURED_STATE_TOO_SMALL;
import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.view.View.TEXT_DIRECTION_FIRST_STRONG;
import static android.view.View.TEXT_DIRECTION_FIRST_STRONG_LTR;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class PracticeQustions extends Fragment implements View.OnClickListener {

    @BindView(R.id.imvBack)
    ImageView imvBack;

    @BindView(R.id.tvNumOfQus)
    TextView tvNumOfQus;

    @BindView(R.id.tvTotalTime)
    TextView tvTotalTime;

    @BindView(R.id.tvQusTime)
    TextView tvQusTime;

    @BindView(R.id.tvQusAllReadyVisit)
    TextView tvQusAllReadyVisit;

    @BindView(R.id.btnGetSolution)
    Button btnGetSolution;

    @BindView(R.id.tvQus)
    TextView tvQus;

    @BindView(R.id.llOptionsContainer)
    LinearLayout llOptionsContainer;

    @BindView(R.id.tvSolution)
    TextView tvSolution;

    @BindView(R.id.tvCorrectOrWrongAns)
    TextView tvCorrectOrWrongAns;

    @BindView(R.id.btnPrevious)
    Button btnPrevious;

    @BindView(R.id.btnNext)
    Button btnNext;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.imvQus)
    ImageView imvQus;

    @BindView(R.id.nestedscrollview)
    NestedScrollView nestedscrollview;

    @BindView(R.id.tvHeaderSolution)
    TextView tvHeaderSolution;

    @BindView(R.id.rg)
    RadioGroup rg;


    MyProgressBar myProgressBar;
    SessionManager sessionManager;

    ArrayList<String> idsList = new ArrayList<>();

    private int position;

    PracticeTestQusModel practiceTestQusModel;

    private boolean result;

    private String session;

    Date today = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

    long qusTimeTaken;
    long totalTimetaken;


    FragmentUpdate fragmentUpdate;

    //for timer
    private long qBase;
    private boolean qStarted;
    private String mDefaultFormat = "%02d:%02d:%02d";

    //for timer
    private long tBase;
    private boolean tStarted;

    //this is for totlal time start once after service call success
    boolean once;

    public PracticeQustions(FragmentUpdate fragmentUpdate) {
        this.fragmentUpdate = fragmentUpdate;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","onCreate");
        once = true;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice_qustions, container, false);
        ButterKnife.bind(this, view);
        idsList = getArguments().getStringArrayList(AppConstants.PRACTICETESTIDSKEY);
        session = getArguments().getString(AppConstants.SESSIONKEY);
        Log.i("onCreateView","onCreateView");
        sessionManager = new SessionManager(getContext());
        myProgressBar = new MyProgressBar(getContext());
        getPracticeTestQusById(idsList.get(position), sessionManager.getUSER_Id());
        setOnclicks();

        return view;

    }

   /* private void demo(){

        final ArrayList<Integer>  ids = new ArrayList<>();

        for (int i= 0;i<5;i++){
            ids.add(i);
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(i);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b){
                        Log.i("checked", String.valueOf(compoundButton.getId()));
                        ids.remove(compoundButton.getId());
                        for (int id : ids){
                            if (radioButton.getId() == id){
                                radioButton.setChecked(false);
                            }
                        }
                    }

                }
            });
            llDemo.addView(radioButton);
        }
    }*/

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause","onPause");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume","onResume");
    }

    private void setOnclicks() {

        imvBack.setOnClickListener(this);
        btnGetSolution.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imvBack:
                getActivity().onBackPressed();
                break;
            case R.id.btnGetSolution:

                nestedscrollview.fullScroll(nestedscrollview.FOCUS_DOWN);

                break;

            case R.id.btnPrevious:

                btnPrevious.setEnabled(false);
                position--;
                getPracticeTestQusById(idsList.get(position), sessionManager.getUSER_Id());

                break;
            case R.id.btnNext:

                btnNext.setEnabled(false);
                position++;
                try {

                    Log.i("position", String.valueOf(position));

                    getPracticeTestQusById(idsList.get(position), sessionManager.getUSER_Id());

                    if (!practiceTestQusModel.getData().isIs_attend()) {
                        Log.i("isIs_attend", String.valueOf(practiceTestQusModel.getData().isIs_attend()));
                        postPractUserQusData(sessionManager.getUSER_Id(), result, practiceTestQusModel.getData().getQua().getTrackid(), practiceTestQusModel.getData().getQua().getCategory(),
                                session, simpleDateFormat.format(today), String.valueOf(qusTimeTaken), practiceTestQusModel.getData().getQua().getQuestion_type_id());
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

                break;
            case R.id.btnSubmit:

                stopQusTime();
                stopTtlTime();
                fragmentUpdate.onSubmitClick();

                break;

            default:

        }
    }

    private void getPracticeTestQusById(String qusId, String userId) {


        Log.i("workoutId", qusId);

        imvQus.setVisibility(View.GONE);
        myProgressBar.show();

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<PracticeTestQusModel> call = service.getPracticeQusById(qusId, userId);
        call.enqueue(new Callback<PracticeTestQusModel>() {
            @Override
            public void onResponse(Call<PracticeTestQusModel> call, Response<PracticeTestQusModel> response) {

                btnPrevious.setEnabled(true);
                btnNext.setEnabled(true);

                practiceTestQusModel = response.body();

                if (practiceTestQusModel.getResponse().equalsIgnoreCase("success")) {

                    myProgressBar.hide();
                    startQusTime();
                    if (once) {
                        startTtlTime();
                    }
                    once = false;
                    bindData(practiceTestQusModel);
                }
            }

            @Override
            public void onFailure(Call<PracticeTestQusModel> call, Throwable t) {

                btnPrevious.setEnabled(true);
                btnNext.setEnabled(true);

                myProgressBar.hide();
                Log.e("error", t.getMessage());
                displayToast("" + t.getMessage());

            }
        });
    }
    private void bindData(PracticeTestQusModel practiceTestQusModel) {

        rg.removeAllViews();

        int quscount = position + 1;

        tvNumOfQus.setText("Questions " + idsList.size() + " of " + quscount);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvQus.setText("" + quscount + "." + Html.fromHtml(practiceTestQusModel.getData().getQua().getName(), Html.FROM_HTML_MODE_COMPACT, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String s) {
                    if (s != null) {
                        imvQus.setVisibility(VISIBLE);
                        Picasso.get().load(s).into(imvQus);
                    }
                    return null;
                }
            }, null));
            tvSolution.setText(Html.fromHtml(practiceTestQusModel.getData().getQua().getSolution(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvQus.setText("" + quscount + "." + Html.fromHtml(practiceTestQusModel.getData().getQua().getName(), new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String s) {
                    Log.i("imagetag", s);
                    if (s != null) {
                        imvQus.setVisibility(VISIBLE);
                        Picasso.get().load(s).into(imvQus);
                    }
                    return null;
                }
            }, null));
            tvSolution.setText(Html.fromHtml(practiceTestQusModel.getData().getQua().getSolution()));
        }

        if (idsList.size() - 1 == position && idsList.size() > 1) {

            btnNext.setVisibility(View.GONE);
            btnPrevious.setVisibility(VISIBLE);
            btnSubmit.setVisibility(VISIBLE);

        } else if (position == 0 && idsList.size() == 1) {

            btnNext.setVisibility(View.GONE);
            btnPrevious.setVisibility(View.GONE);
            btnSubmit.setVisibility(VISIBLE);

        } else if (position == 0 && idsList.size() > 1) {

            btnNext.setVisibility(VISIBLE);
            btnPrevious.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);

        } else {

            btnNext.setVisibility(VISIBLE);
            btnPrevious.setVisibility(VISIBLE);
            btnSubmit.setVisibility(View.GONE);
        }

        if (practiceTestQusModel.getData().isIs_attend())
            tvQusAllReadyVisit.setVisibility(VISIBLE);
        else
            tvQusAllReadyVisit.setVisibility(View.INVISIBLE);

        if (practiceTestQusModel.getData().getQua().isResult()) {
            tvCorrectOrWrongAns.setText("Correct Answer");
            tvCorrectOrWrongAns.setTextColor(getResources().getColor(R.color.colorCreateNewQuestionButton));
            tvCorrectOrWrongAns.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.correct, 0);
        } else {
            tvCorrectOrWrongAns.setText("Wrong Answer");
            tvCorrectOrWrongAns.setTextColor(getResources().getColor(R.color.colorRed));
            tvCorrectOrWrongAns.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.wrong, 0);
        }

        //for options
        for (int i = 0; i < practiceTestQusModel.getData().getQua().getOptions().size(); i++) {

            Log.i("size", String.valueOf(practiceTestQusModel.getData().getQua().getOptions().size()));


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 50);

            //LinearLayout layout = new LinearLayout(getContext());
            //layout.setOrientation(LinearLayout.HORIZONTAL);

            //ImageView imageView = new ImageView(getContext());
            //imageView.setImageResource(R.drawable.correct);
            //imageView.setVisibility(View.INVISIBLE);
            //layout.addView(imageView);


            //TextView textView = new TextView(getContext());
            int countposition = i + 1;
            //textView.setText("" + countposition);
            //layout.addView(textView);

            RadioButton rdbtn = new RadioButton(getContext());
            String plainText = Html.fromHtml(practiceTestQusModel.getData().getQua().getOptions().get(i).getName()).toString();

            rdbtn.setId(i);
            //rdbtn.setPadding(0,20,0,0);
            //rdbtn.setGravity(Gravity.CENTER);
            rdbtn.setText(""+countposition+"."+html2text(practiceTestQusModel.getData().getQua().getOptions().get(i).getName()));
            rdbtn.setPadding(0,0,0,20);
            //layout.addView(rdbtn);
            rg.addView(rdbtn);

            if (practiceTestQusModel.getData().isIs_attend()) {
                if (practiceTestQusModel.getData().getQua().getOptions().get(i).getUser_aws().equalsIgnoreCase("1")) {
                    rdbtn.setChecked(true);
                    rdbtn.setEnabled(false);

                }
                if (practiceTestQusModel.getData().getQua().getOptions().get(i).getIs_answer().equalsIgnoreCase("1")) {
                    //imageView.setVisibility(VISIBLE);
                    //Drawable img = getContext().getResources().getDrawable( R.drawable.correct );
                    //img.setBounds( 30, 30, 0, 0 );
                    //rdbtn.setCompoundDrawables( img, null, null, null );
                    //rdbtn.setButtonDrawable(img);
                    //rdbtn.setCompoundDrawablesRelative(img,null,null,null);
                    //rdbtn.setCompoundDrawablesRelativeWithIntrinsicBounds(img,null,null,null);
                    //rdbtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.correct, 0, 0, 0);
                    rdbtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.correct,0,0,0);
                    //rdbtn.setPadding(0,0,0,0);

                }
            }
        }
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    private void postPractUserQusData(String userId, Boolean result, String trackid, String catType, String sessionId, String createdDate, String timetaken, String subType) {

        myProgressBar.show();

        Gson gson = new Gson();
        String totalObject = gson.toJson(practiceTestQusModel);

        final PractUserQuaDataBodyModel practUserQuaDataBodyModel = new PractUserQuaDataBodyModel();
        practUserQuaDataBodyModel.setUserid(userId);
        practUserQuaDataBodyModel.setResult(result);
        practUserQuaDataBodyModel.setTrackid(trackid);
        practUserQuaDataBodyModel.setTotalobj(totalObject);
        practUserQuaDataBodyModel.setCattype(catType);
        practUserQuaDataBodyModel.setSessionid(sessionId);
        practUserQuaDataBodyModel.setCreate_date(createdDate);
        practUserQuaDataBodyModel.setTimetaken(timetaken);
        practUserQuaDataBodyModel.setSubtype(subType);

        Log.i("practUserQuaDataBody", practUserQuaDataBodyModel.getTotalobj());

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<LikesResModel>> call = service.postPractUserQusData(practUserQuaDataBodyModel);
        call.enqueue(new Callback<List<LikesResModel>>() {
            @Override
            public void onResponse(Call<List<LikesResModel>> call, Response<List<LikesResModel>> response) {

                List<LikesResModel> likesResModels = response.body();
                if (response.code() == 200 && likesResModels.get(0).getResponse().equalsIgnoreCase("success")) {

                    myProgressBar.hide();
                    displayToast("Successfully");
                    Log.i("postPractUserQusData", likesResModels.get(0).getResponse());

                } else {
                    myProgressBar.hide();
                    displayToast("error");
                }
            }

            @Override
            public void onFailure(Call<List<LikesResModel>> call, Throwable t) {

                displayToast("" + t.getMessage());
            }
        });
    }

    private void displayToast(String value) {

        Toast.makeText(getActivity(), "" + value, Toast.LENGTH_SHORT).show();
    }

    /**
     * Set the time that the count-up timer is in reference to.
     *
     * @param base Use the {@link SystemClock#elapsedRealtime} time base.
     */
    public void setQusBase(long base) {
        qBase = base;
        updateQusText(SystemClock.elapsedRealtime());
    }

    /**
     * Return the base time as set through {@link #setQusBase}.
     */
    public long getQusBase() {
        return qBase;
    }

    /**
     * Start counting up.  This does not affect the base as set from {@link #setQusBase(long)} }, just
     * the view display.
     * <p>
     * Chronometer works by regularly scheduling messages to the handler, even when the
     * Widget is not visible.  To make sure resource leaks do not occur, the user should
     * make sure that each start() call has a reciprocal call to {@link #stopQusTime()}.
     */
    public void startQusTime() {
        qBase = SystemClock.elapsedRealtime();
        updateQusText(qBase);
        qStarted = true;
        updateQusRunning();
    }

    /**
     * Stop counting up.  This does not affect the base as set from {@link #setQusBase}, just
     * the view display.
     * <p>
     * This stops the messages to the handler, effectively releasing resources that would
     * be held as the chronometer is running, via {@link #startQusTime()}.
     */
    public void stopQusTime() {
        qStarted = false;
        updateQusRunning();
        long now = SystemClock.elapsedRealtime();
        setQusBase(qBase);
        updateQusText(now);
    }

    private void updateQusText(long now) {

        long seconds = (now - qBase) / 1000;
        int hh = (int) (seconds / 3600);
        int mm = (int) ((seconds % 3600) / 60);
        int ss = (int) (seconds % 60);

        String text = String.format(Locale.US, mDefaultFormat, hh, mm, ss);
        String[] separated = text.split(":");
        long hour = TimeUnit.HOURS.toSeconds(Long.parseLong(separated[0]));
        long min = TimeUnit.MINUTES.toSeconds(Long.parseLong(separated[1]));
        long sec = TimeUnit.SECONDS.toSeconds(Long.parseLong(separated[2]));
        qusTimeTaken = hour+min+sec;
        tvQusTime.setText("Qus Time" + text);
    }

    private void updateQusRunning() {
        updateQusText(SystemClock.elapsedRealtime());
        qHandler.sendMessageDelayed(Message.obtain(), 1000);
    }

    private Handler qHandler = new Handler() {
        public void handleMessage(Message m) {
            if (qStarted) {
                updateQusText(SystemClock.elapsedRealtime());
                sendMessageDelayed(Message.obtain(), 1000);
            }
        }
    };

    //FOR TTL TIME

    /**
     * Set the time that the count-up timer is in reference to.
     *
     * @param base Use the {@link SystemClock#elapsedRealtime} time base.
     */
    public void setTtlBase(long base) {
        tBase = base;
        updateTtlText(SystemClock.elapsedRealtime());
    }

    /**
     * Return the base time as set through {@link #setTtlBase(long)} }.
     */
    public long getTtlBase() {
        return tBase;
    }

    /**
     * Start counting up.  This does not affect the base as set from {@link #setTtlBase}, just
     * the view display.
     * <p>
     * Chronometer works by regularly scheduling messages to the handler, even when the
     * Widget is not visible.  To make sure resource leaks do not occur, the user should
     * make sure that each start() call has a reciprocal call to {@link #stopTtlTime()}.
     */
    public void startTtlTime() {
        tBase = SystemClock.elapsedRealtime();
        updateTtlText(tBase);
        tStarted = true;
        updateTtlRunning();
    }

    /**
     * Stop counting up.  This does not affect the base as set from {@link #setTtlBase}, just
     * the view display.
     * <p>
     * This stops the messages to the handler, effectively releasing resources that would
     * be held as the chronometer is running, via {@link #startTtlTime()}.
     */
    public void stopTtlTime() {
        tStarted = false;
        updateTtlRunning();
        long tnow = SystemClock.elapsedRealtime();
        setTtlBase(tnow);
        updateTtlText(tnow);
    }

    private void updateTtlText(long tnow) {

        long seconds = (tnow - tBase) / 1000;
        int hh = (int) (seconds / 3600);
        int mm = (int) ((seconds % 3600) / 60);
        int ss = (int) (seconds % 60);

        String text = String.format(Locale.US, mDefaultFormat, hh, mm, ss);
        Log.i("textView", text);
        String[] separated = text.split(":");
        long hour = TimeUnit.HOURS.toSeconds(Long.parseLong(separated[0]));
        long min = TimeUnit.MINUTES.toSeconds(Long.parseLong(separated[1]));
        long sec = TimeUnit.SECONDS.toSeconds(Long.parseLong(separated[2]));
        totalTimetaken = hour+min+sec;
        tvTotalTime.setText("Total Time" + text);
    }

    private void updateTtlRunning() {

        updateTtlText(SystemClock.elapsedRealtime());
        tHandler.sendMessageDelayed(Message.obtain(), 1000);
    }

    private Handler tHandler = new Handler() {
        public void handleMessage(Message m) {
            if (tStarted) {
                updateTtlText(SystemClock.elapsedRealtime());
                sendMessageDelayed(Message.obtain(), 1000);
            }
        }
    };
}




