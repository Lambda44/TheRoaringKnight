package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import roaringknight.RKMod;
import roaringknight.vfx.KnifeEffect;
import roaringknight.vfx.SwordEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class INVReduction extends AbstractEasyCard {
    public final static String ID = makeID("INVReduction");

    public INVReduction() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
        this.baseMagicNumber = 8; //darkness value
        this.baseSecondMagic = 3;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new SwordEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        vfx(new KnifeEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (exhaustPile().size() >= this.baseMagicNumber) {
            applyToEnemy(m, new VulnerablePower(m, this.secondMagic, false));
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
        upgradeDamage(2);
        upgradeMagicNumber(-4);
    }
}