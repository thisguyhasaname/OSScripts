import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import java.util.function.BooleanSupplier;

import static org.osbot.rs07.script.MethodProvider.random;


@ScriptManifest(name = "AgilityTrainer", author = "Iownreality1", info = "Train agility", version = 0.1, logo = "")
public class AgilityTrainer extends  Script{
    Position GnomeStrongholdStart = new Position(2474,3436,0);
    Area GnomeAreaGround = new Area(2467,3413,2492,3441);
    Area GnomeAreaMiddle = new Area(2467,3413,2492,3441);
    Area GnomeAreaTop = new Area(2467,3413,2492,3441);

    Position DraynorVillageStart = new Position(3103,3279,0);
    Position VarrockStart = new Position(3221,3414,0);
    Position CanifisStart = new Position(3507,3488,0);
    Position[] Startlist = {GnomeStrongholdStart,DraynorVillageStart,VarrockStart,CanifisStart};
    boolean PriestinPerilDone = false;
    boolean nearCourse = false;
    int currentCourse;
    @Override
    public final void onStart() throws InterruptedException {
        GnomeAreaGround.setPlane(0);
        GnomeAreaMiddle.setPlane(1);
        GnomeAreaTop.setPlane(2);
        PriestinPerilDone = (configs.get(302) == 61);
        if (!PriestinPerilDone && skills.getDynamic(Skill.AGILITY) >= 40) {
            PriestQuest();
        }
    }
    @Override
    public final int onLoop()  throws InterruptedException
    {
        //TODO: everything after gnome
        //TODO: fix gnome sleep timers
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
                nearCourse = (GnomeAreaGround.contains(myPosition()) || GnomeAreaMiddle.contains(myPosition()) || GnomeAreaTop.contains(myPosition()));
                if (nearCourse) {
                    Gnome();
                }
                else if (!inventory.contains("Ardougne teleport")) {
                    log("not in course");
                    try {
                        npcs.closest("Banker").interact("Bank");
                        log("Moving to Bank");
                        Sleep.sleepUntil(() -> getBank().isOpen(), 30000);
                        sleep(random(1200, 1800));
                        log("Waiting for bank to open");
                        getBank().withdraw("Ardougne teleport", 2);
                        sleep(random(1200, 1800));
                    }
                    catch (Exception e) {
                        log(e.toString());
                    }
                ;
                }
                else {
                    log("not in course can tp");
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
    public void Gnome() throws InterruptedException {
        Area GnomeEnd = new Area(2471,3435,2489,3440);//0
        Area firstObstacle = new Area(2470,3430,2477,3425);//0
        Area secondObstacle = new Area(2471,3422,2476,3424);//1
        secondObstacle.setPlane(1);
        Area thirdObstacle = new Area(2472,3421,2477,3418);//2
        thirdObstacle.setPlane(2);
        Area fourthObstacle = new Area(2483,3421,2488,3418);//2
        fourthObstacle.setPlane(2);
        Area fifthObstacle = new Area(2480,3425,2489,3417);//0
        Area sixthObstacle = new Area(2480,3426,2490,3432);//0

        if (GnomeEnd.contains(myPosition())) {
            log("log balance");
            walking.walk(objects.closest("Log balance"));
            sleep(random(200,400));
            objects.closest("Log balance").interact("Walk-across");
            sleep(random(6000,6600));
        }
        else if (firstObstacle.contains(myPosition())) {
            log("obstacle net");
            walking.walk(objects.closest("Obstacle net"));
            sleep(random(200,400));
            objects.closest("Obstacle net").interact("Climb-over");
            sleep(random(6000,6600));
        }
        else if (secondObstacle.contains(myPosition())) {
            log("tree branch");
            walking.walk(objects.closest("Tree branch"));
            sleep(random(200,400));
            objects.closest("Tree branch").interact("Climb");
            sleep(random(6000,6600));
        }
        else if (thirdObstacle.contains(myPosition())) {
            log("balancing rope");
            walking.walk(objects.closest("Balancing rope"));
            sleep(random(200,400));
            objects.closest("Balancing rope").interact("Walk-on");
            sleep(random(6000,6600));
        }
        else if (fourthObstacle.contains(myPosition()) && (myPosition().getZ() > 0)) {
            log("tree branch down");
            walking.walk(objects.closest("Tree branch"));
            sleep(random(200,400));
            objects.closest("Tree branch").interact("Climb-down");
            sleep(random(6000,6600));
        }
        else if (fifthObstacle.contains(myPosition()) && (myPosition().getZ() == 0)) {
            log("obstacle net 2");
            walking.walk(objects.closest("Obstacle net"));
            sleep(random(200,400));
            objects.closest("Obstacle net").interact("Climb-over");
            sleep(random(6000,6600));
        }
        else if (sixthObstacle.contains(myPosition())) {
            log("last pipe");
            walking.walk(objects.closest("Obstacle pipe"));
            sleep(random(200,400));
            objects.closest("Obstacle pipe").interact("Squeeze-through");
            sleep(random(6000,6600));
        }
        else {
            sleep(random(3000,3600));
            if ((!(GnomeEnd.contains(myPosition()) || firstObstacle.contains(myPosition()) || secondObstacle.contains(myPosition())
                    || thirdObstacle.contains(myPosition()) || fourthObstacle.contains(myPosition()) || fifthObstacle.contains(myPosition())
                    || sixthObstacle.contains(myPosition()))) && myPosition().getZ() == 0) {
                log("fucked up");
                walking.webWalk(GnomeStrongholdStart);
            }
            else if ((!(GnomeEnd.contains(myPosition()) || firstObstacle.contains(myPosition()) || secondObstacle.contains(myPosition())
                    || thirdObstacle.contains(myPosition()) || fourthObstacle.contains(myPosition()) || fifthObstacle.contains(myPosition())
                    || sixthObstacle.contains(myPosition()))) && myPosition().getZ() == 1) {
                log("fucked up upstairs");

            }
            else if ((!(GnomeEnd.contains(myPosition()) || firstObstacle.contains(myPosition()) || secondObstacle.contains(myPosition())
                    || thirdObstacle.contains(myPosition()) || fourthObstacle.contains(myPosition()) || fifthObstacle.contains(myPosition())
                    || sixthObstacle.contains(myPosition()))) && myPosition().getZ() == 1) {
                log("fucked up upstairs x2");

            }
        }


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
