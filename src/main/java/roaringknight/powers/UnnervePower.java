package roaringknight.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.cards.DarkBullet;

import java.util.Iterator;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class UnnervePower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("Unnerve");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UnnervePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, amount);
    }

    public void atStartOfTurn() {
        Iterator<AbstractCard> c = hand().group.iterator();
        while (c.hasNext()) {
            AbstractCard derp = (AbstractCard) c.next();
            if (derp instanceof DarkBullet && derp.canUpgrade()) {
                derp.upgrade();
            }
        }
        makeInHand(new DarkBullet(), this.amount);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}
