package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.SwoonPower;
import roaringknight.vfx.KnifeEffect;
import roaringknight.vfx.SwordEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SwordRing extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("SwordRing");

    public SwordRing() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        isMultiDamage = true;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new SwordEffect());
        allDmg(AbstractGameAction.AttackEffect.NONE);
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo.hasPower(SwoonPower.POWER_ID)) {
                vfx(new KnifeEffect(mo));
                atb(new DamageAction(mo, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            }
        }
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