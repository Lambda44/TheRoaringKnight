package roaringknight.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import roaringknight.cards.Prophecy;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class TitanPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("Titan");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TitanPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = 7;
    }

    @Override
    public void stackPower(int amount) {
        this.amount += amount;
        this.amount2 = 7;
        if (this.amount >= this.amount2 && !p().hasPower(TheRoaringPower.POWER_ID)) {
            applyToSelf(new TheRoaringPower(p(), -1));
            updateDescription();
        }
    }

    public void updateDescription() {
        String desc = "";
        if (this.amount == 1) {
            desc += DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            desc += DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
        if (p().hasPower(TheRoaringPower.POWER_ID)) {
            desc += DESCRIPTIONS[4];
        } else {
            desc += DESCRIPTIONS[3];
        }

        this.description = desc;
    }
}
