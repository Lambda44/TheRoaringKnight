package roaringknight.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.RoaringKnight;
import roaringknight.powers.ParalyzePower;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToEnemy;

public class ParalysisPotion extends AbstractEasyPotion {
    public static String ID = makeID("ParalysisPotion");

    public ParalysisPotion() {
        super(ID, PotionRarity.COMMON, PotionSize.T, new Color(1.0f, 1.0f, 0.0f, 1f), new Color(1.0f, 0.8f, 0.0f, 1f), null, RoaringKnight.Enums.THE_ROARING_KNIGHT, RKMod.characterColor);
        this.isThrown = true;
        this.targetRequired = true;
    }

    public int getPotency(int ascensionlevel) {
        return -1;
    }

    public void use(AbstractCreature creature) {
        applyToEnemy((AbstractMonster)creature, new ParalyzePower(creature));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0];
    }

    public void addAdditionalTips() {
        tips.add(new PowerTip(BaseMod.getKeywordTitle(makeID("paralyze")), BaseMod.getKeywordDescription(makeID("paralyze"))));
    }
}