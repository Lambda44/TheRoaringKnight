package roaringknight.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import roaringknight.RKMod;
import roaringknight.powers.SwoonPower;
import roaringknight.powers.TheRoaringPower;
import roaringknight.vfx.SSlashEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class SanguineSlash extends AbstractEasyCard {
    public final static String ID = makeID("SanguineSlash");

    public SanguineSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 2;
        this.baseMagicNumber = 4;
        PurgeField.purge.set(this, true);
        this.tags.add(RKMod.SSLASH);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new SSlashEffect(m, new Color(1f, 0f, 0f, 1f)));
        for (int i = 0; i < this.baseMagicNumber; i++) {
            atb(new DamageCallbackAction(m, new DamageInfo(p(), this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, (dealt) -> {
                if (dealt > 0)
                    att(new HealAction(p, p, (dealt / 2)));
            }));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}