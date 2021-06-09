package ac.kr.kpu.game.s2017180010.flyingbird.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class ImageObject implements GameObject {
    private Bitmap bitmap;
    private RectF dstRect = new RectF();

    public ImageObject(int left, int top, int right, int bottom, int resId)
    {
        bitmap = GameBitmap.load(resId);
        dstRect.set(left, top, right, bottom);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
