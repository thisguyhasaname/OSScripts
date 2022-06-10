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
    public int onLoop() throws InterruptedException
    {

        int plagueCityProg = configs.get(165);

        switch (plagueCityProg)
        {
            case 0:
                getWalking().webWalk(edmondArea);
                getNpcs().closest("Edmond").interact();
                dialogues.completeDialogue("What's happened to her?", "Yes.");
            case 1:
                getNpcs().closest("Alrena").interact();
                dialogues.completeDialogue();
            case 2:
                getGroundItems().closest(1510).interact();
                getNpcs().closest("Edmond").interact();
                dialogues.completeDialogue();
            case 3:
                inventory.getItem("Bucket of Water").interact();
                getObjects().closest("Mud Patch").interact("Use");
            case 4:
                inventory.getItem("Bucket of Water").interact();
                getObjects().closest("Mud Patch").interact("Use");
            case 5:
                inventory.getItem("Bucket of Water").interact();
                getObjects().closest("Mud Patch").interact("Use");
            case 6:
                inventory.getItem("Bucket of Water").interact();
                getObjects().closest("Mud Patch").interact("Use");
            case 7:
                getWalking().webWalk(dugHole);
                inventory.getItem("Spade").interact();
            case 8:
                walking.webWalk(Grateunder);
                objects.closest("Grill").interact();
                inventory.getItem("Rope").interact();
                objects.closest("Grill").interact();
            case 9:
                walking.webWalk(edmondArea);
                npcs.closest("Edmond").interact();
                dialogues.completeDialogue();
            case 10:
                inventory.getItem("Gas mask").interact();
                objects.closest("Pipe").interact();
                npcs.closest("Jethick").interact();
                dialogues.completeDialogue("Yes, I'll return it for you.");
            case 20:
                walking.webWalk(northHouse);
                objects.closest("Door");
                dialogues.completeDialogue();
            case 21:
                npcs.closest("Martha Rehnison").interact();
                dialogues.completeDialogue();
            case 22:
                objects.closest("Stairs").interact();
                npcs.closest("Milli Rehnison").interact();
                dialogues.completeDialogue();
            case 23:
                objects.closest("Stairs").interact();
                objects.closest("Door").interact();
                walking.webWalk(southHouse);
                objects.closest("Door").interact();
                dialogues.completeDialogue("I fear not a mere plague.");

            case 24:
                walking.webWalk(mayorHouse);
                npcs.closest("Clerk").interact();
                dialogues.completeDialogue("I need permission to enter a plague house.", "This is urgent though! Someone's been kidnapped!");

            case 25:
                walking.webWalk(BravekDoor);
                objects.closest("Door").interact();
                npcs.closest("Bravek").interact();
                dialogues.completeDialogue("This is really important though!", "Do you know what's in the cure?");

            case 26:
                inventory.getItem("Chocolate dust").interact();
                inventory.getItem("Bucket of milk").interact();
                inventory.getItem("Snape grass").interact();
                inventory.getItem("Chocolately milk").interact();
                inventory.getItem("Hangover cure").interact();
                npcs.closest("Bravek").interact();
            case 27:
                dialogues.completeDialogue("They won't listen to me!");
                walking.webWalk(southHouse);
                objects.closest("Door").interact();
                dialogues.completeDialogue();
                objects.closest("Barrel").interact();
                objects.closest("Spooky stairs").interact();
                objects.closest("Door").interact();
                npcs.closest("Elena").interact();
                dialogues.completeDialogue();

            case 28:
                objects.closest("Door").interact();
                objects.closest("Spooky stairs").interact();
                //might require more walks than this big one
                walking.webWalk(edmondArea);
                npcs.closest("Edmond").interact();
                //quest complete


        }

        return random(1200, 1800);
    }


}
