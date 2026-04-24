package roaringknight.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class EnumPatch {
    @SpireEnum
    public static AbstractGameAction.AttackEffect RK_STAR;
    @SpireEnum
    public static AbstractGameAction.AttackEffect RK_STAR_EXPLODE;
    @SpireEnum
    public static AbstractGameAction.AttackEffect RK_EXPLODE;
    @SpireEnum
    public static AbstractGameAction.AttackEffect RK_GLOWING_HANDS;
}
