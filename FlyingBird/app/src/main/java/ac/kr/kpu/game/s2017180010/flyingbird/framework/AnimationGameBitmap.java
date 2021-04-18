package ac.kr.kpu.game.s2017180010.flyingbird.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class AnimationGameBitmap extends GameBitmap {
    private Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long craetedOn;
    private final float framesPerSecond;
    private final int frameCount;
    private int frameIndex;

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount)
    {
        bitmap = GameBitmap.load(resId);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();

        if (frameCount == 0)
            frameCount = imageWidth / imageHeight;

        frameWidth = imageWidth / frameCount;
        craetedOn = System.currentTimeMillis();
        frameIndex = 0;
        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;
    }

    public void draw(Canvas canvas, float x, float y)
    {
        int elapsed = (int)(System.currentTimeMillis() - craetedOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % 24;

        int fw = frameWidth;
        int h = imageHeight;
        int hw = fw / 2 * 4;
        int hh = h / 2 * 4;
        Rect src = new Rect(fw * frameIndex, 0, fw * frameIndex + fw, h);
        RectF dst = new RectF(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    public int getWidth()
    {
        return frameWidth * 4;
    }

    public int getHeight()
    {
        return imageHeight * 4;
    }
}
