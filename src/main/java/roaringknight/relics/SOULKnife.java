package roaringknight.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;
import roaringknight.cards.Star;
import roaringknight.powers.SOULCrystalPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SOULKnife extends AbstractEasyRelic {
    public static final String ID = makeID("SOULKnife");

    public SOULKnife() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, RoaringKnight.Enums.KNIGHT_COLOR);
        this.tips.add(new CardPowerTip(new Star()));
    }

    public void atBattleStart() {
        applyToSelf(new SOULCrystalPower(p(), 2));
    }

    public void atTurnStart() {
        this.flash();
        makeInHand(new Star());
    }

    @Override
    public void obtain() {
        if (p().hasRelic(BlackKnife.ID)) {
            for (int i = 0; i < p().relics.size(); ++i) {
                if (p().relics.get(i).relicId.equals(BlackKnife.ID)) {
                    instantObtain(p(), i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public boolean canSpawn() {
        return p().hasRelic(BlackKnife.ID);
    }

    public AbstractRelic makeCopy() {
        return new SOULKnife();
    }
}
