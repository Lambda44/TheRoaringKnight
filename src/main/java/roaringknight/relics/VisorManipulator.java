package roaringknight.relics;

import basemod.BaseMod;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RKMod;
import roaringknight.RoaringKnight;
import roaringknight.cards.Star;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class VisorManipulator extends AbstractEasyRelic {
    public static final String ID = makeID("VisorManipulator");

    public VisorManipulator() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
        this.tips.add(new CardPowerTip(new Star()));
    }

    @Override
    public void onEquip() {
        BaseMod.MAX_HAND_SIZE +=2;
    }

    @Override
    public void onUnequip() {
        BaseMod.MAX_HAND_SIZE -=2;
    }

    public void atBattleStartPreDraw() {
        this.flash();
        atb(new RelicAboveCreatureAction(p(), this));
        makeInHand(new Star(), 2);
    }

    public AbstractRelic makeCopy() {
        return new VisorManipulator();
    }
}
