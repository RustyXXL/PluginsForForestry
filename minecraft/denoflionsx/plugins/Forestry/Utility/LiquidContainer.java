package denoflionsx.plugins.Forestry.Utility;

import buildcraft.api.liquids.LiquidData;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import net.minecraft.src.ItemStack;

public class LiquidContainer {

    // Wraps LiquidData to make it work like the old LiquidContainer.
    private LiquidData liquid;
    public ItemStack filled;
    public ItemStack empty;

    public LiquidContainer(LiquidStack l, ItemStack filled, ItemStack empty) {
        this.liquid = new LiquidData(l, filled, empty);
        this.filled = this.liquid.filled;
        this.empty = this.liquid.container;
    }

    public LiquidContainer(LiquidStack l, ItemStack filled, ItemStack empty, boolean isBucket) {
        this.liquid = new LiquidData(l, filled, empty);
        this.filled = this.liquid.filled;
        this.empty = this.liquid.container;
    }

    public LiquidData getLiquid() {
        return liquid;
    }

    public static class LiquidManagerWrapper {

        public static void registerLiquidContainer(LiquidContainer l) {
            LiquidManager.liquids.add(l.getLiquid());
        }

        public static LiquidContainer getEmptyContainer(ItemStack i, LiquidStack s) {
            ItemStack r = LiquidManager.fillLiquidContainer(s.itemID, s.amount, i);
            return new LiquidContainer(s, r, i);
        }
    }
}
