package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Egg implements GameObject {
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
}
