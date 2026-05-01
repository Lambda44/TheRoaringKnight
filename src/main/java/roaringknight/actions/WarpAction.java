package roaringknight.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import roaringknight.RKMod;
import roaringknight.powers.TwistedHeartbeatPower;
import roaringknight.relics.BlackHole;

import java.util.ArrayList;

import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.p;

public class WarpAction extends AbstractGameAction {
    private boolean prioritizeShadow;
    private int reduceCost;

    public WarpAction(boolean ps) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.prioritizeShadow = ps;
        this.reduceCost = 0;
    }

    public WarpAction(boolean ps, int reduction) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.prioritizeShadow = ps;
        this.reduceCost = reduction;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p().hasRelic(BlackHole.ID) && p().getRelic(BlackHole.ID).counter == 1 && this.prioritizeShadow) {
                p().getRelic(BlackHole.ID).flash();
                p().getRelic(BlackHole.ID).setCounter(0);
                p().getRelic(BlackHole.ID).stopPulse();
                atb(new SelectWarpAction(this.reduceCost));
                this.isDone = true;
            } else if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (p().exhaustPile.isEmpty()) {
                this.isDone = true;
            } else {
                ArrayList<AbstractCard> shadowcards = new ArrayList<>();
                ArrayList<AbstractCard> ep = new ArrayList<>();
                for(AbstractCard c : p().exhaustPile.group) {
                    if (!c.hasTag(AbstractCard.CardTags.HEALING)) {
                        if (c.hasTag(RKMod.SHADOW)) {
                            shadowcards.add(c);
                        }
                        ep.add(c);
                    }
                }
                AbstractCard card;

                if (prioritizeShadow && !shadowcards.isEmpty()) {
                    card = (AbstractCard) shadowcards.get(AbstractDungeon.cardRng.random(0, shadowcards.size() - 1));
                } else {
                    card = (AbstractCard) ep.get(AbstractDungeon.cardRng.random(0, ep.size() - 1));
                }
                card.unfadeOut();
                p().hand.addToHand(card);
                if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                    atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
                }
                if (p().hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
                    card.setCostForTurn(-9);
                } else if (reduceCost != 0) {
                    card.setCostForTurn(card.makeStatEquivalentCopy().cost - reduceCost);
                }
                p().exhaustPile.removeCard(card);
                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }
}
