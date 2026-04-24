package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.WarpAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Wormhole extends AbstractEasyCard {
    public final static String ID = makeID("Wormhole");

    public Wormhole() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 2;
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new WarpAction(this.upgraded));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        String keywordID = "roaringknight:shadow";
        if (this.upgraded) {
            return Collections.singletonList(
                    new TooltipInfo(
                            BaseMod.getKeywordTitle(keywordID),
                            BaseMod.getKeywordDescription(keywordID)
                    )
            );
        } else {
            return new ArrayList<>();
        }
    }
}