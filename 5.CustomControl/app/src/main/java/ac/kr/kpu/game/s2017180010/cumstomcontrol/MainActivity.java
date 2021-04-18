package ac.kr.kpu.game.s2017180010.cumstomcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.d(TAG, "onConfigChange : " + newConfig);
        super.onConfigurationChanged(newConfig);
    }
}