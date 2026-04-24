package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;
import roaringknight.vfx.KnifeEffect;
import roaringknight.vfx.SwordEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class KnifeDance extends AbstractEasyCard {
    public final static String ID = makeID("KnifeDance");

    public KnifeDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new KnifeEffect(m));
        atb(new DamageCallbackAction(m, new DamageInfo(p(), this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, (dealt) -> {
            if (dealt > 0 && pwrAmt(p, TitanPower.POWER_ID) >= this.baseMagicNumber)
                atb(new GainBlockAction(p(), p(), dealt));
        }));
    }

    public void triggerOnGlowCheck() {
        if (pwrAmt(p(), TitanPower.POWER_ID) >= this.baseMagicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(-1);
    }
}