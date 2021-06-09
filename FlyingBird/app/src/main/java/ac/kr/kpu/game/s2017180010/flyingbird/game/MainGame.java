package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.util.Log;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.BaseGame;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;


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
