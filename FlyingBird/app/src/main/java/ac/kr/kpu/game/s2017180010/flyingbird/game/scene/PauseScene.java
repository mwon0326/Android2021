package ac.kr.kpu.game.s2017180010.flyingbird.game.scene;

import android.view.MotionEvent;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Button;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.ImageObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Scene;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class PauseScene extends Scene {
    private Button titleButton;
    private Button backButton;

    public enum Layer{
        bg, ui, LAYER_COUNT
    }

    public static PauseScene scene;

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

        backButton = new Button(w / 2, h - 300, 300, 100,
                R.mipmap.back_button_default, R.mipmap.back_button_press);
        add(Layer.ui, backButton);

        titleButton = new Button(w / 2, h - 600, 300, 100,
                R.mipmap.title_button_default, R.mipmap.title_button_press);
        add(Layer.ui, titleButton);

        add(Layer.bg, new ImageObject(0, 0, w, h, R.mipmap.bg_pause));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (backButton.touchButton(e.getX(), e.getY())) {
                backButton.setPress();
                return true;
            }
            else if (titleButton.touchButton(e.getX(), e.getY()))
            {
                titleButton.setPress();
                return true;
            }
        }
        else if (e.getAction() == MotionEvent.ACTION_UP){
            backButton.setDefault();
            titleButton.setDefault();
            if (backButton.touchButton(e.getX(), e.getY())) {
                MainGame.get().popScene();
            }
            else if (titleButton.touchButton(e.getX(), e.getY()))
            {
                MainGame.get().popScene();
                MainGame.get().popScene();
            }
            return true;
        }
        return false;
    }
}
