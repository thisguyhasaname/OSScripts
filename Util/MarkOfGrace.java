package Util;
import org.osbot.rs07.api.GroundItems;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.Script;

public class MarkOfGrace extends Script{
    static GroundItem groundItem;
    static Position itemPosition;
    public static final void pickUpMark(Area currentArea)
    {
        groundItem = getGroundItems().closest(e -> e != null && e.getName().contains("Mark of grace"));
        if(groundItem != null)
        {
            itemPosition = groundItem.getPosition();
            if (currentArea.contains(itemPosition))
            {
                groundItem.interact("Take");
            }
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        return 0;
    }
}
