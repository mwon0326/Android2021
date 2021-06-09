package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BaseGame;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class Bullet implements GameObject, BoxCollidable {
    private float x, y;
    private final GameBitmap bitmap;
    private float speed;

    public Bullet(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = -speed;

        this.bitmap = new GameBitmap(R.mipmap.bullet);
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        x -= speed * game.frameTime;

        if (x < 0) {
            MainScene.scene.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }
}
