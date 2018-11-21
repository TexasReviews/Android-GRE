package in.texasreview.gre.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.AnswersModel;
import in.texasreview.gre.R;
import in.texasreview.gre.interfaces.LikeOrUnlikeOnclick;

/**
 * Created by User on 17-10-2018.
 */


public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.MyViewHolder> {


    ArrayList<AnswersModel.AnsBean> list;
    Context context;

    LikeOrUnlikeOnclick likeOrUnlikeOnclick;


    public AnswersAdapter(Context context, ArrayList<AnswersModel.AnsBean> list, LikeOrUnlikeOnclick likeOrUnlikeOnclick) {
        this.list = list;
        this.context = context;
        this.likeOrUnlikeOnclick = likeOrUnlikeOnclick;

    }

    @Override
    public AnswersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answers_item, parent, false);

        return new AnswersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnswersAdapter.MyViewHolder holder, final int position) {

        int mypostion = position+1;
        AnswersModel.AnsBean ansewers = list.get(position);
        holder.tvqusCount.setText(""+mypostion+".");
        holder.tvQuestion.setText(ansewers.getAns());
        holder.tvNumOfLikes.setText(String.valueOf(ansewers.getLike()));
        holder.tvNumOfDisLikes.setText(String.valueOf(ansewers.getUnlike()));
        holder.tvFullName.setText(ansewers.getUserdata().getFullname());
        holder.tvTime.setText(ansewers.getQdate());

        holder.imvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likeOrUnlikeOnclick.onLikeClicked(position);

            }
        });
        holder.imvDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likeOrUnlikeOnclick.onUnlikeClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvqusCount)
        TextView tvqusCount;

        @BindView(R.id.tvQuestion)
        TextView tvQuestion;

        @BindView(R.id.tvNumOfLikes)
        TextView tvNumOfLikes;

        @BindView(R.id.tvNumOfDisLikes)
        TextView tvNumOfDisLikes;

        @BindView(R.id.tvFullName)
        TextView tvFullName;

        @BindView(R.id.tvTime)
        TextView tvTime;

        @BindView(R.id.imvDisLike)
        ImageView imvDisLike;

        @BindView(R.id.imvLike)
        ImageView imvLike;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}

