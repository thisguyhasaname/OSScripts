import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import java.util.function.BooleanSupplier;

@ScriptManifest(name = "PlagueCity", author = "Iownreality1", info = "Smelts Cannon Balls", version = 0.1, logo = "")
public class QuestPlagueCity extends Script
{
    int[] supplyID = {2126,954,952,1929,1927,1975,231};
    String[] supplyName = {"Dwellberries", "Rope", "Spade", "bucket of water", "Bucket of milk", "Chocolate dust", "Snape grass"};
    int[] supplyQuantity = {1, 1, 1, 1, 4, 1, 1};
    private final Area GeArea = new Area(3159,3482,3168,3492);
    private final Area edmondArea = new Area(2563,3335,2570,3330);
    Area undergroundTunnel = new Area(2509,9765,2519,9738);
    Area Cellar = new Area(2536,9669,2542,9673);
    Area southHouse = new Area(2532,3272,2541,3268);
    Position Outsidealrena = new Position(2570,3333,0);
    Position alrenaDoor = new Position(2573,3333,0);
    Position dugHole = new Position (2566, 3332, 0);
    Position Grateunder = new Position(2514,9739,0);
    Position EdmondUnder = new Position(2517,9755,0);
    Position northHouse = new Position(2531, 3328, 0);
    Position SouthHouseDoor = new Position(2534,3272,0);
    Position mayorHouse = new Position(2526,3311,0);
    Position BravekDoor = new Position(2529,3314,0);
    Position mudPile = new Position(2518,9759,0);
    public int onLoop() throws InterruptedException {
        if ((settings.getRunEnergy() < 20) || (!settings.isRunning()))
        {

            inventory.getItem("Stamina potion(4)").interact();
        }
        int plagueCityProg = configs.get(165);

        switch (plagueCityProg) {
            case 0:
                if ((!inventory.contains("Dwellberries")) || (!inventory.contains("Rope"))
                    || (!inventory.contains("Spade")) || (!inventory.contains("Bucket of milk"))
                    || (!inventory.contains("Chocolate dust")) || (!inventory.contains("Snape grass"))
                    || (!(inventory.getAmount("Bucket of water") < 4)))
                {
                    Supply();
                }
                log("Case 0");
                getWalking().webWalk(edmondArea);
                sleep(random(1800,2400));
                TalkandWait("Edmond");
                sleep(random(1800,2400));
                dialogues.completeDialogue("What's happened to her?", "Yes.");
                break;
            case 1:
                log("Case 1");
                walking.webWalk(Outsidealrena);
                sleep(random(1800,2400));
                if (doorHandler.canReachOrOpen(alrenaDoor))
                {
                    log("cant reach");
                    objects.closest("Door").interact("Open");
                    sleep(random(1800,2400));
                }
                log("walk to inside door");
                sleep(random(1800,2400));
                WalkingEvent myEvent = new WalkingEvent(new Position(alrenaDoor)); //making the event
                myEvent.setMinDistanceThreshold(0);

                execute(myEvent);
                sleep(random(600, 1200));
                log("open inside door");
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                log("talk to alrena");
                TalkandWait("Alrena");
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                break;
            case 2:
                log("Case 2");
                if (!inventory.contains("Picture"))
                {
                    log("no picture");
                    getGroundItems().closest(1510).interact("Take");
                    sleep(random(1800,2400));
                }
                if (!dialogues.isPendingContinuation())
                {
                    TalkandWait("Edmond");
                }
                Sleep3.sleepUntil(() -> dialogues.isPendingContinuation(), 6000);
                dialogues.completeDialogue();
                break;
            case 3:
                log("Case 3");
                walking.webWalk(edmondArea);
                sleep(random(1800,2400));
                inventory.getItem("Bucket of Water").interact();
                sleep(random(1800,2400));
                getObjects().closest("Mud Patch").interact("Use");
                break;
            case 4:
                log("Case 4");
                walking.webWalk(edmondArea);
                sleep(random(1800,2400));
                inventory.getItem("Bucket of Water").interact();
                sleep(random(1800,2400));
                getObjects().closest("Mud Patch").interact("Use");
                sleep(random(1800,2400));
                break;
            case 5:
                log("Case 5");
                walking.webWalk(edmondArea);
                sleep(random(1800,2400));
                inventory.getItem("Bucket of Water").interact();
                sleep(random(1800,2400));
                getObjects().closest("Mud Patch").interact("Use");
                break;
            case 6:
                log("Case 6");
                walking.webWalk(edmondArea);
                sleep(random(1800,2400));
                inventory.getItem("Bucket of Water").interact();
                sleep(random(1800,2400));
                getObjects().closest("Mud Patch").interact("Use");
                break;
            case 7:
                log("Case 7");
                getWalking().webWalk(dugHole);
                sleep(random(1800,2400));
                inventory.getItem("Spade").interact();
                break;
            case 8:
                log("Case 8");
                walking.webWalk(Grateunder);
                sleep(random(1800,2400));
                objects.closest("Grill").interact("Open");
                sleep(random(1800,2400));
                inventory.getItem("Rope").interact();
                sleep(random(1800,2400));
                objects.closest("Grill").interact("Use");
                break;
            case 9:
                log("Case 9");
                walking.webWalk(EdmondUnder);
                sleep(random(1800,2400));
                TalkandWait("Edmond");
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                break;
            case 10:

                log("Case 10");
                if (!equipment.isWearingItem(EquipmentSlot.HAT,"Gas mask"))
                {
                    log("No hat");
                    inventory.getItem("Gas mask").interact();
                    sleep(random(1800,2400));
                }
                if (undergroundTunnel.contains(myPosition()))
                {
                    log("underground");
                    walking.webWalk(Grateunder);
                    sleep(random(1800,2400));
                    objects.closest("Pipe").interact("Climb-up");
                    sleep(random(1800,2400));
                    dialogues.completeDialogue();
                    sleep(random(1800,2400));
                }
                if (npcs.closest("Jethick") != null)
                {
                    if (!dialogues.isPendingContinuation())
                    {
                        TalkandWait("Jethick");
                    }
                    else
                    {
                        dialogues.completeDialogue("Yes, I'll return it for you.");
                    }
                }
                break;
            case 20:
                log("Case 20");
                walking.webWalk(northHouse);
                sleep(random(1800,2400));
                objects.closest("Door").interact();
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                break;
            case 21:
                log("Case 21");
                TalkandWait("Martha Rehnison");
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                break;
            case 22:
                log("Case 22");
                objects.closest("Stairs").interact();
                sleep(random(1800,2400));
                TalkandWait("Milli Rehnison");
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                break;
            case 23:
                log("Case 23");
                objects.closest("Stairs").interact();
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                walking.webWalk(SouthHouseDoor);
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                dialogues.completeDialogue("I fear not a mere plague.");
                break;

            case 24:
                log("Case 24");
                walking.webWalk(mayorHouse);
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                npcs.closest(4255).interact("Talk-to");
                sleep(random(1800,2400));
                dialogues.completeDialogue("I need permission to enter a plague house.", "This is urgent though! Someone's been kidnapped!");
                break;

            case 25:
                log("Case 25");
                walking.webWalk(BravekDoor);
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                TalkandWait("Bravek");
                Sleep3.sleepUntil(() -> dialogues.isPendingContinuation(),6000);
                sleep(random(1800,2400));
                dialogues.completeDialogue("This is really important though!", "Do you know what's in the cure?");
                break;

            case 26:
                log("Case 26");
                if (inventory.contains("Hangover cure"))
                {
                    inventory.getItem("Hangover cure").interact();
                    sleep(random(1800,2400));
                    npcs.closest("Bravek").interact();
                    sleep(random(1800,2400));
                    dialogues.completeDialogue("They won't listen to me!");
                }
                else if (inventory.contains("Chocolatey milk"))
                {
                    inventory.getItem("Snape grass").interact();
                    sleep(random(1800,2400));
                    inventory.getItem("Chocolatey milk").interact();
                }
                else
                {
                    inventory.getItem("Chocolate dust").interact();
                    sleep(random(1800,2400));
                    inventory.getItem("Bucket of milk").interact();
                }
                break;
            case 27:
                log("Case 27");
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                walking.webWalk(SouthHouseDoor);
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                sleep(random(1800,2400));
                objects.closest("Barrel").interact();
                sleep(random(1800,2400));
                objects.closest("Spooky stairs").interact();
                sleep(random(1800,2400));
                objects.closest("Door").interact("Open");
                sleep(random(1800,2400));
                npcs.closest("Elena").interact();
                sleep(random(1800,2400));
                dialogues.completeDialogue();
                sleep(random(1800,2400));
                break;

            case 28:
                log("Case 28");
                if (Cellar.contains(myPosition()))
                {
                    objects.closest("Door").interact("Open");
                    sleep(random(1800,2400));
                    objects.closest("Spooky stairs").interact();
                    sleep(random(1800,2400));
                }
                else if (southHouse.contains(myPosition()))
                {
                    objects.closest("Door").interact("Open");
                    sleep(random(1800,2400));
                    walking.webWalk(mayorHouse);
                    sleep(random(1800,2400));
                    objects.closest("Manhole").interact("Open");
                    sleep(random(1800,2400));
                    objects.closest("Manhole").interact("Climb-down");
                    sleep(random(1800,2400));
                }
                else if (undergroundTunnel.contains(myPosition()))
                {
                    walking.webWalk(mudPile);
                    sleep(random(1800,2400));
                    objects.closest("Mud pile").interact("Climb");
                    sleep(random(1800,2400));

                }
                else
                {
                    npcs.closest("Edmond").interact();
                    sleep(random(1800,2400));
                    dialogues.completeDialogue();
                    sleep(random(1800,2400));
                }

                //quest complete
                break;


        }

        return random(1200, 1800);

    }
    public void Supply() throws InterruptedException {
        walking.webWalk(GeArea);
        sleep(random(1800,2400));
        npcs.closest("Banker").interact("Bank");
        sleep(random(1800,2400));
        bank.withdrawAll("Coins");
        sleep(random(1800,2400));
        bank.close();
        sleep(random(1800,2400));
        getObjects().closest("Grand Exchange Booth").interact("Exchange");
        sleep(random(1800,2400));
        for (int i = 0; i < supplyID.length; i++) {
            grandExchange.buyItem(supplyID[i],supplyName[i],1000,supplyQuantity[i]);
            sleep(random(1800,2400));
        }
        grandExchange.collect();
        sleep(random(1800,2400));


    }
    public boolean TalkandWait(String npc) throws InterruptedException
    {
        npcs.closest(npc).interact("Talk-to");
        Sleep3.sleepUntil(() -> dialogues.isPendingContinuation(), 6000);

        return dialogues.isPendingContinuation();
    }

}
class Sleep3 extends ConditionalSleep
{

    private final BooleanSupplier condition;

    public Sleep3(final BooleanSupplier condition, final int timeout) {
        super(timeout);
        this.condition = condition;
    }

    @Override
    public final boolean condition() throws InterruptedException {
        return condition.getAsBoolean();
    }

    public static boolean sleepUntil(final BooleanSupplier condition, final int timeout) {
        return new Sleep3(condition, timeout).sleep();
    }
}
