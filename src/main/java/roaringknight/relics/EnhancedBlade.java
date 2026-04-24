package roaringknight.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import roaringknight.RKMod;
import roaringknight.RoaringKnight;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class EnhancedBlade extends AbstractEasyRelic {
    public static final String ID = makeID("EnhancedBlade");

    public EnhancedBlade() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, RoaringKnight.Enums.KNIGHT_COLOR);
    }

    public void atBattleStartPreDraw() {
        this.beginLongPulse();
    }

    public void onVictory() {
        this.grayscale = false;
        this.stopPulse();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(RKMod.TITAN) && !this.grayscale) {
            this.flash();
            atb(new RelicAboveCreatureAction(p(), this));
            applyToSelf(new StrengthPower(p(), 1));
            applyToSelf(new DexterityPower(p(), 1));
            atb(new GainBlockAction(p(), p(), 8));
            this.grayscale = true;
            this.stopPulse();
        }
    }

    public AbstractRelic makeCopy() {
        return new EnhancedBlade();
    }
}
