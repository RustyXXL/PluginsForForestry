package denoflionsx.plugins.BluesFood;

import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;

public class ItemFoods extends ItemFood{
    
    public static String spritesheet = "/denoflionsx/food_sheet.png";
    
    private int texture;
    private String name;

    public ItemFoods(int par1, int texture, int par2, float par3, String name) {
        super(par1, par2, par3, false);
        if (core.isClient()){
            ModLoader.addLocalization("item." + denLib.toLowerCaseNoSpaces(name) + ".name", name);
        }
        this.name = name;
        this.texture = texture;
    }

    @Override
    public int getIconFromDamage(int par1) {
        return this.texture;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return "item." + denLib.toLowerCaseNoSpaces(name);
    }
    
    @Override
    public String getTextureFile() {
        return spritesheet;
    }
}