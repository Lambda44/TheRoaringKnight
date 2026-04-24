package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.playAudioV;

public class TitanSlashEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;

    public TitanSlashEffect() {
        this.img = ImageMaster.loadImage(makeVFXPath("titan_slash.png"));
        this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
        this.x = (float)Settings.WIDTH * 0.7F - (float)this.img.getWidth() / 2.0F;
        this.y = AbstractDungeon.floorY + 100.0F * Settings.scale - (float)this.img.getHeight() / 2.0F;
        this.duration = 0.55f;
        this.scale = 1.2F * Settings.scale;
        this.rotation = 0f;
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
        sb.draw(this.img, this.x, this.y, this.img.getWidth()/2.0f, this.img.getHeight()/2.0f, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
    }

    @Override
    public void dispose() {
    }
}
