package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ShadowDrop extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("ShadowDrop");

    public ShadowDrop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemyFast(mo, new StrengthPower(mo, -this.baseMagicNumber));
        }
        for(AbstractMonster mo : getEnemies()) {
            if (!mo.hasPower("Artifact"))
                applyToEnemyFast(mo, new GainStrengthPower(mo, this.baseMagicNumber));
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