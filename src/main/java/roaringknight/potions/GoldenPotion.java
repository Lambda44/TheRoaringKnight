package roaringknight.potions;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.RoaringKnight;
import roaringknight.actions.GoldenPotionAction;
import roaringknight.powers.SwoonPower;

import java.util.ArrayList;

import static roaringknight.RKMod.makeID;
import static roaringknight.RoaringKnight.Enums.LIBRARY_COLOR;
import static roaringknight.util.Wiz.applyToEnemy;
import static roaringknight.util.Wiz.atb;

public class GoldenPotion extends AbstractEasyPotion {
    public static String ID = makeID("GoldenPotion");
    private boolean pickCard = false;

    public GoldenPotion() {
        super(ID, PotionRarity.RARE, PotionSize.EYE, new Color(1.0f, 1.0f, 0.0f, 1f), new Color(1.0f, 0.8f, 0.0f, 1f), null, RoaringKnight.Enums.THE_ROARING_KNIGHT, RKMod.characterColor);
    }

    public int getPotency(int ascensionlevel) {
        return 1;
    }

    public void use(AbstractCreature creature) {
        atb(new GoldenPotionAction(this.potency));
    }

//    public void initializeData() {
//        this.potency = this.getPotency();
//        strings = CardCrawlGame.languagePack.getPotionString(ID);
//        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
//            this.description = strings.DESCRIPTIONS[1];
//        } else {
//            this.description = strings.DESCRIPTIONS[0];
//        }
//
//        this.tips.clear();
//        this.tips.add(new PowerTip(this.name, this.description));
//    }

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
}