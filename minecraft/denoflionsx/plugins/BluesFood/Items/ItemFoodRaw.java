package denoflionsx.plugins.BluesFood.Items;

import denoflionsx.items.multiItem;

public class ItemFoodRaw extends multiItem{

    public ItemFoodRaw(int par1, String name) {
        super(par1, name);
    }

    @Override
    public String getTextureFile() {
        return ItemFoods.spritesheet;
    }  
}
