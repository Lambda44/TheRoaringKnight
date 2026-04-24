package roaringknight.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.patches.EnumPatch;
import roaringknight.vfx.SwoonEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SwoonPower extends AbstractEasyPower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID("Swoon");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public SwoonPower(AbstractCreature owner) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, 5);
        this.isTwoAmount = true;
        checkRW();
    }

    public void onInitialApplication() {
        checkRW();
    }

    public void stackPower(int amt) {
        this.fontScale = 8.0F;
        checkRW();
        this.flash();
        atb(new DamageAction(this.owner, new DamageInfo(p(), this.amount2, DamageInfo.DamageType.HP_LOSS), EnumPatch.RK_EXPLODE));
    }

    public void atStartOfTurn() {
        this.flash();
        atb(new DamageAction(this.owner, new DamageInfo(p(), this.amount, DamageInfo.DamageType.HP_LOSS), EnumPatch.RK_EXPLODE));
        removePower(this);
    }

    public void onDeath() {
        super.onDeath();
        AbstractDungeon.effectsQueue.add(new SwoonEffect((AbstractMonster)this.owner));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }

    private void checkRW() {
        this.amount2 = (this.amount * 2);
        this.updateDescription();
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return Color.PURPLE;
    }
}
