package roaringknight.relics;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;
import roaringknight.actions.WarpAction;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class WhiteHole extends AbstractEasyRelic {
    public static final String ID = makeID("WhiteHole");

    public WhiteHole() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
        String keywordID = "roaringknight:shadow";
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle(keywordID), BaseMod.getKeywordDescription(keywordID)));
    }

    public void atTurnStart() {
        this.flash();
        atb(new WarpAction(true));
        atb(new GainBlockAction(p(), 6));
    }

    public AbstractRelic makeCopy() {
        return new WhiteHole();
    }
}
