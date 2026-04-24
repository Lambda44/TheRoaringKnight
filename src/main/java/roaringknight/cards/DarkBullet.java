package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.SwoonPower;
import roaringknight.util.ProAudio;
import roaringknight.vfx.BulletEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class DarkBullet extends AbstractEasyCard {
    public final static String ID = makeID("DarkBullet");

    public DarkBullet() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 1;
        selfRetain = true;
        exhaust = true;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.BULLET);
        vfx(new BulletEffect(m));
        if (this.upgraded || !m.hasPower(SwoonPower.POWER_ID))
            applyToEnemy(m, new SwoonPower(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
    }
}