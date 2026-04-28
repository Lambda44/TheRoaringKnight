package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.playAudioV;

public class TitanWingEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img = null;
    private float timeStep;

    public TitanWingEffect(AbstractMonster m) {
        this.img = TexLoader.getTexture(makeVFXPath("titan_wing.png"));
        this.scale = Settings.scale;
        this.duration = 0.6f;
        this.timeStep = 0.05f;
        this.color = new Color(1f, 1f, 1f, 1f);
        this.rotation = 0f;
        this.x = m.hb.x;
        this.y = m.hb.y + (m.hb_h / 2f);
        this.renderBehind = false;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timeStep -= Gdx.graphics.getDeltaTime();
        if (this.timeStep <= 0f) {
            this.timeStep = 0.05f;
            this.rotation -= 15f;
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, 0f, 0f, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale, this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
    }
}
