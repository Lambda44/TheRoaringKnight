package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.StarStormPower;
import roaringknight.powers.SwarmPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class StarStorm extends AbstractEasyCard {
    public final static String ID = makeID("StarStorm");

    public StarStorm() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        isInnate = false;
        this.cardsToPreview = new Star();
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        applyToSelf(new StarStormPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
    }
}