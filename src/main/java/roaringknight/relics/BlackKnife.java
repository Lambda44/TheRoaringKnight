package roaringknight.relics;

import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import roaringknight.RoaringKnight;
import roaringknight.cards.Star;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.makeInHand;

public class BlackKnife extends AbstractEasyRelic {
    public static final String ID = makeID("BlackKnife");

    public BlackKnife() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, RoaringKnight.Enums.KNIGHT_COLOR);
        this.tips.add(new CardPowerTip(new Star()));
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        ++this.counter;
        if (this.counter <= 3) {
            this.flash();
            makeInHand(new Star());
        }

        if (this.counter == 3) {
            this.grayscale = true;
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public void onVictory() {
        this.counter = -1;
    }

    public AbstractRelic makeCopy() {
        return new BlackKnife();
    }
}
