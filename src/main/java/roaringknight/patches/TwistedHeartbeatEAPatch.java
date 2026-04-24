package roaringknight.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import roaringknight.powers.TwistedHeartbeatPower;

import java.util.ArrayList;

import static roaringknight.util.Wiz.atb;
import static roaringknight.util.Wiz.p;

public class TwistedHeartbeatEAPatch {
    @SpirePatch(clz = ExhumeAction.class, method = "update")
    public static class TwistedHeartbeatBlock {
        @SpireInsertPatch(locator = Locator.class, localvars = {"c"})
        public static void gainTHBlock(AbstractCard c) {
            if (p().hasPower(TwistedHeartbeatPower.POWER_ID)) {
                atb(new GainBlockAction(p(), p(), p().getPower(TwistedHeartbeatPower.POWER_ID).amount));
            }
        }
        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws CannotCompileException, PatchingException {
                Matcher matcher = new Matcher.MethodCallMatcher(CardGroup.class, "addToHand");
                return LineFinder.findAllInOrder(ctBehavior, new ArrayList<Matcher>(), matcher);
            }
        }
    }
}
