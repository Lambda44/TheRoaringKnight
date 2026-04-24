package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.patches.EnumPatch;
import roaringknight.powers.TheRoaringPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class CrystalDance extends AbstractEasyCard {
    public final static String ID = makeID("CrystalDance");

    public CrystalDance() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        this.cardsToPreview = new Star();
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        dmgTop(m, EnumPatch.RK_STAR);
        atb(new SelectCardsInHandAction(99, "Exhaust (Crystal Dance)", true, true, c->true, (selectedCards) -> {
            int num = 0;
            if (!selectedCards.isEmpty()) {
                for (AbstractCard c : selectedCards) {
                    atb(new ExhaustSpecificCardAction(c, hand(), true));
                    num++;
                }
                if (num != 0)
                    makeInHand(new Star(), num);
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}