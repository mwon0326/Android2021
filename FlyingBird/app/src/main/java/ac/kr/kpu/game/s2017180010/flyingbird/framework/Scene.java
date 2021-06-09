package ac.kr.kpu.game.s2017180010.flyingbird.framework;

import android.view.MotionEvent;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class Scene {
    protected ArrayList<ArrayList<GameObject>> layers;
    protected boolean transparent;

    public ArrayList<ArrayList<GameObject>> getLayers() { return layers; }

    public boolean isTransparent() {
        return transparent;
    }

    protected void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                BaseGame game = BaseGame.get();
                for (ArrayList<GameObject> objects: layers) {
                    objects.remove(gameObject);
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }
    public void start() {}
    public void end() {}
    public void pause() {}
    public void resume() {}
    public boolean onTouchEvent(MotionEvent e) { return false; }
}
