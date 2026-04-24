package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.MigrainerPower;
import roaringknight.powers.WhirlingVisionPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;
import static roaringknight.util.Wiz.makeInHand;

public class WhirlingVision extends AbstractEasyCard {
    public final static String ID = makeID("WhirlingVision");

    public WhirlingVision() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WhirlingVisionPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}