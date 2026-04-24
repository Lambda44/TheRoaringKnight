package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Lightheaded extends AbstractEasyCard {
    public final static String ID = makeID("Lightheaded");

    public Lightheaded() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.cardsToPreview = new Star();
        exhaust = true;
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        makeInHand(new Star(), this.baseMagicNumber);
        for (int i = 0; i < this.baseMagicNumber; i++) {
            exhaustPile().addToBottom(new Star());
        }
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}