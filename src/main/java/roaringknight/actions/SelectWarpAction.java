package roaringknight.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import roaringknight.RKMod;
import roaringknight.powers.TwistedHeartbeatPower;

import static roaringknight.util.Wiz.*;

public class SelectWarpAction extends AbstractGameAction {
    private int reduceCost;
    private AbstractPlayer p;

    public SelectWarpAction(int reduce) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.reduceCost = reduce;
        this.p = AbstractDungeon.player;
    } //yes i know this is basically just a copy of ExhumeAction but no way im trying to get the reduce cost mechanic to work within that

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (p().exhaustPile.isEmpty()) {
                this.isDone = true;
            } else if (p().exhaustPile.size() == 1) {
                AbstractCard c = this.p.exhaustPile.getTopCard();
                c.unfadeOut();
                this.p.hand.addToHand(c);
                if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                    atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
                }
                if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                    c.setCostForTurn(-9);
                } else if (reduceCost != 0) {
                    c.setCostForTurn(c.makeStatEquivalentCopy().cost - reduceCost);
                }
                this.p.exhaustPile.removeCard(c);

                c.unhover();
                c.fadingOut = false;
                this.isDone = true;
            } else {
                for(AbstractCard c : this.p.exhaustPile.group) {
                    c.stopGlowing();
                    c.unhover();
                    c.unfadeOut();
                }
                AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, "Select a Card to Return to Your Hand", false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    this.p.hand.addToHand(c);
                    if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                        atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
                    }
                    if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                        c.setCostForTurn(-9);
                    } else if (reduceCost != 0) {
                        c.setCostForTurn(c.makeStatEquivalentCopy().cost - reduceCost);
                    }
                    this.p.exhaustPile.removeCard(c);

                    c.unhover();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();

                for(AbstractCard c : this.p.exhaustPile.group) {
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }
            }

            this.tickDuration();
        }
    }
}
