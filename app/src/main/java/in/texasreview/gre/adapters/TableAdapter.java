package in.texasreview.gre.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.ReviewTableModel;
import in.texasreview.gre.R;

/**
 * Created by User on 08-11-2018.
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {

    ArrayList<ReviewTableModel> list;
    Context context;


    public TableAdapter(Context context, ArrayList<ReviewTableModel> list) {
        this.list = list;

        this.context = context;
    }

    @Override
    public TableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        return new TableAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (list.get(position).isHeader()) {

            holder.llitem.setBackgroundColor(Color.parseColor("#3F51B5"));

            holder.tv1.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tv2.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tv3.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tv4.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tv5.setTextColor(Color.parseColor("#FFFFFF"));


        } else {

            holder.llitem.setBackgroundColor(Color.parseColor("#FFFFFF"));

            holder.tv1.setTextColor(Color.parseColor("#000000"));
            holder.tv2.setTextColor(Color.parseColor("#000000"));
            holder.tv3.setTextColor(Color.parseColor("#000000"));
            holder.tv4.setTextColor(Color.parseColor("#000000"));
            holder.tv5.setTextColor(Color.parseColor("#000000"));

        }

        ReviewTableModel reviewTableModel = list.get(position);
        holder.tv1.setText((reviewTableModel.getCategory()));
        holder.tv2.setText((reviewTableModel.getWrongAnswers()));
        holder.tv3.setText((reviewTableModel.getCorrectAnswers()));
        holder.tv4.setText((reviewTableModel.getTotalQuestions()));
        holder.tv5.setText((reviewTableModel.getTotalTime()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llitem)
        LinearLayout llitem;

        @BindView(R.id.tv1)
        TextView tv1;

        @BindView(R.id.tv2)
        TextView tv2;

        @BindView(R.id.tv3)
        TextView tv3;

        @BindView(R.id.tv4)
        TextView tv4;

        @BindView(R.id.tv5)
        TextView tv5;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
