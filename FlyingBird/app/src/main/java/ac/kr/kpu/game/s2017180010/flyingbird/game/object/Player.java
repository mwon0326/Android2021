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

public class Player implements GameObject, BoxCollidable {
    private float x, y;
    private GameBitmap playerBitmap;
    private int eggCount;
    private int MAX_EGG_COUNT = 5;
    private GameBitmap playerFaceBitmap;
    private float bottom;
    private final float GROUND;
    private boolean isOverGround;
    private float fireTime;
    private float FIRE_INTERVAL = 1.0f / 2.0f;
    public boolean isDown;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.playerBitmap = new GameBitmap(R.mipmap.egg);
        this.playerFaceBitmap = new GameBitmap(R.mipmap.bird_face);
        bottom = this.y;
        GROUND = this.y;
        isOverGround = false;
        this.fireTime = 0.0f;
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();

        if (MainScene.scene.shootingMode) {
            fireTime += game.frameTime;
            if (fireTime >= FIRE_INTERVAL) {
                fireBullet();
                fireTime -= FIRE_INTERVAL;
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        playerBitmap.draw(canvas, x, y);
        playerFaceBitmap.draw(canvas, x, y);
    }

    public Egg layEgg() {
        if (eggCount < MAX_EGG_COUNT)
        {
            Egg egg = new Egg(this.x, this.y);
            MainScene.scene.add(MainScene.Layer.egg, egg);
            y -= egg.getHeight();
            eggCount += 1;

            return egg;
        }
        return null;
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
        MainScene.scene.add(MainScene.Layer.bullet, bullet);
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

            if (!MainScene.scene.shootingMode) {
                MainScene.scene.blockGenerator.obstacleInit();
                MainScene.scene.blockGenerator.obstacleSet();
            }
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

    public int getEggCount() { return eggCount; }
}
