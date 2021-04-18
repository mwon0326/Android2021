package ac.kr.kpu.game.s2017180010.flyingbird.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import ac.kr.kpu.game.s2017180010.flyingbird.framework.GameObject;

public class Obstacle implements GameObject {
    int width, height;
    float x, y;
    ArrayList<Block> blocks;
    public Obstacle(int width, int height, float x, float y)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        blocks = new ArrayList<>();
        int bw = 0;
        int bh = 0;

        for (int i = 0; i < width; i++)
        {
            float tempY = y;
            for (int j = 0; j < height; j++)
            {
                Block b = new Block(x, tempY);
                if (i == 0 && j == 0) {
                    bw = b.getWidth();
                    bh = b.getHeight();
                }

                blocks.add(b);
                tempY += bh;
            }
            x += bw;
        }
    }

    public void update()
    {

    }

    public void draw(Canvas canvas)
    {
        for (Block b : blocks)
            b.draw(canvas);
    }
}
