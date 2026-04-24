package roaringknight.powers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;
import roaringknight.RKMod;
import roaringknight.relics.Sanctuary;
import roaringknight.util.ProAudio;
import roaringknight.vfx.RoarFlashEffect;
import roaringknight.vfx.RoaringEyesEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class TheRoaringPower extends AbstractEasyPower implements OnCreateCardInterface {
    public static final String POWER_ID = makeID("TheRoaring");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final int amt = 4;
    private float eyeTimer = 1.0F;

    public TheRoaringPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
    }

    public void onInitialApplication() {
        if (p().hasPower(TitanPower.POWER_ID)) {
            p().getPower(TitanPower.POWER_ID).updateDescription();
        }
        setAllTitanCost();
    }

    public void atStartOfTurn() {
        setAllTitanCost();
        int boost = 0;
        if (this.owner.hasPower(RoaringFormPower.POWER_ID)) {
            boost = this.owner.getPower(RoaringFormPower.POWER_ID).amount;
        }
        applyToSelf(new StrengthPower(p(), amt + boost));
        applyToSelf(new DexterityPower(p(), amt + boost));
    }

    public void update(int slot) {
        super.update(slot);
        this.eyeTimer -= Gdx.graphics.getDeltaTime();
        if (this.eyeTimer < 0.0f) {
            this.eyeTimer = 1.0f;
            AbstractDungeon.effectsQueue.add(new RoaringEyesEffect());
        }
    }

    public void onCardDraw(AbstractCard card) {
        titanCheck(card);
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        titanCheck(abstractCard);
    }

    public void updateDescription() {
        int total = amt;
        if (this.owner.hasPower(RoaringFormPower.POWER_ID)) {
            total += this.owner.getPower(RoaringFormPower.POWER_ID).amount;

        }
        this.description = DESCRIPTIONS[0] + total + DESCRIPTIONS[1];
    }

    private void setAllTitanCost() {
        setGroupTitanCost(hand());
        setGroupTitanCost(drawPile());
        setGroupTitanCost(discardPile());
        setGroupTitanCost(exhaustPile());
    }

    private void setGroupTitanCost(CardGroup cg) {
        for (AbstractCard c : cg.group) {
            titanCheck(c);
        }
    }

    private void titanCheck(AbstractCard card) {
        if (card.hasTag(RKMod.TITAN)) {
            card.modifyCostForCombat(-9);
            card.setCostForTurn(-9);
        }
    }
}
