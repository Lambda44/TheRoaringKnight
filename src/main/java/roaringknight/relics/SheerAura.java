package roaringknight.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.applyToEnemy;

public class SheerAura extends AbstractEasyRelic {
    public static final String ID = makeID("SheerAura");

    public SheerAura() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new SheerAura();
    }
}
