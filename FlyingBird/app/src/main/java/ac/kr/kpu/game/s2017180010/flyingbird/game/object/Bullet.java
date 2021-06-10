package ac.kr.kpu.game.s2017180010.flyingbird.game.object;

import android.graphics.Canvas;
import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BaseGame;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.game.scene.MainScene;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

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

        if (x >= GameView.view.getWidth()) {
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
