package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.WarpAction;
import roaringknight.vfx.LineCutEffect;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.vfx;

public class SwiftSlash extends AbstractEasyCard {
    public final static String ID = makeID("SwiftSlash");

    public SwiftSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.WINDUP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new LineCutEffect(m, 0f, new Color(1f, 1f, 1f, 1f)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new WarpAction(true, this.baseMagicNumber));
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