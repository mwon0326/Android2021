package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class Player implements GameObject {
    private float x, y;
    private float tx, ty;
    private float speed;
    private GameBitmap playerBitmap;
    private int eggCount;
    private int MAX_EGG_COUNT = 5;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.playerBitmap = new GameBitmap(R.mipmap.bird);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        playerBitmap.draw(canvas, x, y);
    }

    public void layEgg() {
        if (eggCount < MAX_EGG_COUNT)
        {
            Egg egg = new Egg(this.x, this.y);
            MainGame game = MainGame.get();
            game.add(MainGame.Layer.egg, egg);
            y -= egg.getHeight();
            eggCount += 1;
        }
    }
}
