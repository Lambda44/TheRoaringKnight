package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.p;
import static roaringknight.util.Wiz.playAudioV;

public class TitanspawnEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img = null;
    private boolean flipX;

    public TitanspawnEffect() {
        this.img = TexLoader.getTexture(makeVFXPath("titanspawn.png"));
        this.scale = Settings.scale;
        this.duration = 1f;
        this.flipX = p().flipHorizontal;
        if (this.flipX) {
            this.x = p().hb.x - (30F * Settings.scale);
        } else {
            this.x = p().hb.x + p().hb_w + (30F * Settings.scale);
        }
        this.y = p().hb.y + (p().hb_h / 2F);
        this.renderBehind = false;
        playAudioV(ProAudio.TITANSPAWN, 1.0f);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), flipX, false);
    }

    @Override
    public void dispose() {
    }
}
