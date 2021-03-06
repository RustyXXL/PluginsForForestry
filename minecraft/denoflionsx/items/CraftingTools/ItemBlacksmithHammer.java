package denoflionsx.items.CraftingTools;

import net.minecraft.src.*;
import denoflionsx.API.PfFManagers;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.PluginsforForestry;
import denoflionsx.Enums.EnumToolTextures;

public class ItemBlacksmithHammer extends Item {

    public ItemBlacksmithHammer(int par1) {
        super(par1);
        this.setTextureFile(PluginsforForestry.texture);
        this.setMaxDamage(10);
        this.setMaxStackSize(1);
        core.addName("Blacksmith Hammer");
        PfFManagers.ItemManager.registerItem("blacksmithhammer", this);
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return "item.blacksmithhammer";
    }

    @Override
    public int getIconFromDamage(int par1) {
        return EnumToolTextures.ToolTextures.BLACKSMITHHAMMER.getIndex();
    }
    
    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public Item getContainerItem() {
        return this;
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return true;
    }

    public static enum BlacksmithHammer {

        HAMMER();
        
        private ItemIDManager ID;
        private ItemBlacksmithHammer hammer;
        private int id;

        private BlacksmithHammer() {
            ID = new ItemIDManager(1, "blacksmithhammer");
            defaults();
            hammer = new ItemBlacksmithHammer(this.id);
            recipes();
        }

        private void defaults() {
            core.PfFCore.config.addDefault("BlacksmithHammer_ItemID=" + this.ID.getItemIDs().get(0));
            this.id = core.PfFCore.config.getOptionInt("BlacksmithHammer_ItemID");
        }
        
        private void recipes(){
            FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("blacksmithhammer"),new Object[]{
            "XBX",
            "III",
            "XSX",
            Character.valueOf('S'),new ItemStack(Item.stick),
            Character.valueOf('I'),new ItemStack(Item.ingotIron),
            Character.valueOf('B'),new ItemStack(Item.dyePowder)});
        }
    }

    public static void BlacksmithHammer() {
        ItemBlacksmithHammer.BlacksmithHammer.values();
    }
}
