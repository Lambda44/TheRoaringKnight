package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.actions.EasyXCostAction;
import roaringknight.powers.SwoonPower;
import roaringknight.vfx.SwordEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SwordTunnel extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID(SwordTunnel.class.getSimpleName());

    public SwordTunnel() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = 1;
        this.cardsToPreview = new DarkBullet();
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new SwordEffect());
        allDmg(AbstractGameAction.AttackEffect.NONE);
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemy(mo, new SwoonPower(mo));
        }
        AbstractCard c = new DarkBullet();
        if (this.upgraded)
            c.upgrade();
        makeInHand(c, this.baseMagicNumber);
    }

    @Override
    public void upp() {
        AbstractCard c = new DarkBullet();
        c.upgrade();
        this.cardsToPreview = c;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}