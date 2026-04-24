package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import roaringknight.RKMod;
import roaringknight.actions.EasyModalChoiceAction;

import java.util.ArrayList;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ConjureDarkness extends AbstractEasyCard {
    public final static String ID = makeID(ConjureDarkness.class.getSimpleName());

    public ConjureDarkness() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 7;
        this.baseSecondMagic = 3;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (exhaustPile().size() >= this.baseMagicNumber) {
            applyToSelf(new PlatedArmorPower(p, this.baseSecondMagic));
            applyToSelf(new ThornsPower(p, this.baseSecondMagic));
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
        upgradeSecondMagic(1);
    }
}