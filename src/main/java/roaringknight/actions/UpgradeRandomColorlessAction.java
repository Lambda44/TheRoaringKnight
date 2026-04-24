package roaringknight.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static roaringknight.util.Wiz.hand;
import static roaringknight.util.Wiz.p;

public class UpgradeRandomColorlessAction extends AbstractGameAction {
    private int num;

    public UpgradeRandomColorlessAction(int num) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.num = num;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (hand().group.size() <= 0) {
                this.isDone = true;
            } else {
                CardGroup upgradeable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for(AbstractCard c : hand().group) {
                    if (c.canUpgrade() && c.type != AbstractCard.CardType.STATUS && c.color == AbstractCard.CardColor.COLORLESS) {
                        upgradeable.addToTop(c);
                    }
                }

                while (upgradeable.size() > 0 && this.num > 0) {
                    upgradeable.shuffle();
                    ((AbstractCard)upgradeable.group.get(0)).upgrade();
                    ((AbstractCard)upgradeable.group.get(0)).superFlash();
                    ((AbstractCard)upgradeable.group.get(0)).applyPowers();
                    upgradeable.group.remove(0);
                    this.num--;
                }

                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }

}