package roaringknight.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import roaringknight.RKMod;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;
import static roaringknight.util.Wiz.p;

public class NarrowedVision extends AbstractEasyCard {
    public final static String ID = makeID("NarrowedVision");

    public NarrowedVision() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 4;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemy(mo, new SwoonPower(mo));
        }
        atb(new ScryAction(this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}