import javafx.geometry.Pos;
import jdk.nashorn.internal.ir.ContinueNode;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import java.sql.*;

public class QuestPlagueCity extends Script
{
    private final Area edmondArea = new Area(2563,3335,2570,3330);
    Position dugHole = new Position (2566, 3332, 0);
    Position Grateunder = new Position(2514,9739,0);
    Position EdmondUnder = new Position(2517,9755,0);
    Position northHouse = new Position(2531, 3328, 0);
    Position southHouse = new Position(2533,3272,0);
    Position mayorHouse = new Position(2526,3311,0);
    Position BravekDoor = new Position(2529,3314,0);
    public int onLoop() throws InterruptedException {
        if ((settings.getRunEnergy() < 20) || (settings.isRunning() == false))
        {
            inventory.getItem("Stamina potion(4)").interact();
        }
        int plagueCityProg = configs.get(165);

        switch (plagueCityProg)
        {
            case 0:
                getWalking().webWalk(edmondArea);
                sleep(random(600,1200));
                getNpcs().closest("Edmond").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue("What's happened to her?", "Yes.");
            case 1:
                getNpcs().closest("Alrena").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 2:
                getGroundItems().closest(1510).interact();
                sleep(random(600,1200));
                getNpcs().closest("Edmond").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 3:
                inventory.getItem("Bucket of Water").interact();
                sleep(random(600,1200));
                getObjects().closest("Mud Patch").interact("Use");
            case 4:
                inventory.getItem("Bucket of Water").interact();
                sleep(random(600,1200));
                getObjects().closest("Mud Patch").interact("Use");
                sleep(random(600,1200));
            case 5:
                inventory.getItem("Bucket of Water").interact();
                sleep(random(600,1200));
                getObjects().closest("Mud Patch").interact("Use");
            case 6:
                inventory.getItem("Bucket of Water").interact();
                sleep(random(600,1200));
                getObjects().closest("Mud Patch").interact("Use");
            case 7:
                getWalking().webWalk(dugHole);
                sleep(random(600,1200));
                inventory.getItem("Spade").interact();
            case 8:
                walking.webWalk(Grateunder);
                sleep(random(600,1200));
                objects.closest("Grill").interact();
                sleep(random(600,1200));
                inventory.getItem("Rope").interact();
                sleep(random(600,1200));
                objects.closest("Grill").interact();
            case 9:
                walking.webWalk(edmondArea);
                sleep(random(600,1200));
                npcs.closest("Edmond").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 10:
                inventory.getItem("Gas mask").interact();
                sleep(random(600,1200));
                objects.closest("Pipe").interact();
                sleep(random(600,1200));
                npcs.closest("Jethick").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue("Yes, I'll return it for you.");
            case 20:
                walking.webWalk(northHouse);
                sleep(random(600,1200));
                objects.closest("Door");
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 21:
                npcs.closest("Martha Rehnison").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 22:
                objects.closest("Stairs").interact();
                sleep(random(600,1200));
                npcs.closest("Milli Rehnison").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
            case 23:
                objects.closest("Stairs").interact();
                sleep(random(600,1200));
                objects.closest("Door").interact();
                sleep(random(600,1200));
                walking.webWalk(southHouse);
                sleep(random(600,1200));
                objects.closest("Door").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue("I fear not a mere plague.");

            case 24:
                walking.webWalk(mayorHouse);
                sleep(random(600,1200));
                npcs.closest("Clerk").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue("I need permission to enter a plague house.", "This is urgent though! Someone's been kidnapped!");

            case 25:
                walking.webWalk(BravekDoor);
                sleep(random(600,1200));
                objects.closest("Door").interact();
                sleep(random(600,1200));
                npcs.closest("Bravek").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue("This is really important though!", "Do you know what's in the cure?");

            case 26:
                inventory.getItem("Chocolate dust").interact();
                sleep(random(600,1200));
                inventory.getItem("Bucket of milk").interact();
                sleep(random(600,1200));
                inventory.getItem("Snape grass").interact();
                sleep(random(600,1200));
                inventory.getItem("Chocolately milk").interact();
                sleep(random(600,1200));
                inventory.getItem("Hangover cure").interact();
                sleep(random(600,1200));
                npcs.closest("Bravek").interact();
            case 27:
                dialogues.completeDialogue("They won't listen to me!");
                sleep(random(600,1200));
                walking.webWalk(southHouse);
                sleep(random(600,1200));
                objects.closest("Door").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();
                sleep(random(600,1200));
                objects.closest("Barrel").interact();
                sleep(random(600,1200));
                objects.closest("Spooky stairs").interact();
                sleep(random(600,1200));
                objects.closest("Door").interact();
                sleep(random(600,1200));
                npcs.closest("Elena").interact();
                sleep(random(600,1200));
                dialogues.completeDialogue();

            case 28:
                objects.closest("Door").interact();
                sleep(random(600,1200));
                objects.closest("Spooky stairs").interact();
                sleep(random(600,1200));
                //might require more walks than this big one
                walking.webWalk(edmondArea);
                sleep(random(600,1200));
                npcs.closest("Edmond").interact();
                sleep(random(600,1200));
                //quest complete


        }

        return random(1200, 1800);
    }


}
