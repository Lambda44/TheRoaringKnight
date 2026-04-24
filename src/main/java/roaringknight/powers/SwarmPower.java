package roaringknight.powers;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import roaringknight.RKMod;

import java.util.Iterator;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SwarmPower extends AbstractEasyPower implements OnCreateCardInterface {
    public static final String POWER_ID = makeID("Swarm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SwarmPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
    }

    public void onInitialApplication() {
        addGroupTitanCR(hand());
        addGroupTitanCR(drawPile());
        addGroupTitanCR(discardPile());
        addGroupTitanCR(exhaustPile());
    }

    public void stackPower(int amt) {
        super.stackPower(amt);
        addGroupTitanCR(hand());
        addGroupTitanCR(drawPile());
        addGroupTitanCR(discardPile());
        addGroupTitanCR(exhaustPile());
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if (abstractCard.hasTag(RKMod.TITAN)) {
            abstractCard.modifyCostForCombat((-1 * this.amount));
        }
    }

    private void addGroupTitanCR(CardGroup cg) {
        Iterator<AbstractCard> i = cg.group.iterator();

        while(i.hasNext()) {
            AbstractCard e = (AbstractCard)i.next();
            if (e.hasTag(RKMod.TITAN)) {
                e.modifyCostForCombat(-1);
            }
        }
    }
}
