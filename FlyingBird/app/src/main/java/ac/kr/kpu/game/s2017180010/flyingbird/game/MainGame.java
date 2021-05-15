package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;
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
    Player player;

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

    ArrayList<ArrayList<GameObject>> layers;
    public enum Layer{
        bg, obstacle, player, egg, LAYER_COUNT
    }
    public boolean initResources() {
        if (initialized)
            return false;

        Random rand = new Random();
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        Random random = new Random();
        int max_rand = 5;
        int min_rand = 1;
        int widRand = random.nextInt(max_rand + min_rand + 1) + min_rand;
        int heiRand = random.nextInt(max_rand - 2 + min_rand + 1) + min_rand;

        Obstacle obstacle = new Obstacle(300, h - 300, 100);
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

        player = new Player(200, h - 300);
        add(Layer.player, player);
        initialized = true;
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

    public void remove(GameObject gameObject)
    {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.remove(gameObject);
            }
        });
    }
}
