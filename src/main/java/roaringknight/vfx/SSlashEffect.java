package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.powers.TheRoaringPower;
import roaringknight.util.ProAudio;

import static roaringknight.util.Wiz.p;
import static roaringknight.util.Wiz.playAudioV;

public class SSlashEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private float timeStep;
    private boolean audioStep;
    private float startDuration;

    public SSlashEffect(AbstractMonster m, Color color) {
        this.x = m.hb.cX - (m.hb_w / 2f);
        this.y = m.hb.cY - (m.hb_h / 2f);
        this.img = ImageMaster.HORIZONTAL_LINE;
        this.duration = 1.9f;
        this.startDuration = this.duration;
        this.timeStep = 0.05f;
        this.scale = 2f * Settings.scale;
        this.rotation = 0f;
        this.color = color;
    }

    public void update() {
        if (this.startDuration == this.duration) {
            playAudioV(ProAudio.CUT, 0.8f);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timeStep -= Gdx.graphics.getDeltaTime();
        if (this.timeStep <= 0f) {
            this.timeStep = 0.05f;
            this.rotation -= 20f;
            if (this.duration >= 0.14f && audioStep) {
                playAudioV(ProAudio.CUT, 0.8f);
                audioStep = false;
            } else if (this.duration >= 0.14f) {
                audioStep = true;
            }
            if (this.rotation <= -360f)
                this.rotation = 0f;
        }
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.getWidth()/2.0f, this.img.getHeight()/2.0f, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
    }
}
