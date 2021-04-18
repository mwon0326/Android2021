package ac.kr.kpu.game.s2017180010.cumstomcontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint paint = new Paint();
    private Rect rect;

    public MyView(Context context, AttributeSet set) {
        super(context, set);
        paint.setColor(0xff0044ff);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouch : " + event);
        //return super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //ViewGroup.LayoutParams lp = getLayoutParams();
        int l = getPaddingLeft();
        int t = getPaddingTop();
        int w = getWidth() - getPaddingRight();
        int h = getHeight() - getPaddingBottom();
        rect.set(0,0, w, h);
        Log.d(TAG, "drawing : " + rect);
        canvas.drawRect(rect, paint);
    }
}
