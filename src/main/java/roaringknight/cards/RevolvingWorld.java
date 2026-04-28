package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import roaringknight.RKMod;
import roaringknight.powers.RevolvingWorldPower;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class RevolvingWorld extends AbstractEasyCard {
    public final static String ID = makeID("RevolvingWorld");

    public RevolvingWorld() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RevolvingWorldPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}