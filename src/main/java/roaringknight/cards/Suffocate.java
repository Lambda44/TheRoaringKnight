package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.actions.UpgradeAllColorlessAction;
import roaringknight.actions.UpgradeRandomColorlessAction;
import roaringknight.powers.TitanPower;
import roaringknight.util.ProAudio;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Suffocate extends AbstractEasyCard {
    public final static String ID = makeID("Suffocate");

    public Suffocate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        MultiCardPreview.add(this, new DarkBullet(), new Star());
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        makeInHand(new DarkBullet());
        makeInHand(new Star(), this.baseMagicNumber);
        atb(new UpgradeAllColorlessAction());
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}