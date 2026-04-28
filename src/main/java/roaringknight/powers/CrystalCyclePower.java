package roaringknight.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import roaringknight.cards.Star;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class CrystalCyclePower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("CrystalCycle");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CrystalCyclePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
        this.updateExistingStars();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateExistingStars();
    }

    private void updateExistingStars() {
        for (AbstractCard c : hand().group) {
            if (c instanceof Star) {
                c.baseDamage = 4 + this.amount;
                c.baseMagicNumber = 4 + this.amount;
            }
        }
        for (AbstractCard c : drawPile().group) {
            if (c instanceof Star) {
                c.baseDamage = 4 + this.amount;
                c.baseMagicNumber = 4 + this.amount;
            }
        }
        for (AbstractCard c : discardPile().group) {
            if (c instanceof Star) {
                c.baseDamage = 4 + this.amount;
                c.baseMagicNumber = 4 + this.amount;
            }
        }
        for (AbstractCard c : exhaustPile().group) {
            if (c instanceof Star) {
                c.baseDamage = 4 + this.amount;
                c.baseMagicNumber = 4 + this.amount;
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : hand().group) {
            if (c instanceof Star) {
                c.baseDamage = 4 + this.amount;
                c.baseMagicNumber = 4 + this.amount;
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
