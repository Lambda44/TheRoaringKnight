package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.patches.EnumPatch;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.exhaustPile;
import static roaringknight.util.Wiz.makeInHand;

public class StarBlast extends AbstractEasyCard {
    public final static String ID = makeID("StarBlast");

    public StarBlast() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, EnumPatch.RK_STAR_EXPLODE);
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += (this.baseMagicNumber * exhaustPile().size());
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += (this.baseMagicNumber * exhaustPile().size());
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}