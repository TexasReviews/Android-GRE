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
import in.texasreview.gre.Models.GreScoreModel;
import in.texasreview.gre.Models.ReviewTableModel;
import in.texasreview.gre.R;

/**
 * Created by User on 15-11-2018.
 */

public class GreScoreAdapter extends RecyclerView.Adapter<GreScoreAdapter.MyViewHolder> {

    ArrayList<GreScoreModel> list;
    Context context;


    public GreScoreAdapter(Context context, ArrayList<GreScoreModel> list) {
        this.list = list;

        this.context = context;
    }

    @Override
    public GreScoreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grescore_item, parent, false);


        return new GreScoreAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GreScoreAdapter.MyViewHolder holder, final int position) {

        GreScoreModel greScoreModel = list.get(position);

        holder.tvTestName.setText(greScoreModel.getTestname());
        holder.tvVerbal.setText(greScoreModel.getVerbal());
        holder.tvQuant.setText(greScoreModel.getQuant());
        holder.tvTotal.setText(greScoreModel.getTotal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.tvTestName)
        TextView tvTestName;

        @BindView(R.id.tvVerbal)
        TextView tvVerbal;

        @BindView(R.id.tvQuant)
        TextView tvQuant;

        @BindView(R.id.tvTotal)
        TextView tvTotal;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
