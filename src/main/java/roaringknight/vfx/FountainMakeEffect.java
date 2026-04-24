package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.p;
import static roaringknight.util.Wiz.playAudioV;

public class FountainMakeEffect extends AbstractGameEffect {
    private float timeSkip;

    public FountainMakeEffect() {
        this.duration = 1.0f;
        this.timeSkip = 0.05f;
        this.scale = Settings.scale;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timeSkip -= Gdx.graphics.getDeltaTime();
        if (this.timeSkip <= 0f) {
            playAudioV(ProAudio.FOUNTAIN, 1.0f);
            this.timeSkip = 0.05f;
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
