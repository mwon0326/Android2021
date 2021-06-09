package ac.kr.kpu.game.s2017180010.flyingbird.game.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Block implements GameObject, BoxCollidable {
    private float x, y;
    private static GameBitmap bitmap;
    private boolean isDraw;
    public Block(float x, float y)
    {
        this.x = x;
        this.y = y;

        if (bitmap == null) {
            bitmap = new GameBitmap(R.mipmap.block);
        }

        this.isDraw = false;
    }

    @Override
    public void update() {

    }

    public void draw(Canvas canvas) {
        if (isDraw)
            bitmap.draw(canvas, x, y);
    }

    public int getWidth()
    {
        return (int)(bitmap.getWidth() * GameView.MULTIPLIER);
    }

    public int getHeight()
    {
        return (int)(bitmap.getHeight() * GameView.MULTIPLIER);
    }

    public void updatePostion(float dx, float dy)
    {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void setIsDraw(boolean isDraw)
    {
        this.isDraw = isDraw;
    }

    public boolean getIsDraw()
    {
        return isDraw;
    }

    public float getX()
    {
        return x;
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }
}
