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
            case 11:

            case 12:

            case 13:

            case 14:

            case 15:

            case 16:

            case 17:

            case 18:

            case 19:

            case 20:

            case 21:

            case 22:

            case 23:

            case 24:

            case 25:

            case 26:

            case 27:

            case 28:

            case 29:


        }

        return random(1200, 1800);
    }


}
