package roaringknight.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import roaringknight.RKMod;
import roaringknight.powers.DarkSalvationPower;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;
import roaringknight.util.ProAudio;
import roaringknight.vfx.TitanSlashEffect;

import java.util.Collections;
import java.util.List;

import static roaringknight.RKMod.makeID;
import static roaringknight.util.Wiz.*;

public class TitanSlash extends AbstractEasyCard {
    public final static String ID = makeID("TitanSlash");

    public TitanSlash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        this.baseMagicNumber = 2;
        this.isMultiDamage = true;
        this.tags.add(RKMod.TITAN);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playAudioV(ProAudio.REND, 0.8f);
        vfx(new TitanSlashEffect(), 0.3f);
        applyToSelf(new TitanPower(p(), 1));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        for(AbstractMonster mo : getEnemies()) {
            applyToEnemy(mo, new VulnerablePower(mo, this.baseMagicNumber, false));
        }
    }

    public void triggerOnGlowCheck() {
        if (p().hasPower(TheRoaringPower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        String keywordID = "roaringknight:the_roaring";
        return Collections.singletonList(
                new TooltipInfo(
                        BaseMod.getKeywordTitle(keywordID),
                        BaseMod.getKeywordDescription(keywordID)
                )
        );
    }
}