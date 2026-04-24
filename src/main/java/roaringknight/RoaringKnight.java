package roaringknight;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.brashmonkey.spriter.Player;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.MysteriousSphere;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.MaskedBandits;
import com.megacrit.cardcrawl.events.exordium.DeadAdventurer;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import roaringknight.animation.CustomSpriterAnimation;
import roaringknight.animation.MyAnimationListener;
import roaringknight.cards.CrystalStorm;
import roaringknight.cards.Defend;
import roaringknight.cards.Strike;
import roaringknight.cards.Wormhole;
import roaringknight.patches.EnumPatch;
import roaringknight.powers.TheRoaringPower;
import roaringknight.powers.TitanPower;
import roaringknight.relics.BlackKnife;
import roaringknight.util.ProAudio;
import roaringknight.vfx.AfterImageEffect;
import roaringknight.vfx.RoarFlashEffect;

import java.util.ArrayList;
import java.util.List;

import static roaringknight.RKMod.makeCharacterPath;
import static roaringknight.RoaringKnight.Enums.KNIGHT_COLOR;
import static roaringknight.RKMod.*;
import static roaringknight.util.Wiz.*;

public class RoaringKnight extends CustomPlayer {
    static final String ID = makeID("RoaringKnight");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;
    private Player.PlayerListener currentListener;
    private float vfxTimer;
    private float resetTimer;
    private float spriteEffect;
    public static String STATE;
    private float fxoffset;
    private boolean sigdmg;
    private boolean hasRoared;
    private static boolean isRoaring;
    public static boolean bkbgm;
    public static AbstractRoom currRoom;
    public static boolean shouldRender;

    public RoaringKnight(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("knight/orb/vfx.png"), new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F}),
                (AbstractAnimation)new CustomSpriterAnimation(makeCharacterPath("knight/static.scml")));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 0.0F, -10.0F, 206.0F, 327.0F, new EnergyManager(3));


        this.currentListener = (Player.PlayerListener)new MyAnimationListener(this);
        ((CustomSpriterAnimation)this.animation).myPlayer.addListener(this.currentListener);
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
        resetTimer = vfxTimer = 0.1f;
        spriteEffect = 0.0f;
        STATE = "Idle";
        playAnimation("Idle");
        fxoffset = 0f;
        sigdmg = false;
        hasRoared = false;
        isRoaring = false;
        bkbgm = false;
        currRoom = null;
        shouldRender = true;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(CrystalStorm.ID);
        retVal.add(Wormhole.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BlackKnife.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playV(makeID(ProAudio.BATTLE_START.name()), 0.6F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    private static final String[] orbTextures = {
            makeCharacterPath("knight/orb/layer1.png"),
            makeCharacterPath("knight/orb/layer2.png"),
            makeCharacterPath("knight/orb/layer3.png"),
            makeCharacterPath("knight/orb/layer4.png"),
            makeCharacterPath("knight/orb/layer4.png"),
            makeCharacterPath("knight/orb/layer6.png"),
            makeCharacterPath("knight/orb/layer1d.png"),
            makeCharacterPath("knight/orb/layer2d.png"),
            makeCharacterPath("knight/orb/layer3d.png"),
            makeCharacterPath("knight/orb/layer4d.png"),
            makeCharacterPath("knight/orb/layer5d.png"),
    };

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return makeID(ProAudio.BATTLE_START.name());
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return KNIGHT_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new CrystalStorm();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new RoaringKnight(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_ROARING_KNIGHT;
        @SpireEnum(name = "KNIGHT_COLOR")
        public static AbstractCard.CardColor KNIGHT_COLOR;
        @SpireEnum(name = "KNIGHT_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public void update() {
        super.update();
        if (shouldRender) {
            this.vfxTimer -= Gdx.graphics.getDeltaTime();
            if (!isRoaring || !customRoar) {
                spriteEffect += (Gdx.graphics.getDeltaTime() * 2.0f);
                this.drawY += (float) (Math.sin(spriteEffect) / (3f * Settings.scale));
            }
            if (this.vfxTimer < 0.0F) {
                AbstractDungeon.effectsQueue.add(new AfterImageEffect(this.hb.x + fxoffset, this.hb.y + (20.0f * Settings.scale), isRoaring, this.flipHorizontal));
                this.vfxTimer = this.resetTimer;
            }
        }
    }

    public static void setAIRender() {
        if (currRoom != null && !(currRoom instanceof RestRoom) && (currRoom.event == null || currRoom.phase == AbstractRoom.RoomPhase.COMBAT || isCombatEvent(currRoom))) {
            shouldRender = true;
        } else {
            shouldRender = false;
        }

    }

    private static boolean isCombatEvent(AbstractRoom r) {
        if (r.event != null && (r.event instanceof DeadAdventurer || r.event instanceof Mushrooms || r.event instanceof MaskedBandits || r.event instanceof MysteriousSphere || r.event instanceof NeowEvent || r.event instanceof SpireHeart)) {
            return true; //i know neow isnt a combat event but i need to do it so the code works on floor 0; same w/ SpireHeart event
        } //i know this only gives compatibility for vanilla events but aint no way im getting this to work with modded combat events
        return false;
    }

    public void applyStartOfTurnRelics() {
        super.applyStartOfTurnRelics();
        sigdmg = false;
    }

    public void playAnimation(String name) {
        ((CustomSpriterAnimation)this.animation).myPlayer.setAnimation(name);
        changeFXOffset();
    }

    private void changeFXOffset() {
        if (this.flipHorizontal) {
            if (STATE.equals("LaidBack")) {
                fxoffset = (this.hb_w / 2f) - 10f;
            } else if (STATE.equals("Stance")) {
                fxoffset = (this.hb_w / 2f) - 70f;
            } else if (STATE.equals("Chargeup")) {
                fxoffset = (this.hb_w / 2f) - 45f;
            } else {
                fxoffset = 0f;
            }
        } else {
            fxoffset = 0f;
        }
    }

    public void preBattlePrep() {
        super.preBattlePrep();
        STATE = "Idle";
        playAnimation("Idle");
        playAudioV(ProAudio.BATTLE_START, 0.6f);
        spriteEffect = spriteEffect % (float)(Math.PI * 2);
        hasRoared = false;
        currRoom = AbstractDungeon.getCurrRoom();
        setAIRender();
    }

    public void onVictory() {
        if (bkbgm) {
            CardCrawlGame.music.fadeOutTempBGM();
            AbstractDungeon.scene.fadeInAmbiance();
            CardCrawlGame.music.unsilenceBGM();
            bkbgm = false;
        }
        super.onVictory();
    }

    @Override
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        RKuseCard(c, monster, energyOnUse);
        if (aboutToRoar(c)) {
            isRoaring = hasRoared = true;
            if (customRoar) {
                STATE = "Roaring";
                playAnimation("Roaring1");
                AbstractDungeon.effectsQueue.add(new RoarFlashEffect());
            } else {
                startBlackKnifeBGM();
            }
        } else if (!isRoaring) {
            if (c.hasTag(LAIDBACK)) {
                STATE = "LaidBack";
                playAnimation("LaidBack");
            } else if (c.hasTag(HAND)) {
                STATE = "Hand";
                playAnimation("Hand");
            } else if (c.hasTag(POINT)) {
                STATE = "Point";
                playAnimation("Point");
            } else if (c.hasTag(STANCE)) {
                STATE = "Stance";
                playAnimation("Stance");
            } else if (c.hasTag(WINDUP)) {
                STATE = "Windup";
                playAnimation("Windup");
            } else if (c.hasTag(CHARGEUP)) {
                STATE = "Chargeup";
                playAnimation("Chargeup");
            } else if (c.hasTag(SSLASH)) {
                STATE = "SS_Windup";
                playAnimation("SS_Windup");
            }
        }
    }

    private boolean aboutToRoar(AbstractCard c) { //check amount should always be 1 less than the amount needed to proc Roaring
        return p().hasPower(TitanPower.POWER_ID) && p().getPower(TitanPower.POWER_ID).amount == 6 && c.hasTag(TITAN) && !hasRoared;
    }

    //identical to AbstractPlayer's useCard except without the c.type == Attack if check
    private void RKuseCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        c.calculateCardDamage(monster);
        if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
            c.energyOnUse = EnergyPanel.totalCount;
        }

        if (c.cost == -1 && c.isInAutoplay) {
            c.freeToPlayOnce = true;
        }

        c.use(this, monster);
        AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
        if (!c.dontTriggerOnUseCard) {
            this.hand.triggerOnOtherCardPlayed(c);
        }

        this.hand.removeCard(c);
        this.cardInUse = c;
        c.target_x = (float)(Settings.WIDTH / 2);
        c.target_y = (float)(Settings.HEIGHT / 2);
        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (!this.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)) {
            this.energy.use(c.costForTurn);
        }

        if (!this.hand.canUseAnyCard() && !this.endTurnQueued) {
            AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.lastDamageTaken >= 10 && !sigdmg && !isRoaring && isInCombat()) {
            sigdmg = true;
            playAnimation("Dmg_Idle");
            playAudioV(ProAudio.DAMAGED, 1.0f);
        }
    }

    public static String getCurrentSprite() {
        switch (STATE) {
            case "LaidBack":
                return makeCharacterPath("knight/laidback.png");
            case "Point":
                return makeCharacterPath("knight/point.png");
            case "Hand":
                return makeCharacterPath("knight/hand.png");
            case "Roaring":
                return makeCharacterPath("knight/roaring1.png");
            case "Stance":
                return makeCharacterPath("knight/stance.png");
            case "Windup":
                return makeCharacterPath("knight/windup.png");
            case "Execute":
                return makeCharacterPath("knight/execute.png");
            case "Chargeup":
                return makeCharacterPath("knight/chargeup.png");
            case "SS_Windup":
                return makeCharacterPath("knight/ss_windup.png");
            case "SSlash1":
                return makeCharacterPath("knight/sslash1.png");
            case "SSlash2":
                return makeCharacterPath("knight/sslash2.png");
            case "SSlash3":
                return makeCharacterPath("knight/sslash3.png");
            default: //also when STATE = "Idle"
                return makeCharacterPath("knight/main.png");
        }
    }

    public static void startBlackKnifeBGM() {
        if ((isRoaring && customBGM) || (!customRoar && customBGM)) {
            CardCrawlGame.music.silenceTempBgmInstantly();
            CardCrawlGame.music.silenceBGMInstantly();
            AbstractDungeon.scene.fadeOutAmbiance();
            AbstractDungeon.getCurrRoom().playBgmInstantly("Black_Knife_BGM");
            bkbgm = true;
            isRoaring = false;
        }
    }

    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("images/scenes/purpleBg.jpg");
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel(makeCharacterPath("ending/ending1.png")));
        panels.add(new CutscenePanel(makeCharacterPath("ending/ending2.png"), makeID(ProAudio.FOUNTAIN_ENDING.name())));
        panels.add(new CutscenePanel(makeCharacterPath("ending/ending3.png")));
        return panels;
    }
}
