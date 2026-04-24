package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;
import roaringknight.vfx.LineCutEffect;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Colossus extends AbstractEasyCard {
    public final static String ID = makeID("Colossus");

    public Colossus() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.WINDUP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new LineCutEffect(m, 0f, new Color(1f, 1f, 1f, 1f)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if (p() != null && p().hasPower(TitanPower.POWER_ID)) {
            this.baseDamage += (p().getPower(TitanPower.POWER_ID).amount * this.baseMagicNumber);
        }
        super.applyPowers();
        if (p() != null && p().hasPower(TitanPower.POWER_ID)) {
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (p() != null && p().hasPower(TitanPower.POWER_ID)) {
            this.baseDamage += (p().getPower(TitanPower.POWER_ID).amount * this.baseMagicNumber);
        }
        super.calculateCardDamage(mo);
        if (p() != null && p().hasPower(TitanPower.POWER_ID)) {
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        String keywordID = "roaringknight:the_roaring";
        return Collections.singletonList(
                new TooltipInfo(
                        BaseMod.getKeywordTitle(keywordID),
                        BaseMod.getKeywordDescription(keywordID)
                )
        );
    }
}