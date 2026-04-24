package roaringknight.actions;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import roaringknight.RKMod;
import roaringknight.powers.EramPower;
import roaringknight.powers.TwistedHeartbeatPower;
import roaringknight.relics.BlackHole;

import java.util.ArrayList;

import static roaringknight.util.Wiz.*;

public class EramAction extends AbstractGameAction {

    public EramAction(int amt) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amt;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (p().exhaustPile.isEmpty()) {
                this.isDone = true;
            } else {
                ArrayList<AbstractCard> shadowcards = new ArrayList<>();
                for(AbstractCard c : p().exhaustPile.group) {
                    if (c.hasTag(RKMod.SHADOW)) {
                        shadowcards.add(c);
                    }
                }
                if (!shadowcards.isEmpty()) {
                    p().getPower(EramPower.POWER_ID).flash();
                    if (shadowcards.size() == 1) {
                        AbstractCard card = shadowcards.get(0);
                        card.unfadeOut();
                        p().hand.addToHand(card);
                        if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                            atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
                        }
                        if (p().hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
                            card.setCostForTurn(-9);
                        }
                        p().exhaustPile.removeCard(card);
                        shadowcards.remove(card);
                    } else {
                        AbstractCard card = (AbstractCard) shadowcards.get(AbstractDungeon.cardRng.random(0, shadowcards.size() - 1));
                        card.unfadeOut();
                        p().hand.addToHand(card);
                        if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                            atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
                        }
                        if (p().hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
                            card.setCostForTurn(-9);
                        }
                        p().exhaustPile.removeCard(card);
                        shadowcards.remove(card);
                        if (this.amount > 1) {
                            atb(new EramAction(this.amount - 1));
                        }
                    }
                }

                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }
}
