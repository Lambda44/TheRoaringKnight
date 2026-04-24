package roaringknight.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.cards.Star;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class WhirlingVisionPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("WhirlingVision");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public WhirlingVisionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = amount;
        this.updateDescription();
    }

    public void stackPower(int amt) {
        super.stackPower(amt);
        this.amount2 += amt;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.amount2 = this.amount;
        this.updateDescription();
    }

    public void onExhaust(AbstractCard card) {
        if (this.amount2 > 0) {
            this.flash();
            AbstractMonster mo = getRandomEnemy();
            applyToEnemyTop(mo, new SwoonPower(mo));
            this.amount2--;
            this.updateDescription();
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            if (this.amount2 == 1) {
                this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
            } else {
                this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[2];
            }
        } else {
            if (this.amount2 == 1) {
                this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount2 + DESCRIPTIONS[5];
            } else {
                this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount2 + DESCRIPTIONS[6];
            }
        }
    }
}
