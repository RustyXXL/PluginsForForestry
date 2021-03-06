package denoflionsx.plugins.Redpower;

import denoflionsx.Old.pluginBase;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.EnumForestryLiquids;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.core.core;
import denoflionsx.plugins.Redpower.Crops.cropFlaxProvider;

public class pluginRedPowerWorld extends pluginBase {

    protected String theClass = "RedPowerWorld";

    public pluginRedPowerWorld() {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedPowerWorld";
        if (core.isBukkit){
            theClass = "net.minecraft.server." + theClass;
        }
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            hooked = false;
            core.print(mod + " not found!");
            return hooked;
        }

        try {

            this.addItem(theClass,"itemSeeds","Flax Seeds",0);
            this.addBlock(theClass,"blockPlants","Indigo Flower",0);
            this.addBlock(theClass,"blockCrops","Flax",0);

            hooked = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            hooked = false;

        } finally {
        }

        return hooked;
    }

    @Override
    protected void recipes() {
        FlowerManager.plainFlowers.add(this.blocks.get("Indigo Flower"));
        RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Flax Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, 100));
        CropProviders.cerealCrops.add(new cropFlaxProvider(Item.silk, this.items.get("Flax Seeds").getItem(), this.getBlock("Flax"),4, new int[]{3,3}));
    }

    @Override
    protected void defaults() {
        
    }
    
    
}
