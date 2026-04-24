package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.EramPower;
import roaringknight.powers.SurroundedPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;

public class Surrounded extends AbstractEasyCard {
    public final static String ID = makeID("Surrounded");

    public Surrounded() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SurroundedPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}