package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.RoaringKnight;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.p;

public class ExecuteEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private boolean flipX;

    public ExecuteEffect() {
        this.renderBehind = true;
        this.color = Settings.QUARTER_TRANSPARENT_WHITE_COLOR.cpy();
        this.flipX = p().flipHorizontal;
        this.scale = Settings.scale;
        if (this.flipX) {
            this.x = p().hb.x - (250.0f * this.scale);
        } else {
            this.x = p().hb.x;
        }
        this.y = p().hb.y + (20f * this.scale);
        this.img = TexLoader.getTexture(makeVFXPath("rkslash.png"));
        this.duration = 0.3f;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Settings.QUARTER_TRANSPARENT_WHITE_COLOR.cpy());
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), flipX, false);
    }

    @Override
    public void dispose() {
    }
}
