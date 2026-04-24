package roaringknight.patches;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import roaringknight.util.ProAudio;
import roaringknight.util.TexLoader;

import static roaringknight.RKMod.makeImagePath;
import static roaringknight.RKMod.makeVFXPath;
import static roaringknight.util.Wiz.playAudioV;

public class AttackEffectPatch {
    @SpirePatch(clz = FlashAtkImgEffect.class, method = "loadImage")
    public static class vfx
    {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect ___effect)
        {
            if (___effect == EnumPatch.RK_STAR) {
                return SpireReturn.Return(TexLoader.getTextureAsAtlasRegion(makeVFXPath("star.png")));
            }
            if (___effect == EnumPatch.RK_STAR_EXPLODE) {
                return SpireReturn.Return(TexLoader.getTextureAsAtlasRegion(makeVFXPath("star_explode.png")));
            }
            if (___effect == EnumPatch.RK_EXPLODE) {
                return SpireReturn.Return(TexLoader.getTextureAsAtlasRegion(makeVFXPath("explode.png")));
            }
            if (___effect == EnumPatch.RK_GLOWING_HANDS) {
                return SpireReturn.Return(TexLoader.getTextureAsAtlasRegion(makeVFXPath("glowing_hands.png")));
            }
            return SpireReturn.Continue();
        }

    }
    @SpirePatch(clz = FlashAtkImgEffect.class, method = "playSound")
    public static class sfx
    {
        @SpirePrefixPatch
        public static SpireReturn Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect effect)
        {
            if (effect == EnumPatch.RK_STAR || effect == EnumPatch.RK_STAR_EXPLODE || effect == EnumPatch.RK_EXPLODE || effect == EnumPatch.RK_GLOWING_HANDS) {
                playAudioV(ProAudio.SOUL_HURT, 1.5f);
            }
            else {
                return SpireReturn.Continue();
            }

            return SpireReturn.Return(null);
        }

    }
}
