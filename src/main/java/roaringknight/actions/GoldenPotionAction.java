package roaringknight.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

import static roaringknight.RoaringKnight.Enums.LIBRARY_COLOR;
import static roaringknight.util.Wiz.makeInHand;

public class GoldenPotionAction extends AbstractGameAction {
    public GoldenPotionAction(int numCopies) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = numCopies;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cardPool = CardLibrary.getCardList(LIBRARY_COLOR);
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : cardPool) {
                if (c.rarity == AbstractCard.CardRarity.RARE) {
                    group.addToBottom(c);
                }
            }
            AbstractDungeon.gridSelectScreen.open(group, 1, "Choose a Rare card to add into your hand.", false);
            this.tickDuration();
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                if (AbstractDungeon.player.hasPower("MasterRealityPower")) { //dunno if i need this
                    c.upgrade();
                }
                c.setCostForTurn(0);
                makeInHand(c, this.amount);
                this.isDone = true;
            }
            this.tickDuration();
        }
    }
}
