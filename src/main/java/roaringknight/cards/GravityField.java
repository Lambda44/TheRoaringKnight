package roaringknight.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import roaringknight.RKMod;
import roaringknight.powers.GravityFieldPower;
import roaringknight.powers.SwoonPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class GravityField extends AbstractEasyCard {
    public final static String ID = makeID("GravityField");

    public GravityField() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 1;
        this.exhaust = true;
        this.tags.add(RKMod.HAND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.STAR_BARRAGE, 2.5f);
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemyFast(mo, new SwoonPower(mo));
        }
        applyToSelf(new GravityFieldPower(p, this.baseMagicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}