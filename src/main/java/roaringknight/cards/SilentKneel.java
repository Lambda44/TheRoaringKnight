package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import roaringknight.RKMod;
import roaringknight.actions.ModifyMagicAction;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SilentKneel extends AbstractEasyCard {
    public final static String ID = makeID("SilentKneel");

    public SilentKneel() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 10;
        this.baseMagicNumber = 2;
        this.baseSecondMagic = 8;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (exhaustPile().size() >= this.baseMagicNumber) {
            blck();
            applyToSelf(new DexterityPower(p, 2));
        }
        atb(new ModifyMagicAction(this.uuid, this.baseSecondMagic));
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
        upgradeSecondMagic(-2);
    }
}