package com.github.wallev.farmsoulkitchen.compat.cloth;

import com.github.wallev.farmsoulkitchen.FarmsoulKitchen;
import com.github.wallev.farmsoulkitchen.config.subconfig.RegisterConfig;
import com.github.wallev.farmsoulkitchen.config.subconfig.TaskConfig;
import com.github.wallev.farmsoulkitchen.event.MelonConfigEvent;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuIntegration {
    private static final Component MENU_TITLE = Component.translatable("config.farmsoulkitchen.title");
    private static final Component MENU_TITLE_TIP = Component.translatable("config.farmsoulkitchen.title.tip").withStyle(ChatFormatting.YELLOW);
    private static final String MOD_TIP = "[Addon: Farm And Cook]";
    public static ConfigBuilder getConfigBuilder() {
        ConfigBuilder root = ConfigBuilder.create().setTitle(MENU_TITLE);
        root.setGlobalized(true);
        root.setGlobalizedExpanded(false);
        return getConfigBuilder(root, false);
    }

    public static ConfigBuilder getConfigBuilder(ConfigBuilder root, boolean tlmEntry) {
        addConfig(root, root.entryBuilder(), tlmEntry);
        return root;
    }

    public static void addConfig(ConfigBuilder root, ConfigEntryBuilder entryBuilder, boolean tlmEntry) {
        taskConfig(root, entryBuilder, tlmEntry);
        registerConfig(root, entryBuilder, tlmEntry);
    }

    private static void registerConfig(ConfigBuilder root, ConfigEntryBuilder entryBuilder, boolean tlmEntry) {
        MutableComponent entryTitle = Component.translatable("config.farmsoulkitchen.register");
        MutableComponent addition = Component.literal("");
        if (tlmEntry) {
            entryTitle.append(MENU_TITLE_TIP);
            addition.append(Component.literal("\n" + MOD_TIP).withStyle(ChatFormatting.BLUE))
                    .append(Component.literal("\nModId: " + FarmsoulKitchen.MOD_ID).withStyle(ChatFormatting.DARK_GRAY));
        }
        ConfigCategory register = root.getOrCreateCategory(entryTitle);

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.berry_farm_task"), RegisterConfig.BERRY_FARM_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.berry_farm_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.BERRY_FARM_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.fruit_farm_task"), RegisterConfig.FRUIT_FARM_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.fruit_farm_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FRUIT_FARM_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.feed_animal_t"), RegisterConfig.FEED_ANIMAL_T_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.feed_animal_t.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FEED_ANIMAL_T_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.compat_melon_farm_task"), RegisterConfig.COMPAT_MELON_FARM_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.compat_melon_farm_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.COMPAT_MELON_FARM_TASK_ENABLED::set).build());

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.serene_seasons_farm_task"), RegisterConfig.SERENESEASONS_FARM_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.serene_seasons_farm_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.SERENESEASONS_FARM_TASK_ENABLED::set).build());

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.feed_and_drink_task"), RegisterConfig.FEED_AND_DRINK_OWNER_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.feed_and_drink_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FEED_AND_DRINK_OWNER_TASK_ENABLED::set).build());

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.furnace_task"), RegisterConfig.FURNACE_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.furnace_task.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FURNACE_TASK_ENABLED::set).build());

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.fd_cook_pot"), RegisterConfig.FD_COOK_POT_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.fd_cook_pot.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FD_COOK_POT_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.fd_cutting_board"), RegisterConfig.FD_CUTTING_BOARD_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.fd_cutting_board.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.FD_CUTTING_BOARD_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.md_cook_pot"), RegisterConfig.MD_COOK_POT_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.md_cook_pot.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.MD_COOK_POT_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.bnc_key"), RegisterConfig.BNC_KEY_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.bnc_key.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.BNC_KEY_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.yhc_moka"), RegisterConfig.YHC_MOKA_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.yhc_moka.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.YHC_MOKA_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.yhc_tea_kettle"), RegisterConfig.YHC_TEA_KETTLE_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.yhc_tea_kettle.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.YHC_TEA_KETTLE_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.yhc_drying_rack"), RegisterConfig.YHC_DRYING_RACK_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.yhc_drying_rack.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.YHC_DRYING_RACK_TASK_ENABLED::set).build());

        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.kk_brew_barrel"), RegisterConfig.KK_BREW_BARREL.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.kk_brew_barrel.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.KK_BREW_BARREL::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.kk_air_compressor"), RegisterConfig.KK_AIR_COMPRESSOR.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.kk_air_compressor.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.KK_AIR_COMPRESSOR::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.db_beer"), RegisterConfig.DB_BEER_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.db_beer.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.DB_BEER_TASK_ENABLED::set).build());
        register.addEntry(entryBuilder.startBooleanToggle(Component.translatable("config.farmsoulkitchen.register.cp_crock_pot"), RegisterConfig.CP_CROk_POT_TASK_ENABLED.get())
                .setDefaultValue(true).setTooltip(Component.translatable("config.farmsoulkitchen.register.cp_crock_pot.tooltip"),
                        Component.translatable("config.farmsoulkitchen.register.restart_warn.tooltip"), addition)
                .setSaveConsumer(RegisterConfig.CP_CROk_POT_TASK_ENABLED::set).build());
    }

    private static void taskConfig(ConfigBuilder root, ConfigEntryBuilder entryBuilder, boolean tlmEntry) {
        MutableComponent entryTitle = Component.translatable("config.farmsoulkitchen.task");
        MutableComponent addition = Component.literal("");
        if (tlmEntry) {
            entryTitle.append(MENU_TITLE_TIP);
            addition.append(Component.literal("\n" + MOD_TIP).withStyle(ChatFormatting.BLUE))
                    .append(Component.literal("\nModId: " + FarmsoulKitchen.MOD_ID).withStyle(ChatFormatting.DARK_GRAY));
        }
        ConfigCategory task = root.getOrCreateCategory(entryTitle);

        task.addEntry(entryBuilder.startStrList(Component.translatable("config.farmsoulkitchen.task.melon_and_stem_list"), TaskConfig.MELON_AND_STEM_LIST.get().stream().map(s -> s.get(0) + "," + s.get(1)).toList())
                .setDefaultValue(TaskConfig.MELON_AND_STEM_LIST.getDefault().stream().map(s -> s.get(0) + "," + s.get(1)).toList())
                .setTooltip(Component.translatable("config.farmsoulkitchen.task.melon_and_stem_list.tooltip"), addition)
                .setSaveConsumer(l -> {
                    List<List<String>> melonAndStemList = new ArrayList<>();
                    for (String s : l) {
                        String[] split = s.split(",");
                        if (split.length < 2) continue;
                        melonAndStemList.add(Arrays.asList(split[0], split[1]));
                    }
                    TaskConfig.MELON_AND_STEM_LIST.set(melonAndStemList);
                    MelonConfigEvent.handleConfig();
                }).build());

        task.addEntry(entryBuilder.startIntField(Component.translatable("config.farmsoulkitchen.task.feed_animal_t"), TaskConfig.FEED_SINGLE_ANIMAL_MAX_NUMBER.get())
                .setDefaultValue(TaskConfig.FEED_SINGLE_ANIMAL_MAX_NUMBER.getDefault())
                .setTooltip(Component.translatable("config.farmsoulkitchen.task.feed_animal_t.tooltip"), addition)
                .setSaveConsumer(TaskConfig.FEED_SINGLE_ANIMAL_MAX_NUMBER::set).build());
    }

    public static void registerModsPage() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
                new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> getConfigBuilder().setParentScreen(parent).build()));
    }
}
