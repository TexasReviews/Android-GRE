package in.texasreview.gre.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.CreatedQuestionsModel;
import in.texasreview.gre.Models.SubVideosModel;
import in.texasreview.gre.R;
import in.texasreview.gre.activities.Answers;
import in.texasreview.gre.interfaces.LikeOrUnlikeOnclick;
import in.texasreview.gre.interfaces.RecyclerviewOnclick;
import in.texasreview.gre.utils.AppConstants;

public class CreateQusAdapter extends RecyclerView.Adapter<CreateQusAdapter.MyViewHolder> {


    ArrayList<CreatedQuestionsModel.QuestionsBean> list;
    Context context;
    LikeOrUnlikeOnclick likeOrUnlikeOnclick;


    public CreateQusAdapter(Context context, ArrayList<CreatedQuestionsModel.QuestionsBean> list,LikeOrUnlikeOnclick likeOrUnlikeOnclick) {
        this.list = list;
        this.context = context;
        this.likeOrUnlikeOnclick = likeOrUnlikeOnclick;

    }

    @Override
    public CreateQusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_question_item, parent, false);

        return new CreateQusAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CreateQusAdapter.MyViewHolder holder, final int position) {

        final CreatedQuestionsModel.QuestionsBean questions = list.get(position);
        holder.tvQuestion.setText(questions.getQuestion());
        holder.tvNumOfAns.setText(String.valueOf(questions.getAns_count())+"Answers");
        holder.tvNumOfLikes.setText(String.valueOf(questions.getLike()));
        holder.tvNumOfDisLikes.setText(String.valueOf(questions.getUnlike()));
        holder.tvMailid.setText(questions.getUserdata().getFullname());
        holder.tvTime.setText(questions.getDate());
        holder.tvNumOfAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent answers = new Intent(context, Answers.class);
                answers.putExtra(AppConstants.QUSTIONIDKEY,questions.getId());
                context.startActivity(answers);

            }
        });

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

        @BindView(R.id.tvQuestion)
        TextView tvQuestion;

        @BindView(R.id.tvNumOfAns)
        TextView tvNumOfAns;

        @BindView(R.id.tvNumOfLikes)
        TextView tvNumOfLikes;

        @BindView(R.id.tvNumOfDisLikes)
        TextView tvNumOfDisLikes;

        @BindView(R.id.tvMailid)
        TextView tvMailid;

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
