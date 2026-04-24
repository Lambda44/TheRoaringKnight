package roaringknight.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class CrystalBarrage extends AbstractEasyCard {
    public final static String ID = makeID("CrystalBarrage");

    public CrystalBarrage() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.baseSecondMagic = 6;
        this.cardsToPreview = new Star();
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        makeInHand(new Star(), this.baseMagicNumber);
        if (exhaustPile().size() >= this.baseSecondMagic) {
            makeInHand(new Star(), this.baseMagicNumber);
        }
    }

    public void triggerOnGlowCheck() {
        if (exhaustPile().size() >= this.baseSecondMagic) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(-4);
    }
}