package denoflionsx.Handlers;

import denoflionsx.Interfaces.IWorldLoaded;
import denoflionsx.core.core;
import java.util.ArrayList;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class WorldLoadHandler {

    public boolean hasRan = false;
    public ArrayList<IWorldLoaded> listeners = new ArrayList();

    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event) {
        if (!hasRan) {
            for (IWorldLoaded p : listeners) {
                if (p != null) {
                    try {
                        p.onWorldLoaded();
                    } catch (Exception ex) {
                        core.print("WorldHandler error! " + p.toString());
                    }
                }
            }
            hasRan = true;
        }
    }
}
