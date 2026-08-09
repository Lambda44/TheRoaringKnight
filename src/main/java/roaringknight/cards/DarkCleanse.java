package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class DarkCleanse extends AbstractEasyCard {
    public final static String ID = makeID("DarkCleanse");

    public DarkCleanse() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(RKMod.LAIDBACK);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(99, "Exhaust (Dark Cleanse)", true, true, c->true, (selectedCards) -> {
            if (!selectedCards.isEmpty()) {
                int s = selectedCards.size();
                for (AbstractCard c : selectedCards) {
                    atb(new ExhaustSpecificCardAction(c, hand(), true));
                }
                atb(new DrawCardAction(p(), s));
            }
        }));
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }
}