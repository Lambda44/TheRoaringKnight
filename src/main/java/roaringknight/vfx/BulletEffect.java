package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeImagePath;
import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.*;

public class BulletEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private AbstractMonster mo;
    private boolean flipX;
    private boolean hit;

    public BulletEffect(AbstractMonster m) {
        this.x = p().hb.x + p().hb_w;
        this.y = m.hb.y + (m.hb_h /2.0f);
        this.mo = m;
        this.img = TexLoader.getTexture(makeVFXPath("bullet.png"));
        this.duration = 2.0f;
        this.scale = Settings.scale;
        this.flipX = p().flipHorizontal;
        this.hit = false;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.flipX) {
            this.x -= (13f * Settings.scale);
            if (this.x > (this.mo.hb.cX - (this.mo.hb_w / 4f)))
                this.hit = true;
        } else {
            this.x += (13f * Settings.scale);
            if (this.x > (this.mo.hb.x + (this.mo.hb_w / 4f)))
                this.hit = true;
        }
        if (this.hit || this.duration < 0.0f) {
            playAudioV(ProAudio.SOUL_HURT, 2.0f);
            this.dispose();
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
