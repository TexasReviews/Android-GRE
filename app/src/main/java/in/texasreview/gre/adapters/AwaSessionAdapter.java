package in.texasreview.gre.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.GreScoreModel;
import in.texasreview.gre.R;

/**
 * Created by User on 15-11-2018.
 */

public class AwaSessionAdapter extends RecyclerView.Adapter<AwaSessionAdapter.MyViewHolder> {

    ArrayList<GreScoreModel> list;
    Context context;


    public AwaSessionAdapter(Context context, ArrayList<GreScoreModel> list) {
        this.list = list;

        this.context = context;
    }

    @Override
    public AwaSessionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.awa_session_item, parent, false);


        return new AwaSessionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AwaSessionAdapter.MyViewHolder holder, final int position) {

        GreScoreModel greScoreModel = list.get(position);

        holder.tvTestName.setText(greScoreModel.getTestname());
        holder.tvStatus.setText(greScoreModel.getStatus());
        holder.tvScore.setText(greScoreModel.getScore());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvTestName)
        TextView tvTestName;

        @BindView(R.id.tvStatus)
        TextView tvStatus;

        @BindView(R.id.tvScore)
        TextView tvScore;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}

