package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.patches.EnumPatch;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Star extends AbstractEasyCard {
    public final static String ID = makeID("Star");

    public Star() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 4;
        this.baseMagicNumber = 4;
        isEthereal = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, EnumPatch.RK_STAR);
    }

    public void triggerOnExhaust() {
        AbstractMonster mo = getRandomEnemy();
        if (mo != null)
            thornDmgTop(mo, this.baseMagicNumber, EnumPatch.RK_STAR_EXPLODE);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}