package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.WarpAction;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class DarkRipple extends AbstractEasyCard {
    public final static String ID = makeID("DarkRipple");

    public DarkRipple() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
        this.baseMagicNumber = 3;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new WarpAction(true));
        if (pwrAmt(p, TitanPower.POWER_ID) >= this.baseMagicNumber) {
            atb(new BetterDiscardPileToHandAction(1));
        }
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
        upgradeBlock(3);
        upgradeMagicNumber(-1);
    }
}