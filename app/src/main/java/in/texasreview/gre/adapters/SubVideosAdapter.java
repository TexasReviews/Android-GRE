package in.texasreview.gre.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.SubVideosModel;
import in.texasreview.gre.R;
import in.texasreview.gre.interfaces.RecyclerviewOnclick;
import in.texasreview.gre.utils.AppConstants;

public class SubVideosAdapter extends RecyclerView.Adapter<SubVideosAdapter.MyViewHolder> {


    ArrayList<SubVideosModel> subcatBeansList;
    RecyclerviewOnclick listener;
    Context context;


    public SubVideosAdapter(Context context, ArrayList<SubVideosModel> subcatBeansList, RecyclerviewOnclick listener) {
        this.subcatBeansList = subcatBeansList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public SubVideosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_videos_item, parent, false);

        return new SubVideosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        SubVideosModel subVideosModel = subcatBeansList.get(position);
        holder.tvSubVideoName.setText((subVideosModel.getTitlelink()));
        holder.tvTestName.setText(subVideosModel.getTestname());

        if (subVideosModel.isIs_open()) {

            holder.cvEnable.setVisibility(View.VISIBLE);
            holder.imvLock.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.unlock));

            holder.thumbnail.initialize(AppConstants.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
                                                    final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(getYoutubeVideoIdFromUrl(subcatBeansList.get(position).getYoutube_uniqid()));
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {

                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            //need to release the loader!!!
                            youTubeThumbnailLoader.release();
                            holder.imvPlay.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView,
                                                     YouTubeThumbnailLoader.ErrorReason errorReason) {
                            //need to release the loader!!!
                            youTubeThumbnailLoader.release();
                            Log.e("InitializationResult", errorReason.name());
                        }
                    });
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView,
                                                    YouTubeInitializationResult youTubeInitializationResult) {

                    Log.e("InitializationResult", youTubeInitializationResult.name());
                    //handle error here
                }
            });

            try {

                if (subcatBeansList.get(position + 1).isIs_open() == false) {

                    holder.cvEnable.setVisibility(View.VISIBLE);
                    holder.llTest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed));
                    holder.imvLock.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.front_arrow_green));

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        } else {

            holder.cvDisable.setVisibility(View.VISIBLE);
            //clicling false
            holder.cvDisable.setEnabled(false);
            holder.llTest2.setEnabled(false);
            holder.rlSubVideosItem2.setEnabled(false);
            holder.tvSubVideoName2.setText((subVideosModel.getTitlelink()));
            holder.tvTestName2.setText(subVideosModel.getTestname());


        }
        holder.rlSubVideosItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(position, "video");
            }
        });

        holder.llTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(position, "test");
            }
        });

    }

    public static String getYoutubeVideoIdFromUrl(String inUrl) {
        if (inUrl.toLowerCase().contains("youtu.be")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1);
        }
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(inUrl);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return subcatBeansList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlSubVideosItem)
        RelativeLayout rlSubVideosItem;

        @BindView(R.id.rlSubVideosItem2)
        RelativeLayout rlSubVideosItem2;

        @BindView(R.id.tvSubVideoName)
        TextView tvSubVideoName;

        @BindView(R.id.tvTestName)
        TextView tvTestName;

        @BindView(R.id.llTest)
        LinearLayout llTest;

        @BindView(R.id.llTest2)
        LinearLayout llTest2;


        @BindView(R.id.imvLock)
        ImageView imvLock;

        @BindView(R.id.cvEnable)
        CardView cvEnable;

        @BindView(R.id.cvDisable)
        CardView cvDisable;


        @BindView(R.id.tvSubVideoName2)
        TextView tvSubVideoName2;

        @BindView(R.id.tvTestName2)
        TextView tvTestName2;

        @BindView(R.id.thumbnail)
        YouTubeThumbnailView thumbnail;

        @BindView(R.id.imvPlay)
        ImageView imvPlay;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
