package ac.kr.kpu.game.s2017180010.flyingbird.game.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Score implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;
    private int score;
    private RectF dst = new RectF();
    private Rect src = new Rect();

    public Score(int right, int top)
    {
        bitmap = GameBitmap.load(R.mipmap.number);
        this.right = right;
        this.top = top;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void addScore(int amount)
    {
        this.score += amount;
    }

    public int getScore()
    {
        return this.score;
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.score;
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
