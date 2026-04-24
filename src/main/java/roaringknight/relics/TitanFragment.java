package roaringknight.relics;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;
import roaringknight.cards.ShadowSerpent;
import roaringknight.cards.TitanSpawn;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class TitanFragment extends AbstractEasyRelic {
    public static final String ID = makeID("TitanFragment");

    public TitanFragment() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
        this.tips.add(new CardPowerTip(new ShadowSerpent()));
    }

    public void atBattleStartPreDraw() {
        atb(new RelicAboveCreatureAction(p(), this));
        AbstractCard c = new ShadowSerpent();
        CardModifierManager.addModifier(c, new RetainMod());
        c.modifyCostForCombat(-9);
        makeInHand(c);
    }

    public AbstractRelic makeCopy() {
        return new TitanFragment();
    }
}
