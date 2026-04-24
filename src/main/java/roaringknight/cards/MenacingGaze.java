package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import roaringknight.RKMod;
import roaringknight.powers.ParalyzePower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class MenacingGaze extends AbstractEasyCard {
    public final static String ID = makeID("MenacingGaze");

    public MenacingGaze() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = 4;
        this.baseMagicNumber = 6;
        this.cardsToPreview = new DarkBullet();
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new ParalyzePower(m));
        if (exhaustPile().size() >= this.baseMagicNumber) {
            AbstractCard c = new DarkBullet();
            if (this.upgraded)
                c.upgrade();
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
        upgradeBlock(3);
        AbstractCard c = new DarkBullet();
        c.upgrade();
        this.cardsToPreview = c;
    }
}