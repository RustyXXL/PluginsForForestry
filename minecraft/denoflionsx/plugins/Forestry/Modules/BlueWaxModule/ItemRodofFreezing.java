package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.API.PfFManagers;
import java.util.List;
import net.minecraft.src.*;
import denoflionsx.core.core;
import denoflionsx.PluginsforForestry;
import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.denLib.denLib;

public class ItemRodofFreezing extends Item{
    
    private String name;

    public ItemRodofFreezing(int par1) {
        super(par1);
        this.setMaxStackSize(1);
        this.name = core.addName("Rod of Freezing");
        PfFManagers.ItemManager.registerItem(denLib.toLowerCaseNoSpaces("Rod of Freezing"), this);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setFull3D();
        this.setContainerItem(this);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition obj = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (obj == null){
            return par1ItemStack;
        }
        int id = par2World.getBlockId(obj.blockX, obj.blockY, obj.blockZ);
        if (id == Block.waterMoving.blockID || id == Block.waterStill.blockID){
            par2World.setBlockAndMetadataWithNotify(obj.blockX,obj.blockY,obj.blockZ, Block.ice.blockID, 0);
        }else if (id == Block.lavaStill.blockID || id == Block.lavaMoving.blockID){
            par2World.setBlockAndMetadataWithNotify(obj.blockX, obj.blockY, obj.blockZ, Block.obsidian.blockID, 0);
        }
        return par1ItemStack;
    }
    
    @Override
    public int getIconFromDamage(int par1) {
        return EnumToolTextures.ToolTextures.RODOFREEZING.getIndex();
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return this.name;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        tooltips(par1ItemStack, par3List);
    }
     
     
     
     private void tooltips(ItemStack item, List list){
         list.add("Right click some water!");
     }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }
}
