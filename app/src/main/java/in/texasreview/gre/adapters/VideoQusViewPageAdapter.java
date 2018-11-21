package in.texasreview.gre.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.QuestionsModel;
import in.texasreview.gre.R;
import in.texasreview.gre.fragments.Questions;
import in.texasreview.gre.interfaces.OnVideoTestAnsSelected;

/**
 * Created by User on 12-10-2018.
 */

public class VideoQusViewPageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {


    Context context;
    ArrayList<QuestionsModel> quslist = new ArrayList<>();
    LayoutInflater layoutInflater;

    @BindView(R.id.tvQus)
    TextView tvQus;

    @BindView(R.id.gr)
    RadioGroup rg;

    OnVideoTestAnsSelected onVideoTestAnsSelected;


    public VideoQusViewPageAdapter(Context context, ArrayList<QuestionsModel> quslist, OnVideoTestAnsSelected onVideoTestAnsSelected) {
        this.context = context;
        this.quslist = quslist;
        this.onVideoTestAnsSelected = onVideoTestAnsSelected;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return quslist.size();

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;


    }

    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        final View itemView = layoutInflater.inflate(R.layout.video_exam_item, container, false);
        ButterKnife.bind(this, itemView);
        final QuestionsModel questionsModel = quslist.get(position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvQus.setText(Html.fromHtml(questionsModel.getQusname(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvQus.setText(Html.fromHtml(questionsModel.getQusname()));
        }

        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);

        for (int i = 0; i < questionsModel.getOptions().size(); i++) {

            RadioButton rdbtn = new RadioButton(context);
            rdbtn.setGravity(View.TEXT_ALIGNMENT_CENTER);
            rdbtn.setId(i);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                rdbtn.setText(Html.fromHtml(questionsModel.getOptions().get(i).getName(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                rdbtn.setText(Html.fromHtml(questionsModel.getOptions().get(i).getName()));
            }
            rg.setLayoutParams(layoutParams);
            rg.addView(rdbtn);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                Log.i("index", String.valueOf(index));
                Log.i("isAns", quslist.get(position).getOptions().get(index).getIs_answer());
                if (quslist.get(position).getOptions().get(index).getIs_answer().equalsIgnoreCase("1")) {

                    onVideoTestAnsSelected.isTreue(true);
                } else {

                    onVideoTestAnsSelected.isTreue(false);
                }

            }
        });

        container.addView(itemView);
        //listening to image click
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
