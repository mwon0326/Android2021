package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Block {
    private float x, y;
    private static Bitmap bitmap;

    public Block(float x, float y)
    {
        this.x = x;
        this.y = y;

        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.block);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public int getWidth()
    {
        return bitmap.getWidth();
    }

    public int getHeight()
    {
        return bitmap.getHeight();
    }

}
