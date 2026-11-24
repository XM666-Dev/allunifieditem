package com.xm666.allunifieditem.handler;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.PlainTextContents;

public class NameConverterHandler {
    public static void convert(PatchedDataComponentMap map) {
        var customName = map.remove(DataComponents.CUSTOM_NAME);
        if (customName != null) {
            map.set(DataComponents.ITEM_NAME, NameConverterHandler.trySimplify(customName));
        }
    }

    public static Component trySimplify(Component component) {
        var contents = component.getContents();
        if (contents instanceof PlainTextContents plainTextContents) {
            var text = plainTextContents.text();
            if (text.startsWith("§f")) {
                var newContents = PlainTextContents.create(text.substring(2));
                return MutableComponent.create(newContents);
            }
        }
        return component;
    }
}
