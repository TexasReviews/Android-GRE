package in.texasreview.gre.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.R;
import in.texasreview.gre.utils.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyYouTubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    String videoUrl;

    @BindView(R.id.youtubeplayerview)
    YouTubePlayerView youtubeplayerview;

    private static final int RECOVERY_REQUEST = 1;

    @BindView(R.id.imvBack)
    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_you_tube_player);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if (intent != null){
            videoUrl=intent.getStringExtra(AppConstants.VIDEOKEY);
        }
        youtubeplayerview.initialize(AppConstants.YOUTUBE_API_KEY, this);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {


        if (!wasRestored) {
            youTubePlayer.loadVideo(videoUrl);
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }

    }
}
