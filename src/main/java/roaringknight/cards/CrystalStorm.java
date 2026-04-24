package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class CrystalStorm extends AbstractEasyCard {
    public final static String ID = makeID("CrystalStorm");

    public CrystalStorm() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = 2;
        cardsToPreview = new Star();
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        makeInHand(new Star(), this.baseMagicNumber);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}