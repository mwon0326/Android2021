package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;


public class MainGame {
    static MainGame instance;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    public float frameTime;
    private static final int BALL_COUNT = 10;
    private boolean initialized;
    private ArrayList<Block> blocks;

    public static MainGame get(){
        if (instance == null){
            instance = new MainGame();
        }
        return instance;
    }

    public void add(GameObject gameObject)
    {
        gameObjects.add(gameObject);
    }

    public boolean initResources() {
        if (initialized)
            return false;

        Random rand = new Random();
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();


        Obstacle obstacle = new Obstacle(5, 5, 300, 300);
        gameObjects.add(obstacle);
        initialized = true;
        return true;
    }

    public void update()
    {
        for(GameObject o: gameObjects)
            o.update();
    }

    public void draw(Canvas canvas)
    {
        for(GameObject o: gameObjects)
            o.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)
        if (action == MotionEvent.ACTION_DOWN)
        {
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
