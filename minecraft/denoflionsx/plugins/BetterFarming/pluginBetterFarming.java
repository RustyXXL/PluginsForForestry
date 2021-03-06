package denoflionsx.plugins.BetterFarming;

import denoflionsx.plugins.BetterFarming.Helpers.growHook;
import denoflionsx.plugins.BetterFarming.Crops.cropCustomSeedProvider;
import denoflionsx.plugins.BetterFarming.Crops.cropCustomProvider;
import denoflionsx.Old.pluginBase;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.BetterFarming.*;
import denoflionsx.Old.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.Old.customFuel_OLD;

public class pluginBetterFarming extends pluginBase {

    public static customFuel_OLD citrusJuice;
    protected String Class = "";
    private ItemIDManager IDs_CitrusJuice = new ItemIDManager(2,"CitrusJuice");

    public pluginBetterFarming() {
        mod = "mod_BetterFarming";
        name = "pluginBetterFarming";
        Class = mod;
        config = new Config("pluginBetterFarming.cfg");
        if (core.isBukkit) {
            Class = "net.minecraft.server." + Class;
        }
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            return hooked;
        }

        String BCT = "BuildCraftTransport";
        if (core.isBukkit) {
            BCT = "net.minecraft.server." + BCT;
        }
        this.addItem(Class, "appleSeeds", "Apple Seeds", 0);
        this.addItem(Class, "orangeSeeds", "Orange Seeds", 0);
        this.addItem(Class, "cocoaSeeds", "Cocoa Seeds", 0);
        this.addItem(Class, "mintSeeds", "Mint Seeds", 0);
        this.addItem(Class, "lemonSeeds", "Lemon Seeds", 0);
        this.addItem(Class, "orange", "Orange", 0);
        this.addItem(Class, "lemon", "Lemon", 0);
        this.addItem(Class, "mint", "Mint", 0);
        this.addItem(Class, "milkBottle", "Milk Bottle", 0);
        this.addBlock(Class, "cocoaTree", "Cocoa Tree", 0);
        this.addBlock(Class, "appleTree", "Apple Tree", 0);
        this.addBlock(Class, "orangeTree", "Orange Tree", 0);
        this.addBlock(Class, "lemonTree", "Lemon Tree", 0);
        this.addBlock(Class, "mintCrop", "Mint Crop", 0);
        this.addItem(BCT, "pipeWaterproof", "Waterproofing", 0);


        citrusJuice = new customFuel_OLD("Citrus Juice", 1, 10000, customFuel_OLD.populateSprites(EnumLiquidTextures.Liquids.CITRUSJUICE.getIndex()), this.IDs_CitrusJuice, Colors.Values.SALMON.getColor(), this);

        hooked = true;
        if (hooked) {
            core.print(getName() + " Loaded!");
        }
        return hooked;
    }

    @Override
    protected void recipes() {

        try {
            int s = Integer.valueOf(config.getOption("Seeds_SeedOilPerSqueeze"));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Apple Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Orange Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Cocoa Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Mint Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Lemon Seeds")}, new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, s));
            if (denLib.convertToBoolean(config.getOption("CitrusJuice_Enabled"))) {
                int s2 = Integer.valueOf(config.getOption("CitrusJuice_AmountPerSqueeze"));
                int s3 = Integer.valueOf(config.getOption("CitrusJuice_PercentChanceOfMulch"));
                ItemStack citrus = PfFManagers.ItemManager.getItem("citrusjuice");
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(this.items.get("Lemon").getItem())}, new LiquidStack(citrus.itemID, s2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), s3);
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(this.items.get("Orange").getItem())}, new LiquidStack(citrus.itemID, s2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), s3);
                addFermenterRecipes.add(citrus, 1.5F);
            }
            if (denLib.convertToBoolean(config.getOption("ForestryIntegration"))) {
                growHook.engage();
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Cocoa Tree"), new ItemStack(Item.dyePowder, 1, 3), new int[]{this.items.get("Cocoa Seeds").itemID, new ItemStack(Item.dyePowder, 1, 3).itemID}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Apple Tree"), new ItemStack(Item.appleRed, 1, 0), new int[]{this.items.get("Apple Seeds").itemID, Item.appleRed.shiftedIndex}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Orange Tree"), new ItemStack(this.items.get("Orange").getItem(), 1, 0), new int[]{this.items.get("Orange Seeds").itemID, this.items.get("Orange").itemID}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Lemon Tree"), new ItemStack(this.items.get("Lemon").getItem(), 1, 0), new int[]{this.items.get("Lemon Seeds").itemID, this.items.get("Lemon").itemID}));
                CropProviders.cerealCrops.add(new cropCustomSeedProvider(this.items.get("Mint").getItem(), this.items.get("Mint Seeds").getItem(), this.getBlock("Mint Crop"), 5));
            }
            FMLWrapper.MODE.FML.addRecipe(this.items.get("Waterproofing"), new Object[]{
                        "XXX",
                        "XMX",
                        "XXX",
                        Character.valueOf('M'), this.items.get("Mint")});
            addFermenterRecipes.bonus = this.getOptionFloat("CitrusJuice_FermenterBonus");
            addFermenterRecipes.addItem(this.items.get("Mint").getItem(), 150, this);
            PfFManagers.ContainerManager.addLiquid("Citrus Juice",PfFManagers.ItemManager.getItem("citrusjuice"), PfFManagers.ColorManager.getColor(Colors.Values.SALMON.name()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void defaults() {
        config.addDefault("[BetterFarming Options]");
        config.addDefault("Seeds_SeedOilPerSqueeze=100");
        config.addDefault("# This option makes BetterFarming plants work in the Forestry Arboretum and Logger");
        config.addDefault("ForestryIntegration=true");
        config.addDefault("# These options are for Citrus Juice");
        config.addDefault("# Citrus Juice is made with BetterFarming fruit in a squeezer");
        config.addDefault("CitrusJuice_AmountPerSqueeze=200");
        config.addDefault("CitrusJuice_PercentChanceOfMulch=40");
        config.addDefault("CitrusJuice_FermenterBonus=1.5");
        config.writeConfig();
        config.readFile();

    }
}
