package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import roaringknight.util.ProAudio;

import static roaringknight.util.Wiz.playAudioV;

public class RoarFlashEffect extends AbstractGameEffect {
    private float timeSkip;

    public RoarFlashEffect() {
        this.duration = 4.0f;
        this.timeSkip = 0.1f;
        this.scale = Settings.scale;
    }

    public void update() {
        if (this.duration == 4.0f) {
            playAudioV(ProAudio.ROAR, 1.0f);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timeSkip -= Gdx.graphics.getDeltaTime();
        if (this.timeSkip <= 0f) {
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.1F, 0.1F, 1.0F)));
            this.timeSkip = 0.1f;
        }
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {
    }
}
