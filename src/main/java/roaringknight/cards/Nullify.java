package roaringknight.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.vfx.LineCutEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Nullify extends AbstractEasyCard {
    public final static String ID = makeID("Nullify");

    public Nullify() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 14;
        this.baseMagicNumber = 8;
        this.baseSecondMagic = 16;
        this.purgeOnUse = true;
        this.tags.add(RKMod.WINDUP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new LineCutEffect(m, 0f, new Color(1f, 0f, 0f, 1f)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new RemoveSpecificPowerAction(m, p, "Artifact"));
        if (exhaustPile().size() >= this.baseMagicNumber) {
            applyToEnemy(m, new StrengthPower(m, -this.baseSecondMagic));
            applyToEnemy(m, new GainStrengthPower(m, this.baseSecondMagic));
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
        upgradeDamage(4);
        upgradeSecondMagic(9);
    }
}