package in.texasreview.gre.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by User on 12-11-2018.
 */

public class DRChronometer extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "DRChronometer";
    private long mBase;
    private boolean mVisible;
    private boolean mStarted;
    private boolean mRunning;
    private boolean mLogged;
    private String mDefaultFormat = "%02d:%02d:%02d";

    TextView textView;

    /**
     * Initialize this Chronometer object.
     * Sets the base to the current time.
     */
    public DRChronometer(Context context, TextView textView) {
        this(context, null, 0);
        this.textView = textView;
    }

    /**
     * Initialize with standard view layout information.
     * Sets the base to the current time.
     */
    public DRChronometer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Initialize with standard view layout information and style.
     * Sets the base to the current time.
     */
    public DRChronometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mBase = SystemClock.elapsedRealtime();
        updateText(mBase);
    }

    /**
     * Set the time that the count-up timer is in reference to.
     *
     * @param base Use the {@link SystemClock#elapsedRealtime} time base.
     */
    public void setBase(long base) {
        mBase = base;
        updateText(SystemClock.elapsedRealtime());
    }

    /**
     * Return the base time as set through {@link #setBase}.
     */
    public long getBase() {
        return mBase;
    }

    /**
     * Start counting up.  This does not affect the base as set from {@link #setBase}, just
     * the view display.
     *
     * Chronometer works by regularly scheduling messages to the handler, even when the
     * Widget is not visible.  To make sure resource leaks do not occur, the user should
     * make sure that each start() call has a reciprocal call to {@link #stop}.
     */
    public void start() {
        mStarted = true;
        updateRunning();
    }

    /**
     * Stop counting up.  This does not affect the base as set from {@link #setBase}, just
     * the view display.
     *
     * This stops the messages to the handler, effectively releasing resources that would
     * be held as the chronometer is running, via {@link #start}.
     */
    public void stop() {
        mStarted = false;
        updateRunning();
        long now = SystemClock.elapsedRealtime();
        setBase(now);
        updateText(now);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        mVisible = visibility == VISIBLE;
        updateRunning();
    }

    private void updateText(long now) {
        long seconds = (now - mBase) / 1000;
        int hh = (int)(seconds / 3600);
        int mm = (int)((seconds % 3600) / 60);
        int ss = (int)(seconds % 60);

        String text = String.format(Locale.US, mDefaultFormat, hh, mm, ss);
        Log.i("textView",text);
        setText(text);
    }

    private void updateRunning() {
        boolean running = mVisible && mStarted;
        if (running != mRunning) {
            if (running) {
                updateText(SystemClock.elapsedRealtime());
                mHandler.sendMessageDelayed(Message.obtain(), 1000);
            }
            mRunning = running;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message m) {
            if (mStarted) {
                updateText(SystemClock.elapsedRealtime());
                sendMessageDelayed(Message.obtain(), 1000);
            }
        }
    };
}
