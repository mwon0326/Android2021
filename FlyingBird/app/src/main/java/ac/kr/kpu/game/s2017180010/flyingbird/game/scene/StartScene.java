package ac.kr.kpu.game.s2017180010.flyingbird.game.scene;

import android.view.MotionEvent;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Scene;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Button;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.ImageObject;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class StartScene extends Scene {
    private Button startButton;

    public enum Layer{
        bg, ui, LAYER_COUNT
    }
    public static StartScene scene;

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    @Override
    public void start() {
        super.start();
        transparent = true;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        initLayers(Layer.LAYER_COUNT.ordinal());

        startButton = new Button(w / 2, h - 300, 300, 100,
                R.mipmap.start_button_default, R.mipmap.start_button_press);
        add(Layer.ui, startButton);

        add(Layer.bg, new ImageObject(0, 0, w, h, R.mipmap.bg_start));
        int sizeX = 600;
        int sizeY = 500;
        add(Layer.bg, new ImageObject((w / 2) - (sizeX / 2), 200,
                (w / 2) + (sizeX / 2), 200 + sizeY, R.mipmap.bg_title));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (startButton.touchButton(e.getX(), e.getY())) {
                startButton.ChangeButton();
                return true;
            }
        }
        else if (e.getAction() == MotionEvent.ACTION_UP){
            startButton.ChangeButton();
            if (startButton.touchButton(e.getX(), e.getY())) {
                MainGame.get().popScene();
                MainGame.get().push(new MainScene());
            }
            return true;
        }
        return false;
    }
}
