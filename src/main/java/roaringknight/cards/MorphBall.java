package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import roaringknight.RKMod;
import roaringknight.actions.EasyXCostAction;
import roaringknight.powers.ParalyzePower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class MorphBall extends AbstractEasyCard {
    public final static String ID = makeID("MorphBall");

    public MorphBall() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = 5;
        this.baseMagicNumber = this.magicNumber = 0;
        this.baseSecondMagic = 1;
        exhaust = true;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new ParalyzePower(m));
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < (effect + params[0]); i++) {
                blck();
            }
            return true;
        }, this.magicNumber));
        applyToSelf(new DexterityPower(p(), this.baseSecondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}