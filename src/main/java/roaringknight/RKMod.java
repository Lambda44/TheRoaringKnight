package roaringknight;

import basemod.*;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.scannotation.AnnotationDB;
import roaringknight.actions.MessageCaller;
import roaringknight.cards.AbstractEasyCard;
import roaringknight.cards.cardvars.AbstractEasyDynamicVariable;
import roaringknight.potions.AbstractEasyPotion;
import roaringknight.relics.AbstractEasyRelic;
import roaringknight.util.ProAudio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.p;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class RKMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        AddAudioSubscriber {

    public static ModInfo info;
    public static String modID;
    static { loadModInfo(); }
    //public static final String modID = "roaringknight";

    //ACTUAL GAMEPLAY TAGS
    @SpireEnum
    public static AbstractCard.CardTags SHADOW;
    @SpireEnum
    public static AbstractCard.CardTags TITAN;
    //ANIMATION TAGS
    @SpireEnum
    public static AbstractCard.CardTags LAIDBACK;
    @SpireEnum
    public static AbstractCard.CardTags POINT;
    @SpireEnum
    public static AbstractCard.CardTags HAND;
    @SpireEnum
    public static AbstractCard.CardTags STANCE;
    @SpireEnum
    public static AbstractCard.CardTags WINDUP;
    @SpireEnum
    public static AbstractCard.CardTags CHARGEUP;
    @SpireEnum
    public static AbstractCard.CardTags SSLASH;

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(0.3f, 0.0f, 0.2f, 1);

    public static final String SHOULDER1 = makeCharacterPath("knight/shoulder.png");
    public static final String SHOULDER2 = makeCharacterPath("knight/shoulder2.png");
    public static final String CORPSE = makeCharacterPath("knight/corpse.png");
    private static final String ATTACK_S_ART = makeImagePath("512/attack.png");
    private static final String SKILL_S_ART = makeImagePath("512/skill.png");
    private static final String POWER_S_ART = makeImagePath("512/power.png");
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String ATTACK_L_ART = makeImagePath("1024/attack.png");
    private static final String SKILL_L_ART = makeImagePath("1024/skill.png");
    private static final String POWER_L_ART = makeImagePath("1024/power.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");
    // config stuff
    public static boolean customBGM;
    public static boolean customRoar;
    public static boolean unseenTutorial;
    private static SpireConfig config;

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public RKMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(RoaringKnight.Enums.KNIGHT_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath) {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static String makeVFXPath(String resourcePath) {
        return modID + "Resources/images/vfx/" + resourcePath;
    }

    public static void initialize() throws IOException {
        //setting the config
        Properties defaults = new Properties();
        defaults.setProperty("customBGM", "true");
        defaults.setProperty("customRoar", "true");
        defaults.setProperty("unseenTutorial", "true");
        config = new SpireConfig(modID, "Config", defaults);
        customBGM = config.getBool("customBGM");
        customRoar = config.getBool("customRoar");
        unseenTutorial = config.getBool("unseenTutorial");

        RKMod thismod = new RKMod();
    }

    public static void saveTutorialSeen() throws IOException {
        config.setBool("unseenTutorial", unseenTutorial);
        config.save();
    }

    @Override
    public void receivePostInitialize() {
        ModPanel settings = new ModPanel();
        settings.addUIElement((IUIElement)new ModLabeledToggleButton("Enable custom BGM", 350.0F, 700.0F, Settings.CREAM_COLOR, FontHelper.charDescFont,
                config.getBool("customBGM"), settings, label -> {}, button -> {
            customBGM = button.enabled;
            config.setBool("customBGM", button.enabled);
            try {
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        settings.addUIElement((IUIElement)new ModLabeledToggleButton("Enable custom Roaring animation", 350.0F, 650.0F, Settings.CREAM_COLOR, FontHelper.charDescFont,
                config.getBool("customRoar"), settings, label -> {}, button -> {
            customRoar = button.enabled;
            config.setBool("customRoar", button.enabled);
            try {
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        settings.addUIElement((IUIElement)new ModLabeledToggleButton("Force Character Tutorial in next battle", 350.0F, 600.0F, Settings.CREAM_COLOR, FontHelper.charDescFont,
                config.getBool("unseenTutorial"), settings, label -> {}, button -> {
            unseenTutorial = button.enabled;
            config.setBool("unseenTutorial", button.enabled);
            try {
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        BaseMod.registerModBadge(new Texture(makeImagePath("ui/badge.png")), info.Name, arrToString(info.Authors), info.Description, settings);
    }

    //This determines the mod's ID based on information stored by ModTheSpire.
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(RKMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new RoaringKnight(RoaringKnight.characterStrings.NAMES[1], RoaringKnight.Enums.THE_ROARING_KNIGHT),
            CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, RoaringKnight.Enums.THE_ROARING_KNIGHT);
        
        new AutoAdd(modID)
            .packageFilter(AbstractEasyPotion.class)
            .any(AbstractEasyPotion.class, (info, potion) -> {
                if (potion.pool == null)
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
                else
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
            });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
            .packageFilter(AbstractEasyDynamicVariable.class)
            .any(DynamicVariable.class, (info, var) -> 
                BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
        BaseMod.loadCustomStringsFile(TutorialStrings.class, modID + "Resources/localization/" + getLangString() + "/Tutorialstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    private static String arrToString(Object[] arr) {
        if (arr == null)
            return null;
        if (arr.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; ++i) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[arr.length - 1]);
        return sb.toString();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (p() instanceof RoaringKnight && RKMod.unseenTutorial) {
            atb(new MessageCaller());
        }
    }
}
