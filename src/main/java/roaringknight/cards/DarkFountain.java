package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import roaringknight.RKMod;
import roaringknight.powers.DarkSalvationPower;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;
import roaringknight.vfx.FountainMakeEffect;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class DarkFountain extends AbstractEasyCard {
    public final static String ID = makeID("DarkFountain");

    public DarkFountain() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.baseSecondMagic = 1;
        this.tags.add(RKMod.TITAN);
        PurgeField.purge.set(this, true);
        this.tags.add(RKMod.STANCE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new FountainMakeEffect());
        applyToSelf(new TitanPower(p(), 1));
        if (p.hasPower(TitanPower.POWER_ID))
            applyToSelf(new StrengthPower(p(), this.baseMagicNumber));
    }

//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        boolean canUse = super.canUse(p, m);
//        if (!canUse) {
//            return false;
//        } else if (!p().hasPower(TitanPower.POWER_ID)) {
//            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
//            return false;
//        } else {
//            return canUse;
//        }
//    }

    public void triggerOnGlowCheck() {
        if (p().hasPower(TitanPower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        String keywordID = "roaringknight:the_roaring";
        return Collections.singletonList(
                new TooltipInfo(
                        BaseMod.getKeywordTitle(keywordID),
                        BaseMod.getKeywordDescription(keywordID)
                )
        );
    }
}