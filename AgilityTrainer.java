import javafx.geometry.Pos;
import org.osbot.rs07.api.Configs;
import org.osbot.rs07.api.Walking;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import java.util.function.BooleanSupplier;

import static org.osbot.rs07.script.MethodProvider.random;


@ScriptManifest(name = "AgilityTrainer", author = "Iownreality1", info = "Train agility", version = 0.1, logo = "")
public class AgilityTrainer extends  Script{
    Position GnomeStrongholdStart = new Position(2474,3436,0);
    Area GnomeArea = new Area(2467,3441,2492,3413);

    Position DraynorVillageStart = new Position(3103,3279,0);
    Position VarrockStart = new Position(3221,3414,0);
    Position CanifisStart = new Position(3507,3488,0);
    Position[] Startlist = {GnomeStrongholdStart,DraynorVillageStart,VarrockStart,CanifisStart};
    boolean PriestinPerilDone = false;
    boolean nearCourse = false;
    int currentCourse;
    @Override
    public final void onStart() throws InterruptedException {
        PriestinPerilDone = (configs.get(302) == 61);
        if (!PriestinPerilDone && skills.getDynamic(Skill.AGILITY) >= 40) {
            PriestQuest();
        }
    }
    @Override
    public final int onLoop()  throws InterruptedException
    {
        if ((settings.getRunEnergy() < 20) || (!settings.isRunning()))
        {

            inventory.getItem("Stamina potion(4)").interact();
        }
        int level = skills.getDynamic(Skill.AGILITY);
        String[] courses =  {"Gnome Stronghold", "Draynor Village", "Varrock","Canifis"};
        int[] courseReqs = {1,10,30,40};
        for (int i= 0; i < courseReqs.length; i++) {
            if (courseReqs[i] <= level) {
                currentCourse = i;
            }
        }
        switch (currentCourse) {
            case 0:
                nearCourse = (GnomeArea.contains(myPosition()));
                if (nearCourse) {
                    Gnome();
                }
                else if (!inventory.contains("Ardougne teleport")) {
                    npcs.closest("Banker").interact("Bank");
                    log("Moving to Bank");
                    Sleep.sleepUntil(() -> getBank().isOpen(), 30000);
                    sleep(random(1200,1800));
                    log("Waiting for bank to open");
                    getBank().withdraw("Ardougne teleport",2);
                    sleep(random(1200,1800));
                ;
                }
                else {
                    inventory.getItem("Ardougne teleport").interact();
                    sleep(random(1200,1800));
                    walking.webWalk(Startlist[currentCourse]);
                    nearCourse = true;
                }
                break;
            case 1:

    };




        return random(1200, 1800);
    }
    public void Gnome() {
        //find what obstacle im near
        //interact with obstacle, wait till done with obstacle, repeat
    }
    public void PriestQuest() {

    }
}
class Sleep1 extends ConditionalSleep
{

    private final BooleanSupplier condition;

    public Sleep1(final BooleanSupplier condition, final int timeout) {
        super(timeout);
        this.condition = condition;
    }

    @Override
    public final boolean condition() throws InterruptedException {
        return condition.getAsBoolean();
    }

    public static boolean sleepUntil(final BooleanSupplier condition, final int timeout) {
        return new Sleep(condition, timeout).sleep();
    }
}
