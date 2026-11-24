package com.xm666.allunifieditem.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.neoforged.neoforge.common.MutableDataComponentHolder;

public class DamageCleanerHandler {
    public static void clean(PatchedDataComponentMap map) {
        var damage = map.get(DataComponents.DAMAGE);
        if (damage != null && damage == 0) {
            map.remove(DataComponents.DAMAGE);
        }
    }

    public static void clean(MutableDataComponentHolder holder) {
        var damage = holder.get(DataComponents.DAMAGE);
        if (damage != null && damage == 0) {
            holder.remove(DataComponents.DAMAGE);
        }
    }
}
