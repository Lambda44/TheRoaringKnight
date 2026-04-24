package roaringknight.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TitanPower;
import roaringknight.util.ProAudio;
import roaringknight.vfx.BulletEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class HomingShards extends AbstractEasyCard {
    public final static String ID = makeID("HomingShards");

    public HomingShards() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.cardsToPreview = new Star();
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudio(ProAudio.BULLET);
        for(AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped()) {
                vfx(new BulletEffect(m));
                dmg(m, AbstractGameAction.AttackEffect.NONE);
            }
        }
        makeInHand(new Star());
        exhaustPile().addToBottom(new Star());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}