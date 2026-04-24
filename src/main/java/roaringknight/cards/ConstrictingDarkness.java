package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.util.ProAudio;
import roaringknight.vfx.BulletEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ConstrictingDarkness extends AbstractEasyCard {
    public final static String ID = makeID("ConstrictingDarkness");

    public ConstrictingDarkness() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = 7;
        this.baseSecondMagic = 1;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.BULLET);
        vfx(new BulletEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new StrengthPower(m, -this.baseSecondMagic));
        if (exhaustPile().size() >= this.baseMagicNumber) {
            applyToSelf(new StrengthPower(p, this.baseSecondMagic));
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
        upgradeDamage(3);
        upgradeMagicNumber(-2);
    }
}