package com.github.wallev.maidsoulkitchen;

import com.github.wallev.maidsoulkitchen.config.GeneralConfig;
import com.github.wallev.maidsoulkitchen.init.InitContainer;
import com.github.wallev.maidsoulkitchen.init.InitEffects;
import com.github.wallev.maidsoulkitchen.init.InitItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MaidsoulKitchen.MOD_ID)
public final class MaidsoulKitchen {
    public static final String MOD_ID = "maidsoulkitchen";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public MaidsoulKitchen() {
        initRegister();
        initConfigureRegister();
    }

    private static void initRegister() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        InitItems.ITEMS.register(modEventBus);
        InitEffects.EFFECTS.register(modEventBus);
        InitContainer.CONTAINER_TYPE.register(modEventBus);
    }

    private static void initConfigureRegister() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GeneralConfig.init());
    }
}
