import javafx.geometry.Pos;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.input.mouse.MainScreenTileDestination;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;
import java.util.function.BooleanSupplier;

@ScriptManifest(name = "Giants Foundry New", author = "Iownreality1", info = "Does Giants Foundry", version = 1.0, logo = "")
public final class GiantsFoundryNew extends Script
{
    RS2Widget heatArrow = null;
    RS2Widget heatProgressCold = null;
    RS2Widget heatProgressWarm = null;
    RS2Widget heatProgressHot = null;
    RS2Widget progressArrow = null;
    RS2Widget progressOne = null;
    RS2Widget progressTwo = null;
    RS2Widget progressThree = null;
    RS2Widget progressFour = null;
    boolean currentlMakingSword = false;
    int currentHeat;//1 = too cold, 2 = cold, 3 = between cold and warm, 4 = warm, 5 = between warm and hot, 6 = hot 7 = too hot
    int progress; //1 = first prog bar 2 = second prog bar 3 = third prog bar 4 = fourth prog bar
    Color progOneColor;
    Color progTwoColor;
    Color progThreeColor;
    Color progFourColor;
    Color red = new Color(184,35,30);
    Color yellow = new Color(238,165,11);
    Color green = new Color(64,133,70);
    boolean interactingFirst = false;
    boolean interactingSecond = false;
    Area lavaPool = new Area(3370,11495,3372, 11498);
    Position tripHammer = new Position(3367,11497,0);
    Position grindStone = new Position(3364,11492,0);
    Position waterFall = new Position(3360,11489,0);
    Area waterFall2 = new Area(3360,11489,3361, 11489);
    Position polishingWheel = new Position(3365, 11485, 0);
    int greenDescending = 200;
    int greenAscending = 158;
    int yellowDescending = 250;
    int yellowAscending = 185;
    int redDescending = 486;
    int redAscending = 450;
    int arrowPosition;



    @Override
    public final int onLoop() throws InterruptedException
    {
        setWidgets();
        if(currentlMakingSword)
        {
            log("WE ARE MAKING A SWORD");
            log("CURRENT PROGRESS IS: " + progress);
            log("CURRENT HEAT IS: " + currentHeat);
            log("CURRENT COLOR IS: " + progFourColor);
            findProgress();
            findHeat();
            if (progress == 1)
            {
                if (progOneColor.equals(red)) //seems to be working
                {
                    while (redAscending > arrowPosition) // if heat is low heat it up
                    {
                        findHeat();
                        while (!lavaPool.contains(myPosition()))
                        {
                            log("Walking to lavapool");
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with lava pool");
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,250));
                        log("End of redAscending > arrowPosition loop");
                    }
                    interactingFirst = false;
                    if (!tripHammer.equals(myPosition()))
                    {
                        log("Walking to Trip hammer");
                        walking.walk(objects.closest("Trip hammer"));
                        findHeat();
                    }
                    while(currentHeat == 7)
                    {
                        findHeat();
                        log("Waiting for heat to lower");
                        log("currentHeat: " + currentHeat);
                        sleep(random(100,250));
                    }
                    while (currentHeat == 6 && progress == 1)
                    {
                        log("No longer waiting");
                        findProgress();
                        findHeat();
                        if (interactingSecond == false)
                        {
                            if (!interactingSecond)
                            {
                                log("interact with hammer");
                                objects.closest("Trip hammer").interact("Use");
                                interactingSecond = true;
                            }
                        }
                        log("Waiting for heat to be outside of red");
                        sleep(random(100,250));
                    }
                    interactingSecond = false;

                }
                else if (progOneColor.equals(yellow))
                {

                }
                else if (progOneColor.equals(green))
                {

                }
            }
            else if (progress == 2)
            {
                if (progTwoColor.equals(red))
                {

                }
                else if (progTwoColor.equals(yellow))//should be working
                {
                    while (yellowDescending < arrowPosition)
                    {
                        findHeat();
                        while(!waterFall2.contains(myPosition()))
                        {
                            log("Starting walk to waterfall");
                            walking.walk(objects.closest("Waterfall"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with waterfall");
                            objects.closest("Waterfall").interact("Cool-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,250));
                        log("End of yellow Descending < arrowposition loop");
                    }
                    interactingFirst = false;
                    if(!grindStone.equals(myPosition()))
                    {
                        log("Walking to grindstone");
                        walking.walk(objects.closest("Grindstone"));
                        findHeat();
                    }
                    while(currentHeat < 4)
                    {
                        findHeat();
                        if(!interactingFirst)
                        {
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(250, 400));
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                    }
                    interactingFirst = false;
                    while (currentHeat == 4 && progress == 2)
                    {
                        findProgress();
                        findHeat();
                        while(!myPosition().equals(grindStone))
                        {
                            log("Walking to grindStone");
                            walking.walk(objects.closest("Grindstone"));
                        }
                        if (!interactingSecond)
                        {
                            log("interact with grindstone");
                            objects.closest("Grindstone").interact("Use");
                            interactingSecond = true;
                        }
                        log("Waiting for heat to be outside of yellow");
                        sleep(random(100,250));
                    }
                }
                else if (progTwoColor.equals(green))
                {
                    smithGreen(progress);
                }
            }
            else if (progress == 3)
            {
                if (progThreeColor.equals(red)) //should be good
                {
                    while (redAscending > arrowPosition) // if heat is low heat it up
                    {
                        findHeat();
                        while (!lavaPool.contains(myPosition()))
                        {
                            log("Walking to lavapool");
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with lava pool");
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,200));
                        log("End of redAscending > arrowPosition loop");
                    }
                    interactingFirst = false;
                    if (!tripHammer.equals(myPosition()))
                    {
                        log("Walking to Trip hammer");
                        walking.walk(objects.closest("Trip hammer"));
                        findHeat();
                    }
                    while(currentHeat == 7)
                    {
                        findHeat();
                        log("Waiting for heat to lower");
                        log("currentHeat: " + currentHeat);
                        sleep(random(100,250));
                    }
                    while (currentHeat == 6 && progress == 3)
                    {
                        log("No longer waiting");
                        findProgress();
                        findHeat();
                        if (interactingSecond == false)
                        {
                            if (!interactingSecond)
                            {
                                log("interact with hammer");
                                objects.closest("Trip hammer").interact("Use");
                                interactingSecond = true;
                            }
                        }
                        log("Waiting for heat to be outside of red");
                        sleep(random(100,250));
                    }
                    interactingSecond = false;
                }
                else if (progThreeColor.equals(yellow))
                {

                }
                else if (progThreeColor.equals(green))
                {
                    while (greenDescending < arrowPosition) //
                    {
                        findHeat();
                        while (!waterFall2.contains(myPosition()))
                        {
                            log("Walking to waterfall");
                            walking.walk(objects.closest("Waterfall"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with Waterfall");
                            objects.closest("Waterfall").interact("Cool-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,200));
                        log("End of green descending < arrowPosition loop");
                    }
                    interactingFirst = false;
                    if (!polishingWheel.equals(myPosition()))
                    {
                        log("Walking to Polishing wheel");
                        walking.walk(objects.closest("Polishing wheel"));
                        findHeat();
                    }
                    while(arrowPosition < greenAscending)
                    {
                        findHeat();
                        if(!interactingFirst)
                        {
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(250, 400));
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                    }
                    interactingFirst = false;
                    while (currentHeat == 2 && progress == 3)
                    {
                        log("No longer waiting");
                        findProgress();
                        findHeat();
                        if (!polishingWheel.equals(myPosition()))
                        {
                            log("Walking to Polishing wheel");
                            walking.walk(objects.closest("Polishing wheel"));
                            findHeat();
                        }
                        if (interactingSecond == false)
                        {
                            if (!interactingSecond)
                            {
                                log("interact with Polishing wheel");
                                objects.closest("Polishing wheel").interact("Use");
                                interactingSecond = true;
                            }
                        }
                        log("Waiting for heat to be outside of green");
                        sleep(random(100,250));
                    }
                    interactingSecond = false;
                }
            }
            else if (progress == 4)
            {

                if (progFourColor.equals(red))
                {

                }
                else if (progFourColor.equals(yellow))
                {
                    while (yellowDescending < arrowPosition)
                    {
                        findHeat();
                        while(!waterFall2.contains(myPosition()))
                        {
                            log("Starting walk to waterfall");
                            walking.walk(objects.closest("Waterfall"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with waterfall");
                            objects.closest("Waterfall").interact("Cool-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,250));
                        log("End of yellow Descending < arrowposition loop");
                    }
                    interactingFirst = false;
                    if(!grindStone.equals(myPosition()))
                    {
                        log("Walking to grindstone");
                        walking.walk(objects.closest("Grindstone"));
                        findHeat();
                    }
                    while(currentHeat < 4)
                    {
                        findHeat();
                        if(!interactingFirst)
                        {
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(250, 400));
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                    }
                    interactingFirst = false;
                    while (currentHeat == 4 && progress == 4)
                    {
                        findProgress();
                        findHeat();
                        while(!myPosition().equals(grindStone))
                        {
                            log("Walking to grindStone");
                            walking.walk(objects.closest("Grindstone"));
                        }
                        if (!interactingSecond)
                        {
                            log("interact with grindstone");
                            objects.closest("Grindstone").interact("Use");
                            interactingSecond = true;
                        }
                        log("Waiting for heat to be outside of yellow");
                        sleep(random(100,250));
                    }
                    interactingSecond = false;

                }
                else if (progFourColor.equals(green)) //should be working
                {
                    while (greenDescending < arrowPosition) // if heat is low heat it up
                    {
                        findHeat();
                        while (!waterFall2.contains(myPosition()))
                        {
                            log("Walking to waterfall");
                            walking.walk(objects.closest("Waterfall"));
                            sleep(random(400,600));
                        }
                        if (interactingFirst == false)
                        {
                            log("Interacting with Waterfall");
                            objects.closest("Waterfall").interact("Cool-preform");
                            interactingFirst = true;
                        }
                        sleep(random(100,200));
                        log("End of green descending < arrowPosition loop");
                    }
                    interactingFirst = false;
                    if (!polishingWheel.equals(myPosition()))
                    {
                        log("Walking to Polishing wheel");
                        walking.walk(objects.closest("Polishing wheel"));
                        findHeat();
                    }
                    while(arrowPosition < greenAscending)
                    {
                        findHeat();
                        if(!interactingFirst)
                        {
                            walking.walk(objects.closest("Lava pool"));
                            sleep(random(250, 400));
                            objects.closest("Lava pool").interact("Heat-preform");
                            interactingFirst = true;
                        }
                    }
                    interactingFirst = false;
                    while (currentHeat == 2 && progress == 4)
                    {
                        log("No longer waiting");
                        findProgress();
                        findHeat();
                        if (!polishingWheel.equals(myPosition()))
                        {
                            log("Walking to Polishing wheel");
                            walking.walk(objects.closest("Polishing wheel"));
                            findHeat();
                        }
                        if (interactingSecond == false)
                        {
                            if (!interactingSecond)
                            {
                                log("interact with Polishing wheel");
                                objects.closest("Polishing wheel").interact("Use");
                                interactingSecond = true;
                            }
                        }
                        log("Waiting for heat to be outside of green");
                        sleep(random(100,250));
                    }
                    interactingSecond = false;
                }
            }

            else if (progress == 5)
            {
                log("Turn in sword");
                sleep(50000);
            }
        }

       return random(600,1200);
    }

    public void setWidgets()
    {
        try {
            //log("Creating Widgets.");
            heatProgressCold = getWidgets().get(754, 19);
            heatProgressWarm = getWidgets().get(754, 20);
            heatProgressHot = getWidgets().get(754, 21);
            heatArrow = getWidgets().get(754, 74);
            progressArrow = getWidgets().get(754, 78);
            progressOne = getWidgets().get(754, 75, 0);
            progressTwo = getWidgets().get(754, 75, 11);
            progressThree = getWidgets().get(754, 75, 22);
            progressFour = getWidgets().get(754, 75, 33);

            //log("Finished Widgets");

            if (heatProgressWarm.isVisible())
            {
                log("making it true");
                currentlMakingSword = true;
            }
            else
            {
                log("making it false");
                currentlMakingSword = false;
            }

        } catch (Exception e)
        {
            log("hit exception");
            currentlMakingSword = false;
        }
    }

    public void findProgress() throws InterruptedException {
        //log("find progress");
        Rectangle rectProgress = new Rectangle();
        Rectangle rectOne = new Rectangle();
        Rectangle rectTwo = new Rectangle();
        Rectangle rectThree = new Rectangle();
        Rectangle rectFour = new Rectangle();
        rectProgress = progressArrow.getRectangleIgnoreIsHidden(false);
        rectOne = progressOne.getRectangleIgnoreIsHidden(false);
        rectTwo = progressTwo.getRectangleIgnoreIsHidden(false);
        rectThree = progressThree.getRectangleIgnoreIsHidden(false);
        rectFour = progressFour.getRectangleIgnoreIsHidden(false);
        progOneColor = colorPicker.colorAt((rectOne.x+2),(rectOne.y+2));
        progTwoColor = colorPicker.colorAt((rectTwo.x+2),(rectTwo.y+2));
        progThreeColor = colorPicker.colorAt((rectThree.x+2),(rectThree.y+2));
        progFourColor = colorPicker.colorAt((rectFour.x+2),(rectFour.y+2));

        if (rectProgress.x+(rectProgress.width/2) >= rectOne.x && rectProgress.x+(rectProgress.width/2) < rectOne.x+rectOne.width)
        {
            log("Progress is within first box");
            progress = 1;
        }
        else if (rectProgress.x+(rectProgress.width/2) >= rectTwo.x && rectProgress.x+(rectProgress.width/2) < rectTwo.x+rectTwo.width)
        {
            log("Progress is within second box");
            progress = 2;
        }
        else if (rectProgress.x+(rectProgress.width/2) >= rectThree.x && rectProgress.x+(rectProgress.width/2) < rectThree.x+rectThree.width)
        {
            log("Progress is within third box");
            progress = 3;
        }
        else if (rectProgress.x+(rectProgress.width/2) >= rectFour.x && rectProgress.x+(rectProgress.width/2) < rectFour.x+rectFour.width)
        {
            log("Progress is within fourth box");
            progress = 4;
        }
        else if (rectProgress.x+(rectProgress.width/2) >= rectFour.x+rectFour.width)
        {
            log("Done with Weapon");
            progress = 5;
        }
        else
        {
            log("No Sword progress need to make sword");
            progress = 0;
        }
    } //do not touch this code this is good

    public void findHeat() throws InterruptedException
    {
        //log("find progress: " + progress);
        Rectangle rectHeat = new Rectangle();
        Rectangle rectOne = new Rectangle();
        Rectangle rectTwo = new Rectangle();
        Rectangle rectThree = new Rectangle();
        rectHeat = heatArrow.getRectangleIgnoreIsHidden(false);
        rectOne = heatProgressCold.getRectangleIgnoreIsHidden(false);
        rectTwo = heatProgressWarm.getRectangleIgnoreIsHidden(false);
        rectThree = heatProgressHot.getRectangleIgnoreIsHidden(false);

        arrowPosition = rectHeat.x+(rectHeat.width/2);

        log("arrow is here x: " + (rectHeat.x+rectHeat.width/2));
        //log("LAST VALUE" + (rectThree.x+rectThree.width));

        if (rectHeat.x+(rectHeat.width/2) > 68 && rectHeat.x+(rectHeat.width/2) <= rectOne.x+rectOne.width)
        {
            log("Progress is within COLD box");
            currentHeat = 2;
        }
        else if (rectHeat.x+(rectHeat.width/2) > rectTwo.x && rectHeat.x+(rectHeat.width/2) < (rectTwo.x+rectTwo.width-5))
        {
            log("Progress is within WARM box");
            currentHeat = 4;
        }
        else if (rectHeat.x+(rectHeat.width/2) > rectThree.x && rectHeat.x+(rectHeat.width/2) <= rectThree.x+rectThree.width)
        {
            log("Progress is within HOT box");
            currentHeat = 6;
        }
        else if (rectHeat.x+(rectHeat.width/2) > rectOne.x+rectOne.width && rectHeat.x+(rectHeat.width/2) <= rectTwo.x+1)
        {
            log("inbetween COLD and WARM");
            currentHeat = 3;
        }
        else if (rectHeat.x+(rectHeat.width/2) > rectTwo.x+rectTwo.width && rectHeat.x+(rectHeat.width/2) <= rectThree.x)
        {
            log("inbetween WARM and HOT");
            currentHeat = 5;
        }
        else if (rectHeat.x+(rectHeat.width/2) <= rectOne.x+1)
        {
            log("BELOW COLD");
            currentHeat = 1;
        }
        else if (rectHeat.x+(rectHeat.width/2) >= rectThree.x)
        {
            log("TOO HOT");
            currentHeat = 7;
        }
        else
        {
            log("SOMEHOW IS NO WHERE ON HEAT METER");
        }
    }

    public void smithGreen(int currentProgress) throws InterruptedException {
        while (greenDescending < arrowPosition) // if heat is low heat it up
        {
            findHeat();
            while (!waterFall2.contains(myPosition()))
            {
                log("Walking to waterfall");
                walking.walk(objects.closest("Waterfall"));
                sleep(random(400,600));
            }
            if (interactingFirst == false)
            {
                log("Interacting with Waterfall");
                objects.closest("Waterfall").interact("Cool-preform");
                interactingFirst = true;
            }
            sleep(random(100,200));
            log("End of green descending < arrowPosition loop");
        }
        interactingFirst = false;
        if (!polishingWheel.equals(myPosition()))
        {
            log("Walking to Polishing wheel");
            walking.walk(objects.closest("Polishing wheel"));
            findHeat();
        }
        while(arrowPosition < greenAscending)
        {
            findHeat();
            if(!interactingFirst)
            {
                walking.walk(objects.closest("Lava pool"));
                sleep(random(250, 400));
                objects.closest("Lava pool").interact("Heat-preform");
                interactingFirst = true;
            }
        }
        interactingFirst = false;
        while (currentHeat == 2 && progress == currentProgress)
        {
            log("No longer waiting");
            findProgress();
            findHeat();
            if (!polishingWheel.equals(myPosition()))
            {
                log("Walking to Polishing wheel");
                walking.walk(objects.closest("Polishing wheel"));
                findHeat();
            }
            if (interactingSecond == false)
            {
                if (!interactingSecond)
                {
                    log("interact with Polishing wheel");
                    objects.closest("Polishing wheel").interact("Use");
                    interactingSecond = true;
                }
            }
            log("Waiting for heat to be outside of green");
            sleep(random(100,250));
        }
        interactingSecond = false;
    }

}
class Sleep5 extends ConditionalSleep
{

    private final BooleanSupplier condition;

    public Sleep5(final BooleanSupplier condition, final int timeout) {
        super(timeout);
        this.condition = condition;
    }

    @Override
    public final boolean condition() throws InterruptedException {
        return condition.getAsBoolean();
    }

    public static boolean sleepUntil(final BooleanSupplier condition, final int timeout) {
        return new Sleep4(condition, timeout).sleep();
    }
}

