package roaringknight.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import roaringknight.relics.SheerAura;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ParalyzePower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("Paralyze");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public ParalyzePower(AbstractCreature owner) {
        super(POWER_ID, NAME, TYPE, TURN_BASED, owner, -1);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return !this.owner.isPlayer && p().hasRelic(SheerAura.ID) ? damage * 0.4F : damage * 0.6F;
        } else {
            return damage;
        }
    }

    public void atEndOfRound() {
        removePower(this);
    }

    public void updateDescription() {
        if (p().hasRelic(SheerAura.ID)) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }
}
