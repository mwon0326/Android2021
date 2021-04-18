package ac.kr.kpu.game.s2017180010.flyingbird.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.Sound;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;


public class GameView extends View {
    // ArrayList<Ball> balls = new ArrayList<>();
    private long lastFrame;
    public static GameView view;
    private static final String TAG = GameView.class.getSimpleName();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        Sound.init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        MainGame game = MainGame.get();
        boolean justInitialized = game.initResources();
        if (justInitialized)
            requestCallback();
    }

    private void update() {
        MainGame game = MainGame.get();
        game.update();
        invalidate();
    }

    private void requestCallback(){
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0)
                {
                    lastFrame = time;
                }
                MainGame game = MainGame.get();
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
                update();
                lastFrame = time;
                requestCallback();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MainGame game = MainGame.get();
        game.draw(canvas);
        //Log.d(TAG, "Drawing at : " + x + ", " + y + ", ft = " + frameTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainGame game = MainGame.get();
        return game.onTouchEvent(event);
    }
}
