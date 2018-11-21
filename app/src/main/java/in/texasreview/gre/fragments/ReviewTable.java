package in.texasreview.gre.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.ReviewTableModel;
import in.texasreview.gre.R;
import in.texasreview.gre.adapters.TableAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class ReviewTable extends Fragment {

    @BindView(R.id.rvTable)
    RecyclerView rvTable;

    ArrayList<ReviewTableModel> list = new ArrayList<>();


    public ReviewTable() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_table, container, false);
        ButterKnife.bind(this, view);
        setRecyclerview();
        return view;
    }

    private void setRecyclerview() {

        ReviewTableModel reviewTableModel0 = new ReviewTableModel();
        reviewTableModel0.setCategory("Category");
        reviewTableModel0.setWrongAnswers("Wrong Answers");
        reviewTableModel0.setCorrectAnswers("Correct Answers");
        reviewTableModel0.setTotalQuestions("Total Questions");
        reviewTableModel0.setTotalTime("Total Time");
        reviewTableModel0.setHeader(true);
        list.add(0, reviewTableModel0);


        for (int i = 0; i < 20; i++) {
            ReviewTableModel reviewTableModel = new ReviewTableModel();
            reviewTableModel.setCategory("Quantitative Comparision");
            reviewTableModel.setWrongAnswers("1");
            reviewTableModel.setCorrectAnswers("2");
            reviewTableModel.setTotalQuestions("2");
            reviewTableModel.setTotalTime("00:00:38");
            list.add(reviewTableModel);
        }


        rvTable.setHasFixedSize(true);
        rvTable.setLayoutManager(new LinearLayoutManager(getContext()));
        TableAdapter adapter = new TableAdapter(getContext(), list);
        rvTable.setAdapter(adapter);
        rvTable.setItemAnimator(new DefaultItemAnimator());
    }

}
