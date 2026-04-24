package roaringknight.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.UnnervePower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;

public class Unnerve extends AbstractEasyCard {
    public final static String ID = makeID("Unnerve");

    public Unnerve() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        AbstractCard c = new DarkBullet();
        c.upgrade();
        MultiCardPreview.add(this, new DarkBullet(), c);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UnnervePower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}