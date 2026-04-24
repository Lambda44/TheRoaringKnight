package roaringknight.cards;

import basemod.helpers.BaseModCardTags;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.RoaringFormPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToSelf;
import static roaringknight.util.Wiz.playAudioV;

public class RoaringForm extends AbstractEasyCard {
    public final static String ID = makeID("RoaringForm");

    public RoaringForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.isEthereal = true;
        this.tags.add(BaseModCardTags.FORM);
        this.cardsToPreview = new Prophecy();
        this.tags.add(RKMod.CHARGEUP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.CHARGEUP, 0.8f);
        applyToSelf(new RoaringFormPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}