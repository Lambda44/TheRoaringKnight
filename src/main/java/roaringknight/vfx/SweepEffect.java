package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.p;
import static roaringknight.util.Wiz.playAudioV;

public class SweepEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;

    public SweepEffect() {
        this.x = p().hb.x + p().hb_w;
        this.y = p().hb.y + (p().hb_h / 4.0f);
        this.img = ImageMaster.loadImage(makeVFXPath("sweep.png"));
        this.duration = 1.2f;
        this.scale = Settings.scale;
        playAudioV(ProAudio.CUT, 0.8f);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.x += (17f * Settings.scale);
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale);
    }

    @Override
    public void dispose() {
    }
}
