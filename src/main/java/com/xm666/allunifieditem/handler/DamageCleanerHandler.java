package com.xm666.allunifieditem.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;

public class DamageCleanerHandler {
    public static void clean(PatchedDataComponentMap map) {
        var damage = map.patch.get(DataComponents.DAMAGE);
        if (damage != null && damage.isPresent() && (Integer) damage.get() == 0) {
            map.patch.remove(DataComponents.DAMAGE);
        }
    }
}
