package roaringknight.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.actions.ModifyMagicAction;
import roaringknight.powers.SwoonPower;
import roaringknight.vfx.LineCutEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SOULGrip extends AbstractEasyCard {
    public final static String ID = makeID("SOULGrip");

    public SOULGrip() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = 4;
        this.baseMagicNumber = 2;
        this.baseSecondMagic = 1;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0; i < this.baseMagicNumber; i++) {
            applyToEnemy(m, new SwoonPower(m));
        }
        atb(new ModifyMagicAction(this.uuid, this.baseSecondMagic));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeSecondMagic(1);
    }
}