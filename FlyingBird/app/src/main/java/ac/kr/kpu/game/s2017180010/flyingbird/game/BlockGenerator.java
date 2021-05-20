package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;
import ac.kr.kpu.game.s2017180010.flyingbird.ui.view.GameView;

public class BlockGenerator implements GameObject {
    int width, height;
    float x, y;
    private final float speed;
    private final int MAX_WIDTH = 3;
    private final int MAX_HEIGHT = 5;
    private HashMap<String, Block> blocks;
    int bw, bh;

    private static final String TAG = BlockGenerator.class.getSimpleName();

    public BlockGenerator(float x, float y, int speed)
    {
        this.x = x;
        this.y = y;
        this.speed = speed * GameView.MULTIPLIER;

        blocks = new HashMap<String, Block>();

        bw = 0;
        bh = 0;

        String key;

        for (int i = 0; i < MAX_WIDTH; i++)
        {
            float tempY = y;
            for (int j = 0; j < MAX_HEIGHT; j++)
            {
                key = Integer.toString(i) + Integer.toString(j);
                Block b = new Block(this.x, tempY);

                if (i == 0 && j == 0) {
                    bw = b.getWidth();
                    bh = b.getHeight();
                }
                blocks.put(key, b);
                tempY -= bh;
            }
            this.x += bw;
        }

        obstacleInit();
        obstacleSet();
    }

    public void update()
    {
        String key;
        MainGame game = MainGame.get();
        float amount = speed * game.frameTime;
        for (int i = 0; i < MAX_WIDTH; i++)
        {
            for (int j = 0; j < MAX_HEIGHT; j++)
            {
                key = Integer.toString(i) + Integer.toString(j);
                Objects.requireNonNull(blocks.get(key)).updatePostion(-amount, 0);
            }
        }

        key = Integer.toString(MAX_WIDTH - 1) + Integer.toString(MAX_HEIGHT - 1);
        if (Objects.requireNonNull(blocks.get(key)).getX() < 0 && !game.player.getIsOverGround())
        {
            obstacleInit();
            obstacleSet();
        }
    }

    public void draw(Canvas canvas)
    {
        String key;
        for (int i = 0; i < MAX_WIDTH; i++)
        {
            for (int j = 0; j < MAX_HEIGHT; j++)
            {
                key = Integer.toString(i) + Integer.toString(j);
                Objects.requireNonNull(blocks.get(key)).draw(canvas);
            }
        }
    }

    public void obstacleInit()
    {
        String key;

        x = GameView.view.getWidth();

        for (int i = 0; i < MAX_WIDTH; i++)
        {
            float tempY = y;
            for (int j = 0; j < MAX_HEIGHT; j++)
            {
                key = Integer.toString(i) + Integer.toString(j);
                blocks.get(key).setIsDraw(false);
                blocks.get(key).setPosition(x, tempY);
                tempY -= bh;
            }

            x += bw;
        }
    }

    public void obstacleSet()
    {
        Random random = new Random();

        int min_rand = 1;
        this.width = random.nextInt(MAX_WIDTH - min_rand + 1) + min_rand;
        this.height = random.nextInt(MAX_HEIGHT - min_rand + 1) + min_rand;

        String key;

        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++) {
                key = Integer.toString(i) + Integer.toString(j);
                blocks.get(key).setIsDraw(true);
            }
        }

        MainGame game = MainGame.get();
        game.setObstacleSize(width, height);
    }

    public Block getBlock(String key)
    {
        return blocks.get(key);
    }
}
