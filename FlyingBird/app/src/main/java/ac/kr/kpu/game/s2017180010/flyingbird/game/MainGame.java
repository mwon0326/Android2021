package ac.kr.kpu.game.s2017180010.flyingbird.game;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.BaseGame;
import ac.kr.kpu.game.s2017180010.flyingbird.game.scene.StartScene;


public class MainGame extends BaseGame {
    private boolean initialized;

    public static MainGame get(){
        return (MainGame) instance;
    }

    @Override
    public boolean initResources() {
        if (initialized)
            return false;

        push(new StartScene());
        initialized = true;

        return true;
    }
}
