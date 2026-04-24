package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import roaringknight.RKMod;
import roaringknight.patches.EnumPatch;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class NEODarkOrb extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID("NEODarkOrb");

    public NEODarkOrb() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.baseMagicNumber = 4;
        this.tags.add(RKMod.SHADOW);
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, EnumPatch.RK_EXPLODE);
        applyToSelf(new NextTurnBlockPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(2);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new ExhaustSpecificCardAction(this, p().drawPile, true));
        return false;
    }
}