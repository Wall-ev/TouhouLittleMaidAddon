package com.github.wallev.farmsoulkitchen.init.registry.tlm;

import com.github.wallev.farmsoulkitchen.config.subconfig.RegisterConfig;
import com.github.wallev.farmsoulkitchen.foundation.utility.Mods;
import com.github.wallev.farmsoulkitchen.task.cook.v1.bnc.TaskBncKey;
import com.github.wallev.farmsoulkitchen.task.cook.v1.crokckpot.TaskCrockPot;
import com.github.wallev.farmsoulkitchen.task.cook.v1.drinkbeer.TaskDbBeerBarrel;
import com.github.wallev.farmsoulkitchen.task.cook.v1.fd.TaskFDCookPot;
import com.github.wallev.farmsoulkitchen.task.cook.v1.fd.TaskFdCuttingBoard;
import com.github.wallev.farmsoulkitchen.task.cook.v1.kitchencarrot.TaskAirCompressor;
import com.github.wallev.farmsoulkitchen.task.cook.v1.kitchencarrot.TaskKkBrewingBarrel;
import com.github.wallev.farmsoulkitchen.task.cook.v1.mc.TaskFurnace;
import com.github.wallev.farmsoulkitchen.task.cook.v1.md.TaskMDCopperPot;
import com.github.wallev.farmsoulkitchen.task.cook.v1.yhc.TaskDryingRack;
import com.github.wallev.farmsoulkitchen.task.cook.v1.yhc.TaskFermentationTank;
import com.github.wallev.farmsoulkitchen.task.cook.v1.yhc.TaskYhcMoka;
import com.github.wallev.farmsoulkitchen.task.cook.v1.yhc.TaskYhcTeaKettle;
import com.github.wallev.farmsoulkitchen.task.farm.TaskBerryFarm;
import com.github.wallev.farmsoulkitchen.task.farm.TaskCompatMelonFarm;
import com.github.wallev.farmsoulkitchen.task.farm.TaskFruitFarm;
import com.github.wallev.farmsoulkitchen.task.farm.TaskSSFarm;
import com.github.wallev.farmsoulkitchen.task.other.TaskFeedAnimalT;
import com.github.tartaricacid.touhoulittlemaid.entity.task.TaskManager;

public final class RegisterTask {
    private RegisterTask() {
    }

    public static void register(TaskManager manager) {
        if (Mods.MC.isLoaded() && RegisterConfig.COMPAT_MELON_FARM_TASK_ENABLED.get()){
            manager.add(new TaskCompatMelonFarm());
        }
        if (Mods.MC.isLoaded() && RegisterConfig.BERRY_FARM_TASK_ENABLED.get()) {
            manager.add(new TaskBerryFarm());
        }
        if (Mods.MC.isLoaded() && RegisterConfig.FRUIT_FARM_TASK_ENABLED.get()) {
            manager.add(new TaskFruitFarm());
        }
        if (Mods.MC.isLoaded() && RegisterConfig.FEED_ANIMAL_T_TASK_ENABLED.get()) {
            manager.add(new TaskFeedAnimalT());
        }

        if (Mods.SS.isLoaded() && RegisterConfig.SERENESEASONS_FARM_TASK_ENABLED.get()) {
            manager.add(new TaskSSFarm());
        }


        if (Mods.MC.isLoaded() && RegisterConfig.FURNACE_TASK_ENABLED.get()) {
            manager.add(new TaskFurnace());
        }


        if (Mods.FD.isLoaded() && RegisterConfig.FD_COOK_POT_TASK_ENABLED.get()) {
            manager.add(new TaskFDCookPot());
        }
        if (Mods.FD.isLoaded() && RegisterConfig.FD_CUTTING_BOARD_TASK_ENABLED.get()) {
            manager.add(new TaskFdCuttingBoard());
        }
        if (Mods.MD.isLoaded() && RegisterConfig.MD_COOK_POT_TASK_ENABLED.get()) {
            manager.add(new TaskMDCopperPot());
        }
        if (Mods.BNCD.isLoaded() && RegisterConfig.BNC_KEY_TASK_ENABLED.get()) {
            manager.add(new TaskBncKey());
        }
        if (Mods.YHCD.isLoaded() && RegisterConfig.YHC_MOKA_TASK_ENABLED.get()) {
            manager.add(new TaskYhcMoka());
        }
        if (Mods.YHCD.isLoaded() && RegisterConfig.YHC_TEA_KETTLE_TASK_ENABLED.get()) {
            manager.add(new TaskYhcTeaKettle());
        }
        if (Mods.YHCD.isLoaded() && RegisterConfig.YHC_DRYING_RACK_TASK_ENABLED.get()) {
            manager.add(new TaskDryingRack());
        }
        if (Mods.YHCD.isLoaded() && RegisterConfig.YHC_FERMENTATION_TANK_TASK_ENABLED.get()) {
            manager.add(new TaskFermentationTank());
        }


        if (Mods.CP.isLoaded() && RegisterConfig.CP_CROk_POT_TASK_ENABLED.get()) {
            manager.add(new TaskCrockPot());
        }
        if (Mods.DB.isLoaded() && RegisterConfig.DB_BEER_TASK_ENABLED.get()) {
            manager.add(new TaskDbBeerBarrel());
        }
        if (Mods.KK.isLoaded() && RegisterConfig.KK_BREW_BARREL.get()) {
            manager.add(new TaskKkBrewingBarrel());
        }
        if (Mods.KK.isLoaded() && RegisterConfig.KK_AIR_COMPRESSOR.get()) {
            manager.add(new TaskAirCompressor());
        }
    }
}
