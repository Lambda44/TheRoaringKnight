package roaringknight.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Accelerate extends AbstractEasyCard {
    public final static String ID = makeID("Accelerate");

    public Accelerate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.baseSecondMagic = 2;
        this.tags.add(RKMod.STANCE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p(), this.baseMagicNumber));
        if (pwrAmt(p, TitanPower.POWER_ID) >= this.baseSecondMagic) {
            applyToSelf(new DrawCardNextTurnPower(p, 2));
        }
    }

    public void triggerOnGlowCheck() {
        if (pwrAmt(p(), TitanPower.POWER_ID) >= this.baseSecondMagic) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(-1);
    }
}