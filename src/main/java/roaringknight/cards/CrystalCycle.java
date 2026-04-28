package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.CrystalCyclePower;
import roaringknight.powers.EramPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;

public class CrystalCycle extends AbstractEasyCard {
    public final static String ID = makeID("CrystalCycle");

    public CrystalCycle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.cardsToPreview = new Star();
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CrystalCyclePower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}