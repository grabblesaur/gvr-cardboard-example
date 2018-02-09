package bizapp.ru.md3260player;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.google.vr.sdk.base.GvrActivity;
import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private VrVideoView mVrVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoLoaderTask mBackgroundVideoLoaderTask = new VideoLoaderTask();
        mBackgroundVideoLoaderTask.execute();
        initViews();
    }

    private void initViews() {
        mVrVideoView = findViewById(R.id.video_view);
        mVrVideoView.setEventListener(new ActivityEventListener());
//        mVrVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mVrVideoView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVrVideoView.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVrVideoView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        mVrVideoView.shutdown();
        super.onDestroy();
    }

    private class ActivityEventListener extends VrVideoEventListener {
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();
        }

        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
        }

        @Override
        public void onClick() {
            super.onClick();
        }

        @Override
        public void onNewFrame() {
            super.onNewFrame();
        }

        @Override
        public void onCompletion() {
//            super.onCompletion();
            mVrVideoView.seekTo(0);
        }
    }

    class VideoLoaderTask extends AsyncTask<Void, Void, Boolean> {
        private String TAG = VideoLoaderTask.class.getName();

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
                mVrVideoView.loadVideoFromAsset("congo.mp4", options);
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: " + e.getMessage());
            }
            return true;
        }
    }

}
