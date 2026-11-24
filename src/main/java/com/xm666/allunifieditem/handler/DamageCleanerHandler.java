package com.xm666.allunifieditem.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.item.ItemStack;

public class DamageCleanerHandler {
    public static void clean(PatchedDataComponentMap map) {
        var damage = map.get(DataComponents.DAMAGE);
        if (damage != null && damage == 0) {
            map.patch.remove(DataComponents.DAMAGE);
        }
    }

    public static void clean(ItemStack stack) {
        clean(stack.components);
    }
}
