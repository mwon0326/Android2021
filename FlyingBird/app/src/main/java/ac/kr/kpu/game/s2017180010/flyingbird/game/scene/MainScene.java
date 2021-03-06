package ac.kr.kpu.game.s2017180010.flyingbird.game.scene;

import android.media.SoundPool;
import android.view.MotionEvent;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Button;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Scene;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.ScrollBackground;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Sound;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.game.ctroller.Collision;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.BlockGenerator;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Egg;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Player;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Score;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Timer;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class MainScene extends Scene {
    public Player player;

    public int GROUND = 0;
    private static final int GRAVITY = 9;
    public boolean shootingMode = true;
    public int combo = 0;
    public static Score score;
    public Timer shootingTimer;
    public int obstacleWidth = 0, obstacleHeight = 0;
    private Button pauseButton;
    private SoundPool bgm;
    public BlockGenerator blockGenerator;
    public boolean isTouch;

    public enum Layer{
        bg, obstacle, bullet, player, egg, ui, controller, LAYER_COUNT
    }

    public static MainScene scene;

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return objectsAt(layer.ordinal());
    }

    @Override
    public void start() {
        scene = this;
        super.start();

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        GROUND = h - 300;

        player = new Player(200, GROUND);
        add(Layer.player, player);

        blockGenerator = new BlockGenerator(300, h - 300, 200);
        add(Layer.obstacle, blockGenerator);

        ScrollBackground sky = new ScrollBackground(R.mipmap.bg_sky, 100);
        add(Layer.bg, sky);
        ScrollBackground cloud1 = new ScrollBackground(R.mipmap.bg_cloud_01, 120);
        add(Layer.bg, cloud1);
        ScrollBackground cloud2 = new ScrollBackground(R.mipmap.bg_cloud_02, 150);
        add(Layer.bg, cloud2);
        ScrollBackground cloud3 = new ScrollBackground(R.mipmap.bg_cloud_03, 180);
        add(Layer.bg, cloud3);

        ScrollBackground ground = new ScrollBackground(R.mipmap.bg_ground, 100);
        add(Layer.bg, ground);

        int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        shootingTimer = new Timer(w / 2, 300, false);
        add(Layer.ui, shootingTimer);

        add(Layer.controller, new Collision(player));

        pauseButton = new Button(100, 100, 100, 100,
                R.mipmap.pause_button_default, R.mipmap.pause_button_press);
        add(Layer.ui, pauseButton);

        shootingMode = false;
        isTouch = false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)
        if (action == MotionEvent.ACTION_DOWN)
        {
            if (pauseButton.touchButton(event.getX(), event.getY())) {
                pauseButton.setPress();
                return true;
            }

            if (!shootingMode && !player.getIsOverGround()) {
                Egg egg = player.layEgg();
            }
            if (shootingMode && isTouch)
            {
                Egg egg = player.layEgg();

                remove(egg);
                player.changeEggCount(1);
                player.setIsOverGround(true);
            }

            Sound.play(R.raw.effect);
            return true;
        }
        else if (action == MotionEvent.ACTION_UP)
        {
            pauseButton.setDefault();
            if (pauseButton.touchButton(event.getX(), event.getY())) {
                MainGame.get().push(new PauseScene());
            }
            return true;
        }
        return false;
    }

    public void setObstacleSize(int width, int height)
    {
        obstacleWidth = width;
        obstacleHeight = height;
    }

}
