package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class TacticalDodge extends AbstractEasyCard {
    public final static String ID = makeID("TacticalDodge");

    public TacticalDodge() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.STANCE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(this.baseMagicNumber, false, false, false));
        for (int i = 0; i < this.baseMagicNumber; i++) {
            blck();
        }
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }
}