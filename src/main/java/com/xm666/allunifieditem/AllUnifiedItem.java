package com.xm666.allunifieditem;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AllUnifiedItem.MODID)
public class AllUnifiedItem {
    public static final String MODID = "allunifieditem";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AllUnifiedItem(IEventBus modEventBus, ModContainer modContainer) {
    }
}
