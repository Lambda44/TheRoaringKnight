package roaringknight.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.patches.EnumPatch;
import roaringknight.powers.TheRoaringPower;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.p;

public class CrystalNova extends AbstractEasyCard {
    public final static String ID = makeID("CrystalNova");

    public CrystalNova() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        this.baseMagicNumber = 3;
        this.purgeOnUse = true;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.baseMagicNumber; i++) {
            allDmg(EnumPatch.RK_STAR);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}