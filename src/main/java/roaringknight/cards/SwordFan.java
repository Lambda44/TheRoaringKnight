package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import roaringknight.RKMod;
import roaringknight.vfx.SwordEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SwordFan extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("SwordFan");

    public SwordFan() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 9;
        this.baseMagicNumber = 1;
        isMultiDamage = true;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new SwordEffect());
        allDmg(AbstractGameAction.AttackEffect.NONE);
        applyToSelf(new DrawCardNextTurnPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}