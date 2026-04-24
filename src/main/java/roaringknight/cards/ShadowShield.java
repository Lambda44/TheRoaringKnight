package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.WarpAction;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.atb;

public class ShadowShield extends AbstractEasyCard {
    public final static String ID = makeID("ShadowShield");

    public ShadowShield() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 14;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new WarpAction(true));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        String keywordID = "roaringknight:shadow";
        return Collections.singletonList(
                new TooltipInfo(
                        BaseMod.getKeywordTitle(keywordID),
                        BaseMod.getKeywordDescription(keywordID)
                )
        );
    }
}