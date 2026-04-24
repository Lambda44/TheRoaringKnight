package roaringknight.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;
import roaringknight.cards.Prophecy;
import roaringknight.powers.TheRoaringPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Sanctuary extends AbstractEasyRelic {
    public static final String ID = makeID("Sanctuary");

    public Sanctuary() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
        this.tips.add(new CardPowerTip(new Prophecy()));
    }

    public void atBattleStartPreDraw() {
        this.flash();
        atb(new RelicAboveCreatureAction(p(), this));
        makeInHand(new Prophecy(), 2);
    }

    public AbstractRelic makeCopy() {
        return new Sanctuary();
    }
}
