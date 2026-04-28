package roaringknight.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.GoldenStarsPower;
import roaringknight.powers.MigrainerPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;

public class GoldenStars extends AbstractEasyCard {
    public final static String ID = makeID("GoldenStars");

    public GoldenStars() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = -1;
        this.isEthereal = true;
        AbstractCard c = new Star();
        c.upgrade();
        MultiCardPreview.add(this, new Star(), c);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GoldenStarsPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}