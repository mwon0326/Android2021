package ac.kr.kpu.game.s2017180010.flyingbird.framework;

public class BaseGame {
    protected static BaseGame instance;
    public static BaseGame get() {
        return instance;
    }
    public float frameTime;

    protected BaseGame() {
        instance = this;
    }

    public boolean initResources() {
        // prints this is error
        return false;
    }


}
