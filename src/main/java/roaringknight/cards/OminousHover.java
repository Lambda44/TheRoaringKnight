package roaringknight.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import roaringknight.RKMod;
import roaringknight.powers.OminousHoverPower;
import roaringknight.powers.SwoonPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class OminousHover extends AbstractEasyCard {
    public final static String ID = makeID("OminousHover");

    public OminousHover() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        this.baseMagicNumber = 1;
        this.tags.add(RKMod.STANCE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new OminousHoverPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}