package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.actions.WarpAction;
import roaringknight.vfx.KnifeEffect;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.vfx;

public class Abyss extends AbstractEasyCard {
    public final static String ID = makeID("Abyss");

    public Abyss() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new KnifeEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new WarpAction(true));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
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