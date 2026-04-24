package roaringknight.animation;

import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Mainline;
import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import roaringknight.RoaringKnight;
import roaringknight.powers.TheRoaringPower;
import roaringknight.vfx.ExecuteEffect;

import static roaringknight.util.Wiz.p;

public class MyAnimationListener implements Player.PlayerListener{
    private final RoaringKnight character;
    public static int animCount;
    public MyAnimationListener(RoaringKnight ch) {
        this.character = ch;
        animCount = 0;
    }

    @Override
    public void animationFinished(Animation animation) {
        if (animation.name.equals("Dmg_Idle") && animCount <= 8) {
            this.character.playAnimation("Dmg_Damaged");
            animCount++;
        } else if (animation.name.equals("Dmg_Damaged") && animCount <= 8) {
            this.character.playAnimation("Dmg_Idle");
            animCount++;
        } else if (animation.name.equals("Roaring1") && animCount <= 120) {
            this.character.playAnimation("Roaring2");
            animCount++;
        } else if (animation.name.equals("Roaring2") && animCount <= 120) {
            this.character.playAnimation("Roaring1");
            animCount++;
        } else if (animation.name.equals("Windup")) {
            AbstractDungeon.effectsQueue.add(new ExecuteEffect());
            this.character.STATE = "Execute";
            this.character.playAnimation("Execute");
        } else if ((animation.name.equals("SS_Windup") || animation.name.equals("SSlash3")) && animCount <= 48) {
            this.character.STATE = "SSlash1";
            this.character.playAnimation("SSlash1");
            animCount++;
        } else if (animation.name.equals("SSlash1") && animCount <= 48) {
            this.character.STATE = "SSlash2";
            this.character.playAnimation("SSlash2");
            animCount++;
        } else if (animation.name.equals("SSlash2") && animCount <= 48) {
            this.character.STATE = "SSlash3";
            this.character.playAnimation("SSlash3");
            animCount++;
        } else {
            this.character.STATE = "Idle";
            this.character.playAnimation("Idle");
            animCount = 0;
            this.character.startBlackKnifeBGM();
        }
    }

    @Override
    public void animationChanged(Animation animation, Animation animation1) {
    }

    @Override
    public void preProcess(Player player) {

    }

    @Override
    public void postProcess(Player player) {

    }

    @Override
    public void mainlineKeyChanged(Mainline.Key key, Mainline.Key key1) {

    }
}
