package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import roaringknight.RKMod;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ShadowMantle extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("ShadowMantle");

    public ShadowMantle() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 14;
        magicNumber = baseMagicNumber = 4;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void applyPowers() {
        AbstractPower d = p().getPower("Dexterity");
        if (d != null) {
            d.amount *= this.magicNumber;
        }
        super.applyPowers();
        if (d != null) {
            d.amount /= this.magicNumber;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower d = p().getPower("Dexterity");
        if (d != null) {
            d.amount *= this.magicNumber;
        }
        super.calculateCardDamage(mo);
        if (d != null) {
            d.amount /= this.magicNumber;
        }
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}