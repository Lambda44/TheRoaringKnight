package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.exhaustPile;

public class DarkHarmony extends AbstractEasyCard {
    public final static String ID = makeID("DarkHarmony");

    public DarkHarmony() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
        this.baseMagicNumber = 5;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (exhaustPile().size() >= this.baseMagicNumber) {
          blck();
        }
    }

    public void triggerOnGlowCheck() {
        if (exhaustPile().size() >= this.baseMagicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }
}