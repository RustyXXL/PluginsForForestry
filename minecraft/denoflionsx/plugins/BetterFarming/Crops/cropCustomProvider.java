package denoflionsx.plugins.BetterFarming.Crops;

import denoflionsx.plugins.BetterFarming.Helpers.growHook;
import denoflionsx.plugins.Forestry.Helpers.ForestryBlock;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class cropCustomProvider implements ICropProvider {
    
    protected int treeID;
    protected Block tree;
    protected ItemStack windFallData;
    protected int saplingIDs[];
    protected int treeMeta;
    protected int LogID;
    protected int LogMeta;

    public cropCustomProvider(Block param1, ItemStack param2, int param3[]) {
        
        this.tree = param1;
        this.treeID = param1.blockID;
        this.windFallData = param2;
        this.saplingIDs = param3;
        this.treeMeta = 0;
        this.LogID = Block.wood.blockID;
    }
    
    public cropCustomProvider(int param1, ItemStack param2, int param3[], int param4, int param5, int param6){
        this.treeID = param1;
        this.windFallData = param2;
        this.saplingIDs = param3;
        this.treeMeta = param5;
        this.LogID = param4;
        this.LogMeta = param6;
    }

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int var6 = world.getBlockId(x, y, z);

        if (var6 != 0) {
            int var7 = world.getBlockMetadata(x, y, z);
            if (var7 == 4){
            growHook.growTree(world,x,y,z,this.tree);
            }
            return false;
        } else {
            int var7 = world.getBlockId(x, y - 1, z);
            int var8 = world.getBlockMetadata(x, y - 1, z);

            if (var7 == ForestryBlock.soil.blockID && (var8 & 3) == 0) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.treeID, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropCustomTree(world, x, y, z, this.LogID, this.LogMeta);
    }

    @Override
    public ItemStack[] getWindfall() {
        return new ItemStack[]{this.windFallData};
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        return (id == this.treeID && meta == this.treeMeta);
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        for(int i = 0; i < this.saplingIDs.length; i++){
        if (germling.itemID == this.saplingIDs[i]){
            return true;
        }
    }    
        return false;
    }
}
