package ac.kr.kpu.game.s2017180010.flyingbird.game.scene;

import android.view.MotionEvent;

import androidx.annotation.MainThread;

import ac.kr.kpu.game.s2017180010.flyingbird.R;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Button;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.ImageObject;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.Scene;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Score;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class GameOverScene extends Scene {
    private Button titleButton;

    public enum Layer{
        bg, ui, LAYER_COUNT
    }

    public static GameOverScene scene;

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    @Override
    public void start() {
        super.start();
        transparent = true;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        initLayers(PauseScene.Layer.LAYER_COUNT.ordinal());

        titleButton = new Button(w / 2, h - 300, 300, 100,
                R.mipmap.title_button_default, R.mipmap.title_button_press);
        add(Layer.ui, titleButton);

        add(Layer.bg, new ImageObject(0, 0, w, h, R.mipmap.bg_start));

        add(Layer.ui, new ImageObject((w/2) - 300, 300,(w/2) + 300,500,
                R.mipmap.gameover));

        add(Layer.ui, new ImageObject((w/2) - 200, (h/2) - 300, (w/2) + 200,
                (h/2) - 100, R.mipmap.score));

        Score score = new Score((w / 2), (h / 2));
        score.setScore(MainScene.score.getScore());

        add(Layer.ui, score);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (titleButton.touchButton(e.getX(), e.getY()))
            {
                titleButton.setPress();
                return true;
            }
        }
        else if (e.getAction() == MotionEvent.ACTION_UP){
            titleButton.setDefault();
            if (titleButton.touchButton(e.getX(), e.getY()))
            {
                MainGame.get().popScene();
                MainGame.get().popScene();
            }
            return true;
        }
        return false;
    }
}
