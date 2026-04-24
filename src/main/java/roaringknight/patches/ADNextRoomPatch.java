package roaringknight.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import roaringknight.RoaringKnight;

public class ADNextRoomPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
    public static class adnr {
        @SpirePostfixPatch
        public static void Postfix(AbstractDungeon __instance, SaveFile saveFile) {
            if (AbstractDungeon.player.getCharacterString().NAMES[0].equals("The Roaring Knight")) {
                RoaringKnight.currRoom = AbstractDungeon.getCurrRoom();
                RoaringKnight.setAIRender();
            }
        }
    }
}
