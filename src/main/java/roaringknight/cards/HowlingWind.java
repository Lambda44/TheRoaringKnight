package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import roaringknight.RKMod;
import roaringknight.powers.HowlingWindPower;
import roaringknight.powers.ParalyzePower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class HowlingWind extends AbstractEasyCard {
    public final static String ID = makeID("HowlingWind");

    public HowlingWind() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 1;
        PurgeField.purge.set(this, true);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemyFast(mo, new ParalyzePower(mo));
        }
        applyToSelf(new HowlingWindPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}