package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.RoaringKnight;
import roaringknight.util.ProAudio;
import roaringknight.util.TexLoader;

import java.util.Set;

import static roaringknight.RKMod.makeImagePath;
import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.p;
import static roaringknight.util.Wiz.playAudio;

public class KnifeEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private float rotation;

    public KnifeEffect(AbstractMonster m) {
        this.img = TexLoader.getTexture(makeVFXPath("black_knife.png"));
        this.x = m.hb.x + (m.hb_w / 3f);
        this.y = m.hb.y + (m.hb_h * 2f);
        this.duration = 0.5f;
        this.scale = Settings.scale;
        this.rotation = -90.0f;
    }

    public void update() {
        if (this.duration == 0.5f) {
            playAudio(ProAudio.KNIFE);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.y -= (12f * Settings.scale);
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        sb.draw(this.img, this.x, this.y, this.img.getWidth()/2.0f, this.img.getHeight()/2.0f, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
    }
}
