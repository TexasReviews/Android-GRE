package in.texasreview.gre.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.VideosModel;
import in.texasreview.gre.R;
import in.texasreview.gre.interfaces.RecyclerviewOnclick;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {


    private ArrayList<VideosModel> videosList;

    RecyclerviewOnclick  listener;

    public VideosAdapter(Context context, ArrayList<VideosModel> videosList,RecyclerviewOnclick listener ) {
        this.videosList = videosList;
        this.listener = listener;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.videos_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        VideosModel videosModel = videosList.get(position);
        holder.tvNumOfSubVideos.setText(String.valueOf(videosModel.getTotal_vedios()));
        holder.tvVideoName.setText(videosModel.getTitle());
        holder.tvNumOfQues.setText(String.valueOf(videosModel.getTotal_test_que()+" Questions"));

        holder.rlVideosItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(position,null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlVideosItem)
        RelativeLayout rlVideosItem;

        @BindView(R.id.tvNumOfSubVideos)
        TextView tvNumOfSubVideos;

        @BindView(R.id.tvVideoName)
        TextView tvVideoName;

        @BindView(R.id.tvNumOfQues)
        TextView tvNumOfQues;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
