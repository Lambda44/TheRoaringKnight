package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeVFXPath;

public class RoaringEyesEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img = null;

    public RoaringEyesEffect() {
        this.img = TexLoader.getTexture(makeVFXPath("titan_eye.png"));
        this.scale = Settings.scale;
        this.duration = 1f;
        this.color = Settings.HALF_TRANSPARENT_WHITE_COLOR.cpy();
        this.x = (Settings.WIDTH / 2.0F) - (this.img.getWidth() / 2.0f);
        this.y = Settings.HEIGHT / 2.0F;
        this.renderBehind = false;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale);
    }

    @Override
    public void dispose() {
    }
}
