package com.github.wallev.farmsoulkitchen.config;

import com.github.wallev.farmsoulkitchen.config.subconfig.RegisterConfig;
import com.github.wallev.farmsoulkitchen.config.subconfig.RenderConfig;
import com.github.wallev.farmsoulkitchen.config.subconfig.TaskConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {
    public static ForgeConfigSpec init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        TaskConfig.init(builder);
        RenderConfig.init(builder);
        RegisterConfig.init(builder);
        return builder.build();
    }
}
