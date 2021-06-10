package ac.kr.kpu.game.s2017180010.flyingbird.game.ctroller;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.BaseGame;
import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.game.MainGame;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Block;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Bullet;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Egg;
import ac.kr.kpu.game.s2017180010.flyingbird.game.object.Player;
import ac.kr.kpu.game.s2017180010.flyingbird.game.scene.GameOverScene;
import ac.kr.kpu.game.s2017180010.flyingbird.game.scene.MainScene;

public class Collision implements GameObject {
    private final Player player;

    public Collision(Player player)
    {
        this.player = player;
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();

        String key;
        Block block;
        ArrayList<GameObject> eggs = MainScene.scene.objectsAt(MainScene.Layer.egg);
        ArrayList<GameObject> bullets = MainScene.scene.objectsAt(MainScene.Layer.bullet);

        if (MainScene.scene.shootingMode)
        {
            if (MainScene.scene.shootingTimer.getStopTimer())
            {
                MainScene.scene.shootingMode = false;
                MainScene.scene.combo = 0;
            }

            if (player.getIsOverGround())
            {
                player.down(game.frameTime * 400);
            }

            for (int i = 0; i < MainScene.scene.obstacleWidth; i++)
            {
                boolean collided = false;

                for (int j = 0; j < MainScene.scene.obstacleHeight; j++)
                {
                    key = Integer.toString(i) + Integer.toString(j);
                    block = MainScene.scene.blockGenerator.getBlock(key);

                    for(GameObject object : bullets)
                    {
                        Bullet bullet = (Bullet)object;
                        if (CollisionHelper.collideSide(block, bullet))
                        {
                            MainScene.scene.remove(bullet);
                            block.setIsDraw(false);
                            MainScene.score.addScore(50);
                            collided = true;
                            break;
                        }
                    }
                    if (collided)
                        break;
                }
                if (collided)
                    break;
            }

        }
        else
        {
            if (player.getIsOverGround())
            {
                key = Integer.toString(MainScene.scene.obstacleWidth - 1) + Integer.toString(MainScene.scene.obstacleHeight - 1);
                block = MainScene.scene.blockGenerator.getBlock(key);

                if (CollisionHelper.overBlock(block, player)) {
                    player.down(game.frameTime * 800);
                    int count = 0;
                    for (GameObject object: eggs) {
                        Egg egg = (Egg) object;
                        egg.down(game.frameTime * 800, player.getIsOverGround(),
                                MainScene.scene.GROUND - (count * egg.getHeight()));
                        count += 1;
                    }
                }
            }
            else
            {
                key = '0' + Integer.toString(MainScene.scene.obstacleHeight - 1);
                block = MainScene.scene.blockGenerator.getBlock(key);

                if (CollisionHelper.collideSide(block, player)) {
                    Log.d("MainGame", "Dead");
                    MainGame.get().push(new GameOverScene());
                }

                boolean comboCheck = true;
                int checkCount = 0;

                for (GameObject object: eggs)
                {
                    Egg egg = (Egg) object;
                    if (CollisionHelper.collideSide(block, egg)) {
                        if (comboCheck && checkCount == 0)
                        {
                            if (player.getEggCount() == MainScene.scene.obstacleHeight)
                            {
                                MainScene.scene.combo += 1;
                                comboCheck = false;
                                Log.d("MainGame", "Combo : " + MainScene.scene.combo);
                                MainScene.score.addScore(30);
                            }
                        }
                        MainScene.scene.remove(egg);
                        player.changeEggCount(1);
                        player.setIsOverGround(true);
                        checkCount += 1;
                    }
                }
            }

            if (MainScene.scene.combo >= 5) {
                MainScene.scene.shootingMode = true;
                MainScene.scene.shootingTimer.setDraw(true);
                MainScene.scene.shootingTimer.setStopTimer(false);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
