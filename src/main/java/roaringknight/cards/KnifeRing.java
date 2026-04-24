package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.EasyXCostAction;
import roaringknight.actions.UpgradeRandomColorlessAction;
import roaringknight.powers.RevolvingWorldPower;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class KnifeRing extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("KnifeRing");

    public KnifeRing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.SHADOW);
        AbstractCard c = new DarkBullet();
        c.upgrade();
        this.cardsToPreview = c;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new DarkBullet();
        c.upgrade();
        makeInHand(c, this.baseMagicNumber);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}