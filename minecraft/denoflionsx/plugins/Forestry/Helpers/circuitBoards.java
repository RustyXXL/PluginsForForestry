package denoflionsx.plugins.Forestry.Helpers;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;

public class circuitBoards {
    
    //Same as tubes.
    
    public static final Item baseCircuitBoard = denLib.getItem("forestry.core.config.ForestryItem","circuitboards");
    public static ItemStack smallCircuitBoard = new ItemStack(baseCircuitBoard,1,0);
    public static ItemStack mediumCircuitBoard = new ItemStack(baseCircuitBoard,1,1);
    public static ItemStack largeCircuitBoard = new ItemStack(baseCircuitBoard,1,2);
    
}
