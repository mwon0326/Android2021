package ac.kr.kpu.game.s2017180010.flyingbird.game.object;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Egg implements GameObject, BoxCollidable {
    private float x,y;
    private GameBitmap bitmap;

    public Egg(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.bitmap = new GameBitmap(R.mipmap.egg);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }

    public int getHeight()
    {
        return (int)(bitmap.getHeight() * GameView.MULTIPLIER);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }

    public void down(float amount, boolean isOverGround, float bottom)
    {
        this.y += amount;

        if (!isOverGround)
        {
            this.y = bottom;
        }
    }
}
