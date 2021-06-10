package ac.kr.kpu.game.s2017180010.flyingbird.game.ctroller;

import android.graphics.RectF;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.BoxCollidable;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Block;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();

    public static boolean collideSide(Block block, BoxCollidable object)
    {
        block.getBoundingRect(rect1);
        object.getBoundingRect(rect2);

        if (!block.getIsDraw())
            return false;

        return (rect2.right > rect1.left) && (rect2.bottom > rect1.top);
    }

    public static boolean collideBullet(Block block, BoxCollidable object)
    {
        block.getBoundingRect(rect1);
        object.getBoundingRect(rect2);

        if (!block.getIsDraw())
            return false;

        if (rect2.bottom < rect1.top) return false;
        if (rect2.top > rect1.bottom) return false;

        return (rect2.right > rect1.left);
    }
    public static boolean overBlock(Block block, BoxCollidable object)
    {
        block.getBoundingRect(rect1);
        object.getBoundingRect(rect2);

        return (rect1.right < rect2.left);
    }
}
