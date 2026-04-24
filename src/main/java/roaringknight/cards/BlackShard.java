package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.util.ProAudio;
import roaringknight.vfx.BulletEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class BlackShard extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("BlackShard");

    public BlackShard() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.baseMagicNumber = 2;
        exhaust = true;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.BULLET);
        vfx(new BulletEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DrawCardAction(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}