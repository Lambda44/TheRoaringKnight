package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.powers.EramPower;
import roaringknight.powers.StarStormPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Eram extends AbstractEasyCard {
    public final static String ID = makeID("Eram");

    public Eram() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.ERAM, 0.6f);
        applyToSelf(new EramPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}