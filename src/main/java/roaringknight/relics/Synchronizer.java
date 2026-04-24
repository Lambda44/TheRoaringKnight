package roaringknight.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RoaringKnight;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Synchronizer extends AbstractEasyRelic implements OnReceivePowerRelic {
    public static final String ID = makeID("Synchronizer");

    public Synchronizer() {
        super(ID, RelicTier.SHOP, LandingSound.MAGICAL, RoaringKnight.Enums.KNIGHT_COLOR);
    }

    public AbstractRelic makeCopy() {
        return new Synchronizer();
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (abstractPower instanceof WeakPower && abstractCreature != p()) {
            this.flash();
            applyToEnemyTop((AbstractMonster)abstractCreature, new WeakPower(abstractCreature, abstractPower.amount, true));
        } else if (abstractPower instanceof VulnerablePower && abstractCreature != p()) {
            this.flash();
            applyToEnemyTop((AbstractMonster)abstractCreature, new VulnerablePower(abstractCreature, abstractPower.amount, true));
        } else if (abstractPower instanceof StrengthPower && abstractPower.amount < 0 && abstractCreature != p()) {
            this.flash();
            applyToEnemyTop((AbstractMonster)abstractCreature, new StrengthPower(abstractCreature, abstractPower.amount));
        }
        return true;
    }
}
