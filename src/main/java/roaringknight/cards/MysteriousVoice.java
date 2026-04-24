package roaringknight.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import roaringknight.RKMod;
import roaringknight.powers.TheRoaringPower;
import roaringknight.util.ProAudio;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class MysteriousVoice extends AbstractEasyCard {
    public final static String ID = makeID("MysteriousVoice");

    public MysteriousVoice() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 3;
        this.purgeOnUse = true;
        this.tags.add(RKMod.POINT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.SOUL_HURT, 1.5f);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new ExhumeAction(false));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}