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
import static roaringknight.util.Wiz.playAudio;

public class SwoonEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;

    public SwoonEffect(AbstractMonster m) {
        this.x = m.hb.x - (m.hb_w / 4f);
        this.y = m.hb.y + m.hb_h;
        this.img = ImageMaster.loadImage(makeVFXPath("swoon.png"));
        this.duration = 1.2f;
        this.scale = Settings.scale;
    }

    public void update() {
        if (this.duration == 1.2f) {
            playAudio(ProAudio.SOUL_HURT);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        sb.draw(this.img, this.x, this.y, this.img.getWidth()/2.0f, this.img.getHeight()/2.0f, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, 0.0f, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
    }
}
