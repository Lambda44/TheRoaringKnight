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

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.playAudio;
import static roaringknight.util.Wiz.playAudioV;

public class TitanLaserEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private float timeStep;
    private String path;
    private int animNum;

    public TitanLaserEffect(AbstractMonster m) {
        this.animNum = 0;
        this.path = "Laser/TitanLaser" + animNum + ".png";
        this.img = TexLoader.getTexture(makeVFXPath(this.path));
        this.x = m.hb.x + (m.hb_w / 2f) - (this.img.getWidth() / 2f);
        this.y = m.hb.y;
        this.duration = 1.0f;
        this.scale = Settings.scale;
        this.timeStep = 0.05f;
        playAudioV(ProAudio.TITAN_LASER, 1.0f);
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timeStep -= Gdx.graphics.getDeltaTime();
        if (this.timeStep <= 0f) {
            this.timeStep = 0.05f;
            getNextImage();
        }
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

    private void getNextImage() {
        this.animNum++;
        if (animNum > 19)
            animNum = 19;
        this.path = "Laser/TitanLaser" + animNum + ".png";
        this.img = TexLoader.getTexture(makeVFXPath(this.path));
    }
}
