package in.texasreview.gre.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.GreScoreModel;
import in.texasreview.gre.R;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.MyProgressBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * created by srinivas
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {

    @BindView(R.id.tlGreScore)
    TableLayout tlGreScore;

    @BindView(R.id.tlAwaSession)
    TableLayout tlAwaSession;


    @BindView(R.id.barChart)
    BarChart barChart;

    @BindView(R.id.linechart)
    LineChart linechart;

    @BindView(R.id.tvmQusUnlock)
    TextView tvmQusUnlock;

    @BindView(R.id.pbMath)
    ProgressBar pbMath;

    @BindView(R.id.pbVerbal)
    ProgressBar pbVerbal;


    MyProgressBar myProgressBar;
    SessionManager sessionManager;

    @BindView(R.id.tvmQuestions_Correct)
    TextView tvmQuestions_Correct;

    @BindView(R.id.tvmQuestions_practiced)
    TextView tvmQuestions_practiced;

    @BindView(R.id.tvmAvgTime)
    TextView tvmAvgTime;

    @BindView(R.id.tvvQusUnlock)
    TextView tvvQusUnlock;

    @BindView(R.id.tvvQuestions_Correct)
    TextView tvvQuestions_Correct;

    @BindView(R.id.tvvQuestions_practiced)
    TextView tvvQuestions_practiced;

    @BindView(R.id.tvvAvgTime)
    TextView tvvAvgTime;

    ArrayList<GreScoreModel> scoreList = new ArrayList<>();

    ArrayList<BarEntry> barentry;
    ArrayList<String> barlabels;
    BarDataSet bardataset;
    BarData bardata;


    public Dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Dashboard.
     */

    public static Dashboard newInstance() {
        return new Dashboard();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myProgressBar = new MyProgressBar(getContext());
        myProgressBar.show();
        sessionManager = new SessionManager(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        getDashboard(sessionManager.getUSER_Id());
        return view;
    }

    private void getDashboard(String userId) {

        myProgressBar.show();
        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<ResponseBody> call = service.getDashboard(userId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {

                    myProgressBar.hide();

                    try {
                        String content = new String(getBytesFromInputStream(response.body().byteStream()));
                        Log.i("responce", content);
                        JSONObject jsonObject1 = new JSONObject(content);
                        if (jsonObject1.getString("response").equalsIgnoreCase("success")) {
                            JSONObject data = jsonObject1.getJSONObject("data");
                            binddata(data);
                        }
                    } catch (Exception e) {
                        myProgressBar.hide();
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                myProgressBar.hide();
                displayToast("error please try again");

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

        Toast.makeText(getActivity(), "" + value, Toast.LENGTH_SHORT).show();
    }

    private void binddata(JSONObject data) {

        try {
            JSONObject math = data.getJSONObject("maths");
            String mtotalqua = math.optString("totalqua");
            String mpractisedqua = math.optString("practisedqua");
            String mcorrectAns = math.optString("correctAns");
            String mavgTime = math.optString("avgTime");

            //for math
            long mathper = 100 * Integer.parseInt(mpractisedqua) / Integer.parseInt(mtotalqua);
            tvmQusUnlock.setText(String.valueOf(mathper) + "%");
            pbMath.setProgress(Integer.parseInt(String.valueOf(mathper)));
            pbMath.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
            Log.i("mathper", String.valueOf(mathper));

            long qusCorrect = 100 * Integer.parseInt(mcorrectAns) / Integer.parseInt(mpractisedqua);

            tvmQuestions_Correct.setText(String.valueOf(qusCorrect) + "%");
            tvmQuestions_practiced.setText(mpractisedqua);

            StringTokenizer tokens = new StringTokenizer(mavgTime, ".");
            String first = tokens.nextToken();
            tvmAvgTime.setText(timeConversion(Integer.parseInt(first)));

            //for verbal
            JSONObject verbal = data.getJSONObject("Verbal");
            String vtotalqua = verbal.optString("totalqua");
            String vpractisedqua = verbal.optString("practisedqua");
            String vcorrectAns = verbal.optString("correctAns");
            String vavgTime = verbal.optString("avgTime");
            long verbalper = 100 * Integer.parseInt(vpractisedqua) / Integer.parseInt(vtotalqua);

            tvvQusUnlock.setText(String.valueOf(verbalper) + "%");
            pbVerbal.setProgress(Integer.parseInt(String.valueOf(verbalper)));
            pbVerbal.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
            Log.i("mathper", String.valueOf(verbalper));

            long qusCorrectVerbal = 100 * Integer.parseInt(vcorrectAns) / Integer.parseInt(vpractisedqua);

            tvvQuestions_Correct.setText(String.valueOf(qusCorrectVerbal) + "%");
            tvvQuestions_practiced.setText(vpractisedqua);

            StringTokenizer tokensVerbal = new StringTokenizer(vavgTime, ".");
            String firstVerbal = tokensVerbal.nextToken();// this will contain "Fruit"
            tvvAvgTime.setText(timeConversion(Integer.parseInt(firstVerbal)));

            JSONArray fulllength = data.getJSONArray("fullLength_mobile");
            for (int i = 0; i < fulllength.length(); i++) {

                JSONObject gredata = fulllength.getJSONObject(i);

                GreScoreModel greScoreModel = new GreScoreModel();
                greScoreModel.setTestname(gredata.optString("name"));
                greScoreModel.setStatus(gredata.optString("awsstatus"));

                JSONObject graphdata = gredata.getJSONObject("graphdata");

                greScoreModel.setVerbal(graphdata.optString("verbal"));
                greScoreModel.setQuant(graphdata.optString("quant"));
                greScoreModel.setTotal(graphdata.optString("grescore"));
                greScoreModel.setScore(graphdata.getString("awsscore"));
                scoreList.add(greScoreModel);
            }
            //for graph
            JSONArray linegraph = data.getJSONArray("line_graph_data_mobile");
            Log.i("linegraph", String.valueOf(linegraph));
            setLineChart(linegraph);
            setGreScoreTableData(scoreList);
            setAwaSessionTableData(scoreList);
            setbarchat();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setGreScoreTableData(ArrayList<GreScoreModel> greScoreModels) {

        for (int j = 0; j < greScoreModels.size(); j++) {

            GreScoreModel greScoreModel = greScoreModels.get(j);

            TableRow tableRow = new TableRow(getContext());
            tableRow.setPadding(5, 0, 5, 0);
            TextView[] textViews = new TextView[4];
            for (int i = 0; i < 4; i++) {

                String text;
                if (i == 0) {
                    text = greScoreModel.getTestname();

                } else if (i == 1) {

                    text = greScoreModel.getVerbal();

                } else if (i == 2) {

                    text = greScoreModel.getQuant();

                } else {

                    text = greScoreModel.getTotal();

                }
                TableRow.LayoutParams trPara = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                trPara.weight = 1.0f;
                textViews[i] = new TextView(getContext());
                textViews[i].setText(text);
                textViews[i].setPadding(5, 5, 5, 5);
                textViews[i].setBackgroundResource(R.drawable.cell_shape);
                textViews[i].setLayoutParams(trPara);
                tableRow.addView(textViews[i]);
            }
            tlGreScore.addView(tableRow);
        }
    }

    private void setAwaSessionTableData(ArrayList<GreScoreModel> greScoreModels) {

        for (int j = 0; j < greScoreModels.size(); j++) {

            GreScoreModel greScoreModel = greScoreModels.get(j);

            TableRow tableRow = new TableRow(getContext());
            tableRow.setPadding(5, 0, 5, 0);
            TextView[] textViews = new TextView[3];
            for (int i = 0; i < 3; i++) {
                String text;
                if (i == 0) {
                    text = greScoreModel.getTestname();

                } else if (i == 1) {

                    text = greScoreModel.getStatus();

                } else {

                    text = greScoreModel.getScore();
                    Log.i("text", text);
                    if (text == null) {

                        text = "---";
                    }

                }
                TableRow.LayoutParams trPara = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                trPara.weight = 1.0f;
                textViews[i] = new TextView(getContext());
                textViews[i].setText(text);
                textViews[i].setPadding(5, 5, 5, 5);
                textViews[i].setBackgroundResource(R.drawable.cell_shape);
                textViews[i].setLayoutParams(trPara);
                tableRow.addView(textViews[i]);
            }
            tlAwaSession.addView(tableRow);
        }
    }

    private static String timeConversion(int totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;

        String hrStr = (hours < 10 ? "0" : "") + hours;
        String mnStr = (minutes < 10 ? "0" : "") + minutes;
        String secStr = (seconds < 10 ? "0" : "") + seconds;

        return hrStr + ":" + mnStr + ":" + secStr;
    }

    private ArrayList<String> barnames(ArrayList<GreScoreModel> scoreList) {

        ArrayList<String> xAxisLabel = new ArrayList<>();
        for (int i = 0; i < scoreList.size(); i++) {
            xAxisLabel.add(scoreList.get(i).getTestname());
        }
        return xAxisLabel;
    }

    private void setbarchat() {

        barentry = new ArrayList<>();

        barlabels = new ArrayList<>();

        addValuesToBarentry();

        bardataset = new BarDataSet(barentry, "Test's");

        bardata = new BarData(bardataset);
        barChart.setDescription(null);

        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardata.setBarWidth(0.7f);
        barChart.setVisibleXRangeMaximum(3);
        barChart.setHorizontalScrollBarEnabled(true);
        barChart.setPinchZoom(false);

        barChart.setData(bardata);
        YAxis y = barChart.getAxisLeft();
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setAxisMaximum(500);
        y.setAxisMinimum(0);
        y.setLabelCount(5);

        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(5);
        linechart.setPinchZoom(false);
        xAxis.setLabelCount(barnames(scoreList).size());

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return barnames(scoreList).get((int) value);
            }
        });

        barChart.animateY(3000);
    }

    public void addValuesToBarentry() {

        for (int i = 0; i < scoreList.size(); i++) {

            barentry.add(new BarEntry(i, Float.parseFloat(scoreList.get(i).getTotal())));
        }
    }

    private void setLineChart(JSONArray graphdata) throws JSONException {

        int[] colors = ColorTemplate.COLORFUL_COLORS;

        final ArrayList<String> sessions = new ArrayList<>();

        LineData data = new LineData();

        for (int i = 0; i < graphdata.length(); i++) {

            JSONArray graphmain = graphdata.getJSONArray(i);
            Log.i("graphmain", String.valueOf(graphmain));

            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < graphmain.length(); j++) {
                int sessionlenth = j + 1;
                sessions.add("session" + sessionlenth);
                String values = graphmain.optString(j);
                entries.add(new Entry(j, Integer.parseInt(values)));
                Log.i("values", values);
            }
            int test = i + 1;
            LineDataSet dataSet = new LineDataSet(entries, "Test" + test);
            dataSet.setColor(colors[i]);
            data.addDataSet(dataSet);

        }

        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return sessions.get((int) value);
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setValueFormatter(formatter);


        YAxis yAxisRight = linechart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis y = linechart.getAxisLeft();
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setAxisMaximum(100);
        y.setAxisMinimum(0);
        y.setLabelCount(5);
        y.setGranularity(1f);


        linechart.setData(data);
        linechart.setPinchZoom(false);
        linechart.setHorizontalScrollBarEnabled(true);
        linechart.setDescription(null);
        linechart.animateX(3000);
        linechart.invalidate();
    }
}


