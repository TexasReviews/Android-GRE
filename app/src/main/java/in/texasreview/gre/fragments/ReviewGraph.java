package in.texasreview.gre.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class ReviewGraph extends Fragment {

    @BindView(R.id.chart)
    BarChart barChart;

    int[] colorClassArray = new int[]{Color.YELLOW, Color.RED};


    public ReviewGraph() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_graph, container, false);
        ButterKnife.bind(this,view);
        bindChatData();
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void bindChatData() {

        BarDataSet dataSet = new BarDataSet(datavalues(), null);
        dataSet.setColors(colorClassArray);
        dataSet.setStackLabels(new String[]{"Correct", "Wrong",});
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);
        barChart.setDrawGridBackground(false);
        barChart.setDescription(null);
        barChart.setScaleEnabled(false);
        barChart.setData(barData);
        barChart.setVisibleXRangeMaximum(3);
        barChart.setHorizontalScrollBarEnabled(true);


        YAxis y = barChart.getAxisLeft();
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setAxisMaximum(100);
        y.setAxisMinimum(0);
        y.setLabelCount(10);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(5);
        xAxis.setLabelCount(barnames().size());

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return barnames().get((int) value);
            }
        });
    }

    private ArrayList<BarEntry> datavalues() {

        ArrayList<BarEntry> datavalues = new ArrayList<BarEntry>();
        datavalues.add(new BarEntry(0, new float[]{20, 50}));
        datavalues.add(new BarEntry(1, new float[]{60, 30}));
        datavalues.add(new BarEntry(2, new float[]{10, 50}));
        datavalues.add(new BarEntry(3, new float[]{10, 50}));
        datavalues.add(new BarEntry(4, new float[]{10, 50}));
        datavalues.add(new BarEntry(5, new float[]{10, 50}));


        return datavalues;
    }

    private ArrayList<String> barnames() {

        ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Quantitative Comparision");
        xAxisLabel.add("Quantitative");
        xAxisLabel.add("Quantita Comparision\t");
        xAxisLabel.add("Quantita Comparision\t");
        xAxisLabel.add("Quantita Comparision\t");
        xAxisLabel.add("Quantita Comparision\t");
        return xAxisLabel;
    }
}
