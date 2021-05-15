package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.RectF;
import android.util.Log;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();

    public static boolean collideSide(Block block, BoxCollidable object)
    {
        block.getBoundingRect(rect1);
        object.getBoundingRect(rect2);

        return (rect2.right > rect1.left) && (rect2.bottom > rect1.top);
    }
}
