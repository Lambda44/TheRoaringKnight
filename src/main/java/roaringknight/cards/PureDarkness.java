package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class PureDarkness extends AbstractEasyCard {
    public final static String ID = makeID("PureDarkness");

    public PureDarkness() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        this.isEthereal = true;
        this.baseMagicNumber = 1;
        PurgeField.purge.set(this, true);
        this.tags.add(RKMod.LAIDBACK);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IntangiblePlayerPower(p, 1));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}