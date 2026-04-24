package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.vfx.KnifeEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.makeInHand;
import static roaringknight.util.Wiz.vfx;

public class StarStrike extends AbstractEasyCard {
    public final static String ID = makeID("StarStrike");

    public StarStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.cardsToPreview = new Star();
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new KnifeEffect(m));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        makeInHand(new Star());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}