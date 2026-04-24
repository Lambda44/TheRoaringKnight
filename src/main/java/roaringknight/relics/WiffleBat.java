package roaringknight.relics;

import basemod.BaseMod;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import roaringknight.RoaringKnight;
import roaringknight.cards.Star;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class WiffleBat extends AbstractEasyRelic {
    public static final String ID = makeID("WiffleBat");

    public WiffleBat() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, RoaringKnight.Enums.KNIGHT_COLOR);
    }

    public void atTurnStart() {
        this.flash();
        AbstractMonster mo = getRandomEnemy();
        applyToEnemy(mo, new SwoonPower(mo));
    }

    public AbstractRelic makeCopy() {
        return new WiffleBat();
    }
}
