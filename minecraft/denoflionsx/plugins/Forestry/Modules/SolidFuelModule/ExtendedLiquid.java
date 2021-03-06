
package denoflionsx.plugins.Forestry.Modules.SolidFuelModule;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.Enums.EnumContainers;
import denoflionsx.items.Fuels.customFuelSolid;

public class ExtendedLiquid {
    
    private int id;
    private customFuelSolid solid;
    private solidfuelModule.fuelRBG color;

    public ExtendedLiquid(int id,String name,ItemStack Liquid, int burntime, int mjt, solidfuelModule.fuelRBG color) {
        this.id = id;
        this.color = color;
        this.create(name,Liquid.getItem(),burntime,mjt);
    }
    
    private void create(String name, Item liquid,int burntime, int mjt){
        solid = new customFuelSolid(id,name,5,10000,EnumContainers.Containers.BAR.getTexture(),false,this.color.getColor());
    }
    
}
