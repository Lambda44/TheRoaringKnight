package roaringknight.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ShadowSerpentPower extends AbstractEasyPower implements NonStackablePower {
    public static final String POWER_ID = makeID("ShadowSerpent");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private AbstractCard card;

    public ShadowSerpentPower(AbstractCreature owner, int amount, AbstractCard copyMe) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount); //amount is the amount of cards itll make

        this.card = copyMe.makeStatEquivalentCopy();
        this.card.resetAttributes();
        this.updateDescription();
    }

    public void atStartOfTurn() {
        makeInHand(this.card, this.amount);
        removePower(this);
    }

    public void updateDescription() {
        if (this.card == null) { //to prevent the super constructor from crashing the game
            this.description = DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + " copies of " + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1];
        }
    }

    @Override
    public boolean isStackable(AbstractPower po) {
        return false;
    }
}
