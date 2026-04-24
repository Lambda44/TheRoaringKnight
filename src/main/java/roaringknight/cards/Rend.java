package roaringknight.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.ParalyzePower;
import roaringknight.util.ProAudio;
import roaringknight.vfx.LineCutEffect;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class Rend extends AbstractEasyCard {
    public final static String ID = makeID("Rend");

    public Rend() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        this.tags.add(RKMod.WINDUP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.REND, 0.8f);
        vfx(new LineCutEffect(m, 0f, new Color(1f, 0f, 0f, 1f)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        playAudioV(ProAudio.REND, 0.8f);
        vfx(new LineCutEffect(m, -90f, new Color(1f, 0f, 0f, 1f)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new ParalyzePower(m));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}