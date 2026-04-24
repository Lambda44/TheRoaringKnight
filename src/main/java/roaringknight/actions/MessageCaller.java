package roaringknight.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import roaringknight.RKMod;
import roaringknight.tutorials.RoaringKnightTutorials;

import java.io.IOException;

public class MessageCaller extends AbstractGameAction {
    private float startingDuration;

    public MessageCaller() {
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (RKMod.unseenTutorial) {
            AbstractDungeon.ftue = new RoaringKnightTutorials();
            RKMod.unseenTutorial = false;
        }

        try {
            RKMod.saveTutorialSeen();
            this.isDone = true;
            ;
        } catch (IOException e) {
            e.printStackTrace();
            this.isDone = true;
        }
    }
}
