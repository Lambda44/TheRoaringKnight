package roaringknight.patches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import roaringknight.RKMod;

import java.util.Objects;

@SpirePatch(clz = TempMusic.class, method = "getSong")
public class TempMusicPatch {
    @SpirePrefixPatch
    public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
        if (Objects.equals(key, "Black_Knife_BGM") && RKMod.customBGM) {
            return SpireReturn.Return(MainMusic.newMusic("roaringknightResources/audio/BlackKnifeBGM.ogg"));
        }

        return SpireReturn.Continue();
    }
}
