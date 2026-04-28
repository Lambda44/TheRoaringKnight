package roaringknight.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;

import static roaringknight.util.Wiz.hand;

public class UpgradeAllColorlessAction extends AbstractGameAction {
    public UpgradeAllColorlessAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (hand().group.size() <= 0) {
                this.isDone = true;
            } else {
                for(AbstractCard c : hand().group) {
                    if (c.canUpgrade() && c.type != AbstractCard.CardType.STATUS && c.color == AbstractCard.CardColor.COLORLESS) {
                        c.upgrade();
                    }
                }
                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }

}