package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import roaringknight.RKMod;
import roaringknight.powers.DarkSalvationPower;
import roaringknight.powers.ShadowSerpentPower;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class ShadowSerpent extends AbstractEasyCard {
    public final static String ID = makeID("ShadowSerpent");

    public ShadowSerpent() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.tags.add(RKMod.TITAN);
        this.purgeOnUse = true;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TitanPower(p(), 1));
        atb(new SelectCardsAction(exhaustPile().group, 1, "Choose A Card to Gain 2 Copies of", false, c->true, (selectedCard) -> {
            applyToSelf(new ShadowSerpentPower(p(), this.baseMagicNumber, selectedCard.get(0)));
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
        upgradeBaseCost(2);
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