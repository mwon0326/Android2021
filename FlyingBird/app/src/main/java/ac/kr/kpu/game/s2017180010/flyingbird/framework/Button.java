package ac.kr.kpu.game.s2017180010.flyingbird.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class Button implements GameObject {
    private int x, y;
    private int width, height;
    private Bitmap defaultImage;
    private Bitmap pressImage;
    private Bitmap preButton;
    private RectF dstRect = new RectF();

    public Button(int x, int y, int width, int height, int defaultId, int pressId)
    {
        this.x = x - (width / 2);
        this.y = y - (width / 2);
        this.width = width;
        this.height = height;
        defaultImage = GameBitmap.load(defaultId);
        pressImage = GameBitmap.load(pressId);
        preButton = defaultImage;
        dstRect.set(this.x, this.y, x + (width / 2), y + (height / 2));
    }

    public void ChangeButton()
    {
        if (preButton == defaultImage)
            preButton = pressImage;
        else
            preButton = defaultImage;
    }

    public boolean touchButton(float x, float y)
    {
        return x >= dstRect.left && y >= dstRect.top && x <= dstRect.right && y <= dstRect.bottom;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(preButton, null, dstRect, null);
    }
}
