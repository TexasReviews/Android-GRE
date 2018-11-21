package in.texasreview.gre.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import butterknife.BindView;
import butterknife.ButterKnife;

import in.texasreview.gre.Models.PracticeTestIdModel;
import in.texasreview.gre.R;
import in.texasreview.gre.activities.Login;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.FragmentUpdate;
import in.texasreview.gre.interfaces.ManageServices;
import io.realm.Realm;
import io.realm.RealmModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
@SuppressLint("ValidFragment")
public class PracticeTest extends Fragment implements View.OnClickListener {

    FragmentUpdate fragmentUpdate;


    public PracticeTest(FragmentUpdate fragmentUpdate) {
        this.fragmentUpdate = fragmentUpdate;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PracticeTest.
     */


    @BindView(R.id.imvPsExpand)
    ImageView imvPsExpand;

    @BindView(R.id.llProblenSolving)
    LinearLayout llProblenSolving;

    @BindView(R.id.cbQuantitative_Comparision)
    CheckBox cbQuantitative_Comparision;

    @BindView(R.id.cbData_Interpretation)
    CheckBox cbData_Interpretation;

    @BindView(R.id.cbProblenSolving)
    CheckBox cbProblenSolving;

    @BindView(R.id.cbSingle_Answer)
    CheckBox cbSingle_Answer;

    @BindView(R.id.cbMultiple_Answer)
    CheckBox cbMultiple_Answer;

    @BindView(R.id.cbNumeric_Entry)
    CheckBox cbNumeric_Entry;

    @BindView(R.id.llSubCatogiriContainer)
    LinearLayout llSubCatogiriContainer;

    @BindView(R.id.llMath)
    LinearLayout llMath;

    @BindView(R.id.llVerbal)
    LinearLayout llVerbal;

    @BindView(R.id.nsv)
    NestedScrollView nsv;

    @BindView(R.id.rbMath)
    RadioButton rbMath;

    @BindView(R.id.rbVerbal)
    RadioButton rbVerbal;

    @BindView(R.id.rgMathOrVerbal)
    RadioGroup rgMathOrVerbal;

    @BindView(R.id.tvNumOfQus)
    TextView tvNumOfQus;

    String[] subcatnames;

    //verbal
    @BindView(R.id.imvExpandVerdal1)
    ImageView imvExpandVerdal1;

    @BindView(R.id.imvExpandVerdal2)
    ImageView imvExpandVerdal2;

    @BindView(R.id.llText_Completion)
    LinearLayout llText_Completion;

    @BindView(R.id.llReading_Comprehension)
    LinearLayout llReading_Comprehension;

    @BindView(R.id.cbText_Completion)
    CheckBox cbText_Completion;

    @BindView(R.id.cbSingle_Blank)
    CheckBox cbSingle_Blank;

    @BindView(R.id.cbDouble_Blank)
    CheckBox cbDouble_Blank;

    @BindView(R.id.cbTriple_Blank)
    CheckBox cbTriple_Blank;

    @BindView(R.id.cbSentence_Equivalence)
    CheckBox cbSentence_Equivalence;

    @BindView(R.id.cbReading_Comprehension)
    CheckBox cbReading_Comprehension;

    @BindView(R.id.cbShort)
    CheckBox cbShort;

    @BindView(R.id.cbLong)
    CheckBox cbLong;

    @BindView(R.id.cbCritical_Reasoning)
    CheckBox cbCritical_Reasoning;

    @BindView(R.id.btnPracticeTest)
    Button btnPracticeTest;

    @BindView(R.id.etTestName)
    EditText etTestName;

    PracticeTestIdModel practiceTestIdModel;

    ArrayList<String> selectedQusType = new ArrayList<>();
    ArrayList<String> selectedLession = new ArrayList<>();

    ArrayList<String> ids = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subcatnames = getContext().getResources().getStringArray(R.array.subcatnames);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_practice_test, container, false);
        ButterKnife.bind(this, view);
        createCheckBoxes();
        setCheckedCheckBoxMath();
        setCheckedCheckBoxVerbal();
        setOnClicks();
        getAllPracticeTestIds();
        OnRadioButttonCheck();
        return view;


    }

    private void setOnClicks() {

        imvPsExpand.setOnClickListener(this);
        imvExpandVerdal1.setOnClickListener(this);
        imvExpandVerdal2.setOnClickListener(this);
        btnPracticeTest.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        etTestName.setText("");
    }

    private void OnRadioButttonCheck() {

        rgMathOrVerbal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                Log.i("index", String.valueOf(index));
                switch (index) {

                    case 0:
                        llMath.setVisibility(View.VISIBLE);
                        llVerbal.setVisibility(View.GONE);
                        getIds("Maths", null);
                        break;
                    case 1:

                        llMath.setVisibility(View.GONE);
                        llVerbal.setVisibility(View.VISIBLE);
                        getIds("Verbal", null);
                        break;

                    default:
                }
            }
        });
    }

    private void setCheckedCheckBoxMath() {

        //1
        cbQuantitative_Comparision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    selectedQusType.add(buttonView.getText().toString());
                    getQustypeIdsMath(buttonView.getText().toString());

                } else {

                    selectedQusType.remove(buttonView.getText().toString());

                }

            }
        });
        //2
        cbData_Interpretation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    selectedQusType.add(buttonView.getText().toString());
                    getQustypeIdsMath(buttonView.getText().toString());

                } else {

                    selectedQusType.remove(buttonView.getText().toString());

                }

            }
        });
        //3
        cbProblenSolving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    cbSingle_Answer.setChecked(true);
                    cbMultiple_Answer.setChecked(true);
                    cbNumeric_Entry.setChecked(true);

                    selectedQusType.add("Problem Solving-Single Answer");
                    selectedQusType.add("Problem Solving-Multiple Answer");
                    selectedQusType.add("Problem Solving-Numeric Entry");

                    if (!llProblenSolving.isShown()) {

                        imvPsExpand.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                        llProblenSolving.setVisibility(View.VISIBLE);
                    }

                    getQustypeIdsMath(buttonView.getText().toString());

                } else {
                    cbSingle_Answer.setChecked(false);
                    cbMultiple_Answer.setChecked(false);
                    cbNumeric_Entry.setChecked(false);

                    selectedQusType.remove("Problem Solving-Single Answer");
                    selectedQusType.remove("Problem Solving-Multiple Answer");
                    selectedQusType.remove("Problem Solving-Numeric Entry");

                }

            }
        });
        //4
        cbSingle_Answer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    selectedQusType.add(buttonView.getText().toString());
                    getQustypeIdsMath(buttonView.getText().toString());


                } else {

                    selectedQusType.remove(buttonView.getText().toString());

                }

            }
        });
        //6
        cbMultiple_Answer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    selectedQusType.add(buttonView.getText().toString());
                    getQustypeIdsMath(buttonView.getText().toString());

                } else {

                    selectedQusType.remove(buttonView.getText().toString());


                }

            }
        });

        cbNumeric_Entry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    selectedQusType.add(buttonView.getText().toString());
                    getQustypeIdsMath(buttonView.getText().toString());

                } else {

                    selectedQusType.remove(buttonView.getText().toString());
                }
            }
        });

    }

    private void createCheckBoxes() {

        LinearLayout layout = null;
        for (int i = 0; i < subcatnames.length; i++) {

            layout = new LinearLayout(getContext());
            layout.setGravity(Gravity.CENTER_VERTICAL);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.circle);
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(subcatnames[i]);
            checkBox.setId(i);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedLession.add(buttonView.getText().toString());
                        getLessionIdsMath(buttonView.getText().toString());

                    } else {

                        selectedLession.remove(buttonView.getText().toString());
                    }

                }
            });
            layout.addView(imageView);
            layout.addView(checkBox);
            llSubCatogiriContainer.addView(layout);
        }


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imvPsExpand:

                if (llProblenSolving.isShown()) {

                    imvPsExpand.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add));
                    llProblenSolving.setVisibility(View.GONE);
                } else {

                    imvPsExpand.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                    llProblenSolving.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.imvExpandVerdal1:

                if (llText_Completion.isShown()) {

                    imvExpandVerdal1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add));
                    llText_Completion.setVisibility(View.GONE);
                } else {

                    imvExpandVerdal1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                    llText_Completion.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.imvExpandVerdal2:

                if (llReading_Comprehension.isShown()) {

                    imvExpandVerdal2.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add));
                    llReading_Comprehension.setVisibility(View.GONE);
                } else {

                    imvExpandVerdal2.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                    llReading_Comprehension.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btnPracticeTest:

                HashSet<String> hashSet = new HashSet<String>();
                hashSet.addAll(selectedQusType);
                selectedQusType.clear();
                selectedQusType.addAll(hashSet);

                Log.i("selectedQusType", String.valueOf(selectedQusType));
                Log.i("selectedQusType", String.valueOf(selectedQusType.size()));

                if (etTestName.getText().toString() == null || etTestName.getText().toString().equalsIgnoreCase("")) {

                    displatToast("please enter test name ");

                } else {

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    fragmentUpdate.onPracticetestClick(ids, etTestName.getText().toString());
                }
                break;
            default:
        }
    }


    private void getAllPracticeTestIds() {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<PracticeTestIdModel> call = service.getAllPracticeTestQuestions();
        call.enqueue(new Callback<PracticeTestIdModel>() {
            @Override
            public void onResponse(Call<PracticeTestIdModel> call, Response<PracticeTestIdModel> response) {

                practiceTestIdModel = response.body();
                Log.i("practiceTestRealmMaster", practiceTestIdModel.getResponse());
                if (practiceTestIdModel.getData().size() > 0) {

                    getIds("Maths", null);
                }
            }

            @Override
            public void onFailure(Call<PracticeTestIdModel> call, Throwable t) {


                displatToast("Something went wrong...Please try later!");
            }
        });
    }

    private void getIds(String category, String lession, String questiontype) {

        for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

            if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase(category)) {

                if (practiceTestIdModel.getData().get(i).getQuestiontype().equalsIgnoreCase(questiontype)) {

                    Log.i("ids", String.valueOf(practiceTestIdModel.getData().get(i).getQuestionids()));

                    for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                        if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getCategoryid().equalsIgnoreCase(category)) {

                            if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestiontype().equalsIgnoreCase(questiontype)) {

                                Log.i("ids", String.valueOf(practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()));

                            }
                        }
                    }
                }
            }
        }
    }

    private void getIds(String category, String lession) {

        for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

            if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase(category)) {

                Log.i("ids", String.valueOf(practiceTestIdModel.getData().get(i).getQuestionids()));

                for (String idss : practiceTestIdModel.getData().get(i).getQuestionids()) {

                    ids.add(idss);

                }

                for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                    Log.i("ids", String.valueOf(practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()));

                    for (String idss : practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()) {

                        ids.add(idss);
                    }
                }
            }

            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(ids);
            ids.clear();
            ids.addAll(hashSet);
            Log.i("totalids", String.valueOf(ids));
            Log.i("totalidssize", String.valueOf(ids.size()));
            tvNumOfQus.setText("Your test will contains " + ids.size() + " qusetions");
        }
    }

    private void getLessionIdsMath(String lessionIds) {

        if (selectedQusType.size() == 0) {

            for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

                if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase("Maths")) {

                    if (practiceTestIdModel.getData().get(i).getLessonid().equalsIgnoreCase(lessionIds)) {

                        for (String idss : practiceTestIdModel.getData().get(i).getQuestionids()) {

                            ids.add(idss);

                        }
                    }
                    for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                        if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getLessonid().equalsIgnoreCase(lessionIds)) {

                            for (String idss : practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()) {

                                ids.add(idss);
                            }
                        }
                    }
                }
            }

        } else {

            for (String questiontype : selectedQusType) {

                for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

                    if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase("Maths")) {

                        if (practiceTestIdModel.getData().get(i).getQuestiontype().equalsIgnoreCase(questiontype) && practiceTestIdModel.getData().get(i).getLessonid().equalsIgnoreCase(lessionIds)) {

                            for (String idss : practiceTestIdModel.getData().get(i).getQuestionids()) {

                                ids.add(idss);

                            }
                        }
                        for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                            if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestiontype().equalsIgnoreCase(questiontype) && practiceTestIdModel.getData().get(i).getSubcategories().get(j).getLessonid().equalsIgnoreCase(lessionIds)) {

                                for (String idss : practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()) {

                                    ids.add(idss);
                                }
                            }
                        }
                    }
                }
            }

        }
        Log.i("totalids", String.valueOf(ids));
        Log.i("totalidssize", String.valueOf(ids.size()));
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(ids);
        ids.clear();
        ids.addAll(hashSet);
        tvNumOfQus.setText("Your test will contains " + ids.size() + " qusetions");
    }

    private void getQustypeIdsMath(String qusType) {

        if (selectedLession.size() == 0) {

            for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

                if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase("Maths")) {

                    if (practiceTestIdModel.getData().get(i).getQuestiontype().equalsIgnoreCase(qusType)) {

                        for (String idss : practiceTestIdModel.getData().get(i).getQuestionids()) {

                            ids.add(idss);

                        }
                    }
                    for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                        if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestiontype().equalsIgnoreCase(qusType)) {

                            for (String idss : practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()) {

                                ids.add(idss);
                            }
                        }
                    }
                }
            }


        } else {

            for (String qustype : selectedLession) {

                for (int i = 0; i < practiceTestIdModel.getData().size(); i++) {

                    if (practiceTestIdModel.getData().get(i).getCategoryid().equalsIgnoreCase("Maths")) {

                        if (practiceTestIdModel.getData().get(i).getQuestiontype().equalsIgnoreCase(qustype) && practiceTestIdModel.getData().get(i).getQuestiontype().equalsIgnoreCase(qusType)) {

                            for (String idss : practiceTestIdModel.getData().get(i).getQuestionids()) {

                                ids.add(idss);

                            }
                        }
                        for (int j = 0; j < practiceTestIdModel.getData().get(i).getSubcategories().size(); j++) {

                            if (practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestiontype().equalsIgnoreCase(qustype) && practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestiontype().equalsIgnoreCase(qusType)) {

                                for (String idss : practiceTestIdModel.getData().get(i).getSubcategories().get(j).getQuestionids()) {

                                    ids.add(idss);
                                }
                            }
                        }
                    }
                }
            }
        }
        Log.i("totalids", String.valueOf(ids));
        Log.i("totalidssize", String.valueOf(ids.size()));
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(ids);
        ids.clear();
        ids.addAll(hashSet);
        tvNumOfQus.setText("Your test will contains " + ids.size() + " qusetions");
    }


    // verbal purpose

    private void setCheckedCheckBoxVerbal() {

        //1
        cbText_Completion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    cbSingle_Blank.setChecked(true);
                    cbDouble_Blank.setChecked(true);
                    cbTriple_Blank.setChecked(true);

                    if (!llText_Completion.isShown()) {

                        imvExpandVerdal1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                        llText_Completion.setVisibility(View.VISIBLE);
                    }

                } else {
                    cbSingle_Blank.setChecked(false);
                    cbDouble_Blank.setChecked(false);
                    cbTriple_Blank.setChecked(false);
                }
            }
        });
        //2
        cbSingle_Blank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });
        //3
        cbDouble_Blank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });
        //4
        cbTriple_Blank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });
        //6
        cbSentence_Equivalence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });

        cbReading_Comprehension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    cbShort.setChecked(true);
                    cbLong.setChecked(true);


                    if (!llReading_Comprehension.isShown()) {

                        imvExpandVerdal2.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.remove));
                        llReading_Comprehension.setVisibility(View.VISIBLE);
                    }

                } else {
                    cbShort.setChecked(false);
                    cbLong.setChecked(false);

                }

            }
        });

        cbShort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });

        cbLong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });

        cbCritical_Reasoning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });

    }

    private void displatToast(String value) {

        Toast.makeText(getActivity(), "" + value, Toast.LENGTH_SHORT).show();
    }
}
