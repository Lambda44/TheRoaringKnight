package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SharpenBlade extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("SharpenBlade");

    public SharpenBlade() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.baseSecondMagic = 1;
        exhaust = true;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, this.baseMagicNumber));
        applyToSelf(new DexterityPower(p, this.baseMagicNumber));
        atb(new DrawCardAction(p, this.baseSecondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}