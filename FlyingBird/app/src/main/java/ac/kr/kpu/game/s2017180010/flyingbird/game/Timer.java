package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Timer implements GameObject {
    private int right, top;
    private boolean isDraw;
    private final Bitmap bitmap;
    private boolean stopTimer;
    private float shootingTime;
    private final int SHOOTING_END_TIME = 10;
    private RectF dst = new RectF();
    private Rect src = new Rect();

    public Timer(int right, int top, boolean isDraw)
    {
        bitmap = GameBitmap.load(R.mipmap.number_red);
        this.right = right;
        this.top = top;
        this.isDraw = isDraw;
        stopTimer = false;
        shootingTime = 10.f;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();

        if (game.shootingMode)
        {
            shootingTime -= game.frameTime;
            if (shootingTime <= 0)
            {
                isDraw = false;
                stopTimer = true;
                shootingTime = 10.f;
            }
        }
    }

    public void setStopTimer(boolean stopTimer)
    {
        this.stopTimer = stopTimer;
    }

    public void setDraw(boolean isDraw)
    {
        this.isDraw = isDraw;
    }

    public boolean getStopTimer()
    {
        return stopTimer;
    }

    @Override
    public void draw(Canvas canvas) {
        if (isDraw)
        {
            int value = (int)this.shootingTime;
            int nw = bitmap.getWidth() / 10;
            int nh = bitmap.getHeight();
            int x = right;
            int dw = (int) (nw * GameView.MULTIPLIER);
            int dh = (int) (nh * GameView.MULTIPLIER);

            while (value > 0) {
                int digit = value % 10;
                src.set(digit * nw, 0, (digit + 1) * nw, nh);
                x -= dw;
                dst.set(x, top, x + dw, top + dh);
                canvas.drawBitmap(bitmap, src, dst, null);
                value /= 10;
            }
        }
    }
}
