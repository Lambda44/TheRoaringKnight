package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.SwarmPower;
import roaringknight.powers.SwoonPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Swarm extends AbstractEasyCard {
    public final static String ID = makeID("Swarm");

    public Swarm() {
        super(ID, 3, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.isEthereal = true;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SwarmPower(p, this.baseMagicNumber));
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

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }
}