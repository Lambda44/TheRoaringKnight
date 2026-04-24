package roaringknight.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import roaringknight.RoaringKnight;

import static roaringknight.util.Wiz.p;

public class AfterImageEffect extends AbstractGameEffect {
    private Texture img = null;
    private float x;
    private float y;
    private boolean flipX;
    private boolean roar;
    private float speed;

    public AfterImageEffect(float x, float y, boolean isRoaring, boolean flipH) {
        this.roar = isRoaring;
        this.renderBehind = true;
        this.color = Settings.QUARTER_TRANSPARENT_WHITE_COLOR.cpy();
        this.x = x;
        if (flipH) {
            this.x -= (65.0f * Settings.scale);
        }
        this.y = y;
        this.img = ImageMaster.loadImage(RoaringKnight.getCurrentSprite());
        if (isRoaring) {
            this.duration = 0.3f;
            this.speed = 3.0f;
        } else {
            this.duration = 0.6f;
            this.speed = 1.5f;
        }
        this.flipX = flipH;
        this.scale = Settings.scale;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.flipX) { //if fighting spear and shield
            this.x += (this.speed * Settings.scale);
        } else { //render it normally
            this.x -= (this.speed * Settings.scale);
        }
        if (this.roar) {
            this.y -= (this.speed * Settings.scale);
        }
        if (this.duration < 0.0f) {
            this.dispose();
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Settings.QUARTER_TRANSPARENT_WHITE_COLOR.cpy());
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), flipX, false);
    }

    @Override
    public void dispose() {
    }
}
