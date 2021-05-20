package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;


public class MainGame {
    static MainGame instance;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    public float frameTime;
    private static final int BALL_COUNT = 10;
    private boolean initialized;
    private ArrayList<Block> blocks;
    private Player player;
    public Obstacle obstacle;
    private int obstacleWidth = 0, obstacleHeight = 0;
    private int GROUND = 0;
    private static final int GRAVITY = 9;
    private boolean shootingMode = false;

    public static MainGame get(){
        if (instance == null){
            instance = new MainGame();
        }
        return instance;
    }

    public void add(Layer layer, GameObject gameObject)
    {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);
            }
        });
    }

    public void remove(Layer layer, GameObject gameObject)
    {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.remove(gameObject);
            }
        });
    }

    ArrayList<ArrayList<GameObject>> layers;
    public enum Layer{
        bg, obstacle, bullet, player, egg, LAYER_COUNT
    }
    public boolean initResources() {
        if (initialized)
            return false;

        Random rand = new Random();
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        obstacle = new Obstacle(300, h - 300, 100);
        add(Layer.obstacle, obstacle);

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

        GROUND = h - 300;
        player = new Player(200, GROUND);
        add(Layer.player, player);

        initialized = true;
        shootingMode = false;

        return true;
    }

    private void initLayers(int layerCount)
    {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update()
    {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }

        String key;
        Block block;
        ArrayList<GameObject> eggs = layers.get(Layer.egg.ordinal());
        ArrayList<GameObject> bullets = layers.get(Layer.bullet.ordinal());

        /*for (int i = 0; i < obstacleWidth; i++)
        {
            boolean collided = false;
            for (int j = 0; j < obstacleHeight; j++)
            {
                key = Integer.toString(i) + Integer.toString(j);
                block = obstacle.getBlock(key);

                for (GameObject object: bullets)
                {
                    Bullet bullet = (Bullet) object;
                    if (CollisionHelper.collideSide(block, bullet))
                    {
                        remove(Layer.bullet, bullet);
                        block.setIsDraw(false);
                        collided = true;
                        break;
                    }
                }
                if (collided)
                    break;
            }
            if (collided)
                break;
        }*/

        if (player.getIsOverGround())
        {
            key = Integer.toString(obstacleWidth - 1) + Integer.toString(obstacleHeight - 1);
            block = obstacle.getBlock(key);

            if (CollisionHelper.overBlock(block, player)) {
                player.down(frameTime * 800);
                int count = 0;
                for (GameObject object: eggs) {
                    Egg egg = (Egg) object;
                    egg.down(frameTime * 800, player.getIsOverGround(),
                            GROUND - (count * egg.getHeight()));
                    count += 1;
                }
            }
        }
        else
        {
            key = '0' + Integer.toString(obstacleHeight - 1);
            block = obstacle.getBlock(key);

            if (CollisionHelper.collideSide(block, player))
                Log.d("MainGame", "Dead");

            for (GameObject object: eggs)
            {
                Egg egg = (Egg) object;
                if (CollisionHelper.collideSide(block, egg)) {
                    remove(Layer.egg, egg);
                    player.changeEggCount(1);
                    player.setIsOverGround(true);
                }
            }
        }
    }

    public void draw(Canvas canvas)
    {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)
        if (action == MotionEvent.ACTION_DOWN)
        {
            player.layEgg();
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
