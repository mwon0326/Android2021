package ac.kr.kpu.game.s2017180010.flyingbird.framework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.BuildConfig;

public class BaseGame {
    protected static BaseGame instance;
    public float frameTime;
    ArrayList<Scene> sceneStack = new ArrayList<>();

    public static BaseGame get() {
        return instance;
    }

    protected BaseGame() {
        instance = this;
    }

    public boolean initResources() {
        // prints this is error
        return false;
    }

    public Scene getTopScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex < 0) return null;
        return sceneStack.get(lastIndex);
    }

    public void start(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            top.end();
            sceneStack.set(lastIndex, scene);
        } else {
            sceneStack.add(scene);
        }
        scene.start();
    }

    public void push(Scene scene) {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            top.pause();
        }
        sceneStack.add(scene);
        scene.start();
    }

    public void popScene() {
        int lastIndex = sceneStack.size() - 1;
        if (lastIndex >= 0) {
            Scene top = sceneStack.remove(lastIndex);
            top.end();
        }
        lastIndex--;
        if (lastIndex >= 0) {
            Scene top = sceneStack.get(lastIndex);
            top.resume();
        } else {
            Log.e("BaseGame", "should end app in popScene()");
        }
    }

    public void update() {
        ArrayList<ArrayList<GameObject>> layers = getTopScene().getLayers();
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
    }

    public void draw(Canvas canvas) {
        draw(canvas, sceneStack.size() - 1);
    }
    protected void draw(Canvas canvas, int index) {
        Scene scene = sceneStack.get(index);
        if (scene.isTransparent() && index > 0) {
            draw(canvas, index - 1);
        }
        ArrayList<ArrayList<GameObject>> layers = scene.getLayers();
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return getTopScene().onTouchEvent(event);
    }
}
