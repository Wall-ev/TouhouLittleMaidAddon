package com.github.catbert.tlma.inventory.container;

import com.github.catbert.tlma.entity.passive.CookTaskData;
import com.github.tartaricacid.touhoulittlemaid.api.task.IMaidTask;
import net.minecraft.nbt.CompoundTag;

public class ClientTaskSettingMenuManager {
    private static CompoundTag menuData;
    private static CookTaskData cookTaskData;
    private static IMaidTask task;
    private ClientTaskSettingMenuManager() {
    }

    public static void setCookTaskData(CookTaskData cookTaskData) {
        ClientTaskSettingMenuManager.cookTaskData = cookTaskData;
    }

    public static CookTaskData getCookTaskData() {
        return cookTaskData;
    }

    public static void setMenuData(CompoundTag menuData) {
        ClientTaskSettingMenuManager.menuData = menuData;
    }

    public static CompoundTag getMenuData() {
        return ClientTaskSettingMenuManager.menuData;
    }

    public static IMaidTask getTask() {
        return task;
    }

    public static void setTask(IMaidTask task) {
        ClientTaskSettingMenuManager.task = task;
    }

    public static void clearMenuData() {
        ClientTaskSettingMenuManager.menuData = null;
    }
}
