package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.CrystalCyclePower;
import roaringknight.powers.MigrainerPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;

public class Migrainer extends AbstractEasyCard {
    public final static String ID = makeID("Migrainer");

    public Migrainer() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MigrainerPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}