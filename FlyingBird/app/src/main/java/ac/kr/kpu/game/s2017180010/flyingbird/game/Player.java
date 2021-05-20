package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameBitmap;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Player implements GameObject, BoxCollidable {
    private float x, y;
    private float tx, ty;
    private float speed;
    private GameBitmap playerBitmap;
    private int eggCount;
    private int MAX_EGG_COUNT = 5;
    private GameBitmap playerFaceBitmap;
    private float bottom;
    private final float GROUND;
    private boolean isOverGround;
    private float fireTime;
    private float FIRE_INTERVAL = 1.0f / 7.5f;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = 0;
        this.playerBitmap = new GameBitmap(R.mipmap.egg);
        this.playerFaceBitmap = new GameBitmap(R.mipmap.bird_face);
        bottom = this.y;
        GROUND = this.y;
        isOverGround = false;
        this.fireTime = 0.0f;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();

        fireTime += game.frameTime;
        if (fireTime >= FIRE_INTERVAL) {
            fireBullet();
            fireTime -= FIRE_INTERVAL;
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        playerBitmap.draw(canvas, x, y);
        playerFaceBitmap.draw(canvas, x, y);
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

    public int getWidth()
    {
        return (int)(playerBitmap.getWidth() * GameView.MULTIPLIER);
    }

    public int getHeight()
    {
        return (int)(playerBitmap.getHeight() * GameView.MULTIPLIER);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        playerBitmap.getBoundingRect(x, y, rect);
    }

    public void changeEggCount(int amount)
    {
        eggCount -= amount;
        bottom -= amount * getHeight();
    }

    public void fireBullet()
    {
        Bullet bullet = new Bullet(this.x, this.y, 1000);
        MainGame game = MainGame.get();
        game.add(MainGame.Layer.bullet, bullet);
    }


    public void down(float amount)
    {
        y += amount;
        bottom += amount;

        if (bottom >= GROUND)
        {
            y = GROUND - (this.getHeight() * eggCount);
            bottom = GROUND;
            isOverGround = false;

            MainGame game = MainGame.get();
            game.obstacle.obstacleInit();
            game.obstacle.obstacleSet();
        }
    }
    public boolean getIsOverGround()
    {
        return isOverGround;
    }

    public void setIsOverGround(boolean isOverGround)
    {
        this.isOverGround = isOverGround;
    }
}
