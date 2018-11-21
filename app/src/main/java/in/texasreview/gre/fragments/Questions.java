package in.texasreview.gre.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.Models.OptionsModel;
import in.texasreview.gre.Models.QuestionsModel;
import in.texasreview.gre.Models.VideoTestUnlockBodyModel;
import in.texasreview.gre.R;
import in.texasreview.gre.adapters.VideoQusViewPageAdapter;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.interfaces.OnVideoTestAnsSelected;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AppConstants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class Questions extends Fragment implements OnVideoTestAnsSelected {

    String testId;
    String videoId;

    @BindView(R.id.vpVideoExamQus)
    ViewPager vpVideoExamQus;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.imvBack)
    ImageView imvBack;

    int positionlast;


    @BindView(R.id.tvVideoExamAdapterCount)
    TextView tvVideoExamAdapterCount;

    ArrayList<QuestionsModel> questionslist = new ArrayList<>();

    private boolean ansTrueOrFalse;


    public Questions() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testId = getArguments().getString(AppConstants.TESTIDKEY);
        videoId = getArguments().getString(AppConstants.VIDEOIDKEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, view);
        setOnclick();
        getVideoQusById(testId);

        vpVideoExamQus.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                bindviews();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void bindviews() {

        int currentitem = vpVideoExamQus.getCurrentItem();
        int finalitem = currentitem + 1;

        tvVideoExamAdapterCount.setText("Questions " + questionslist.size() + " of " + finalitem);
    }


    private void setOnclick() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ansTrueOrFalse) {

                    displayToast("Correct Answer");
                    videoTestUnlock();

                } else {

                    displayToast("Please Select Correct Answer");
                }


            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
            }
        });
    }


    private void getVideoQusById(String testId) {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<ResponseBody> call = service.getVideoQusByQusId(testId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {

                    try {
                        String content = new String(getBytesFromInputStream(response.body().byteStream()));
                        Log.d("content", content);
                        JSONObject jsonObject1 = new JSONObject(content);
                        JSONArray questions = jsonObject1.getJSONArray("questions");
                        for (int i = 0; i < questions.length(); i++) {

                            JSONObject qusObject = (JSONObject) questions.get(i);
                            String question_type_id = qusObject.getString("question_type_id");

                            if (question_type_id.equalsIgnoreCase("1")) {
                                Log.i("qusObject", qusObject.toString());
                                String qusname = qusObject.optString("name");
                                QuestionsModel questionsmodel = new QuestionsModel();
                                questionsmodel.setQusname(qusname);

                                JSONArray options = qusObject.getJSONArray("options");
                                Log.i("options", options.toString());

                                ArrayList<OptionsModel> optionslist = new ArrayList<>();

                                for (int j = 0; j < options.length(); j++) {
                                    JSONObject optObject = options.optJSONObject(j);
                                    String optName = optObject.optString("name");
                                    String is_answer = optObject.optString("is_answer");
                                    OptionsModel optionsmodel = new OptionsModel();
                                    optionsmodel.setName(optName);
                                    optionsmodel.setIs_answer(is_answer);
                                    optionslist.add(optionsmodel);
                                    questionsmodel.setOptions(optionslist);
                                }
                                questionslist.add(questionsmodel);
                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getViewPageAdapter(questionslist);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                displayToast("Something went wrong...Please try later!");
            }
        });
    }

    public byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        try {
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toByteArray();
        } catch (OutOfMemoryError error) {
            return null;
        }
    }

    private void displayToast(String value) {
        Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
    }

    private void getViewPageAdapter(ArrayList<QuestionsModel> questionslist) {

        bindviews();
        positionlast = questionslist.size();
        //apply filter
        //Arrays.sort(new ArrayList[]{questionslist}, Collections.reverseOrder());
        VideoQusViewPageAdapter videoQusViewPageAdapter = new VideoQusViewPageAdapter(getContext(), questionslist, Questions.this);
        vpVideoExamQus.setAdapter(videoQusViewPageAdapter);
    }

    @Override
    public void isTreue(boolean trurorfalse) {

        if (trurorfalse) {

            ansTrueOrFalse = true;
        } else {

            ansTrueOrFalse = false;
        }

    }

    private void videoTestUnlock() {

        SessionManager sessionManager = new SessionManager(getContext());

        VideoTestUnlockBodyModel videoTestUnlockBodyModel = new VideoTestUnlockBodyModel();
        videoTestUnlockBodyModel.setUserid(sessionManager.getUSER_Id());
        videoTestUnlockBodyModel.setVideoid(videoId);


        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<LikesResModel>> call = service.videoUnlockInsert(videoTestUnlockBodyModel);
        call.enqueue(new Callback<List<LikesResModel>>() {
            @Override
            public void onResponse(Call<List<LikesResModel>> call, Response<List<LikesResModel>> response) {

                List<LikesResModel> likesResModels = response.body();
                if (response.code() == 200 && likesResModels.get(0).getResponse().equalsIgnoreCase("success")) {

                    displayToast("Successfully");
                    pageScroll();


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

    private void pageScroll() {

        int previous = vpVideoExamQus.getCurrentItem()+1;
        if (positionlast == previous) {
            getActivity().onBackPressed();
        } else {
            int next = vpVideoExamQus.getCurrentItem() + 1;
            vpVideoExamQus.setCurrentItem(next, true);

        }
    }
}


