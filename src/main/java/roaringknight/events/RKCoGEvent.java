package roaringknight.events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import roaringknight.cards.PureDarkness;

import java.util.ArrayList;
import java.util.List;

import static roaringknight.RKMod.makeID;

public class RKCoGEvent extends AbstractImageEvent {
    public static final String ID = makeID("CouncilOfGhosts");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_BODY_M = DESCRIPTIONS[0];
    private static final String ACCEPT_BODY = DESCRIPTIONS[1];
    private static final String EXIT_BODY = DESCRIPTIONS[2];
    private static final float HP_DRAIN = 0.5F;
    private int screenNum = 0;
    private int hpLoss = 0;
    private boolean higher_ascension = false;

    public RKCoGEvent() {
        super(NAME, INTRO_BODY_M, "images/events/ghost.jpg");
        this.hpLoss = MathUtils.ceil((float) AbstractDungeon.player.maxHealth * 0.5F);
        if (this.hpLoss >= AbstractDungeon.player.maxHealth) {
            this.hpLoss = AbstractDungeon.player.maxHealth - 1;
        }

        if (AbstractDungeon.ascensionLevel >= 15) {
            higher_ascension = true;
        }
        this.imageEventText.setDialogOption(OPTIONS[0] + (higher_ascension?3:5) + OPTIONS[1] + this.hpLoss + OPTIONS[2], new PureDarkness());
        this.imageEventText.setDialogOption(OPTIONS[3]);
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_GHOSTS");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(ACCEPT_BODY);
                        AbstractDungeon.player.decreaseMaxHealth(this.hpLoss);
                        this.becomeGhost();
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                    default:
                        this.imageEventText.updateBodyText(EXIT_BODY);
                        this.screenNum = 2;
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        return;
                }
            case 1:
                this.openMap();
                break;
            default:
                this.openMap();
        }
    }

    private void becomeGhost() {
        List<String> cards = new ArrayList();

        for(int i = 0; i < (higher_ascension?3:5); ++i) {
            AbstractCard c = new PureDarkness();
            cards.add(c.cardID);
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }
}
