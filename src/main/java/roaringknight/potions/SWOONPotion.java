package roaringknight.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import roaringknight.RKMod;
import roaringknight.RoaringKnight;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SWOONPotion extends AbstractEasyPotion {
    public static String ID = makeID("SWOONPotion");

    public SWOONPotion() {
        super(ID, PotionRarity.UNCOMMON, PotionSize.H, new Color(0.5f, 0.0f, 0.6f, 1f), new Color(0.3f, 0.2f, 0.3f, 1f), null, RoaringKnight.Enums.THE_ROARING_KNIGHT, RKMod.characterColor);
        this.isThrown = true;
    }

    public int getPotency(int ascensionlevel) {
        return 2;
    }

    public void use(AbstractCreature creature) {
        for(AbstractMonster mo : getEnemies()) {
            atb(new RemoveSpecificPowerAction(mo, p(), "Artifact"));
        }
        for (int i = 0; i < this.potency; i++) {
            for(AbstractMonster mo : getEnemies()) {
                applyToEnemy(mo, new SwoonPower(mo));
            }
        }
    }

    public String getDescription() {
        boolean sacred = false;
        this.potency = this.getPotency();
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            sacred = true;
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        if (sacred) {
            return strings.DESCRIPTIONS[1];
        } else {
            return strings.DESCRIPTIONS[0];
        }
    }

    public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("swoon")), BaseMod.getKeywordDescription(makeID("swoon"))));
    }
}