package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TitanPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ForgottenArmy extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("ForgottenArmy");

    public ForgottenArmy() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 4;
        exhaust = true;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void applyPowers() {
        int realBlock = this.baseBlock;
        this.baseBlock += exhaustPile().size();
        super.applyPowers();
        this.baseBlock = realBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBlock = this.baseBlock;
        this.baseBlock += exhaustPile().size();
        super.calculateCardDamage(mo);
        this.baseBlock = realBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}