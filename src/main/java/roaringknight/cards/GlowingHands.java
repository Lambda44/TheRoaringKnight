package roaringknight.cards;

import basemod.BaseMod;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.patches.EnumPatch;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;
import static roaringknight.util.Wiz.p;

public class GlowingHands extends AbstractEasyCard {
    public final static String ID = makeID("GlowingHands");

    public GlowingHands() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.TITAN);
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TitanPower(p(), 1));
        dmg(m, EnumPatch.RK_GLOWING_HANDS);
        atb(new SelectCardsInHandAction(1, "Gain Retain (Glowing Hands)", false, false, c->true, (selectedCards) -> {
            CardModifierManager.addModifier(selectedCards.get(0), new RetainMod());
        }));
    }

    public void triggerOnGlowCheck() {
        if (p().hasPower(TheRoaringPower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
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