package roaringknight.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import roaringknight.RKMod;
import roaringknight.cards.Star;

import java.util.ArrayList;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.p;

public class GoldenStarsPower extends AbstractEasyPower implements OnCreateCardInterface {
    public static final String POWER_ID = makeID("GoldenStars");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GoldenStarsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if (abstractCard instanceof Star) {
            this.flashWithoutSound();
            abstractCard.upgrade();
        }
    }
}
