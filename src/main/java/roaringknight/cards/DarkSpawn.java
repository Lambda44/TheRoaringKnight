package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.vfx.TitanspawnEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class DarkSpawn extends AbstractEasyCard {
    public final static String ID = makeID("DarkSpawn");

    public DarkSpawn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
        this.baseMagicNumber = 14;
        this.cardsToPreview = new TitanSpawn();
        this.purgeOnUse = true;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (exhaustPile().size() >= this.baseMagicNumber) {
            AbstractCard c = new TitanSpawn();
            if (!p.hasPower(TheRoaringPower.POWER_ID))
                c.setCostForTurn(0);
            makeInHand(c);
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
        upgradeBlock(1);
        upgradeMagicNumber(-4);
    }
}