package com.hungle.freakingcolor.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hungle.freakingcolor.assets.MyAtlas;

public class AnimCircle {
    private static final Color color = new Color();
    //Todo renderer private static Sprite circle;
    public float scale;
    public float alpha;
    public float x, y;

    public void setup(float x, float y) {
        this.x = x;
        this.y = y;
        alpha = 255;
        scale = 0;
    }

    public void update(float delta) {
        if (alpha > 0) {
            alpha -= (delta * 0.5f);
            scale += (delta * 0.5f);
            if (alpha < 0)
                alpha = 0;
        }
    }

    public void draw(SpriteBatch batch) {
        if (alpha > 0) {
            color.set(1, 1, 1, alpha / 255f);
            batch.setColor(color);
            batch.draw(MyAtlas.playGameAtlas.findRegion("item5"),x-scale/2,y-scale/2, scale,scale);
        }
    }
}
