package roaringknight.relics;

import basemod.BaseMod;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;

import static roaringknight.RKMod.makeID;

public class BlackHole extends AbstractEasyRelic {
    public static final String ID = makeID("BlackHole");

    public BlackHole() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
        String keywordID = "roaringknight:shadow";
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle(keywordID), BaseMod.getKeywordDescription(keywordID)));
    }

    public void atPreBattle() {
        this.counter = 0;
    }

    public void atTurnStart() {
        this.counter = 1;
        this.beginLongPulse();
    }

    public void onEquip() {
        this.counter = -1;
    }

    public void onVictory() {
        this.counter = -1;
        this.stopPulse();
    }

    public void setCounter(int counter) {
        super.setCounter(counter);
    }

    public AbstractRelic makeCopy() {
        return new BlackHole();
    }
}
