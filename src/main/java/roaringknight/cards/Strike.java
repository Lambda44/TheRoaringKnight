package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.vfx.KnifeEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.vfx;

public class Strike extends AbstractEasyCard {
    public final static String ID = makeID("Strike");
    // intellij stuff attack, enemy, basic, 6, 3,  , , , 

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new KnifeEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);

    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}