package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.playAudioV;

public class TitanFaceEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img = null;

    public TitanFaceEffect() {
        this.img = ImageMaster.loadImage(makeVFXPath("titan_face.png"));
        this.scale = Settings.scale;
        this.duration = 1f;
        this.color = new Color(1f, 1f, 1f, 1f);
        this.x = (Settings.WIDTH / 4.0F) - (this.img.getWidth() / 2.0f);
        this.y = Settings.HEIGHT / 2.0F;
        this.renderBehind = false;
        playAudioV(ProAudio.TITAN_HEAL, 1.0f);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale);
    }

    @Override
    public void dispose() {
    }
}
