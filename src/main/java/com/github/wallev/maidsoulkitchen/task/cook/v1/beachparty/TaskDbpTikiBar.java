package com.github.wallev.maidsoulkitchen.task.cook.v1.beachparty;

import com.github.wallev.maidsoulkitchen.entity.data.inner.task.CookData;
import com.github.wallev.maidsoulkitchen.init.registry.tlm.RegisterData;
import com.github.wallev.maidsoulkitchen.task.TaskInfo;
import com.github.wallev.maidsoulkitchen.task.cook.v1.common.TaskLdBaseContainerCook;
import com.github.tartaricacid.touhoulittlemaid.api.entity.data.TaskDataKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import satisfy.beachparty.block.entity.TikiBarBlockEntity;
import satisfy.beachparty.recipe.TikiBarRecipe;
import satisfy.beachparty.registry.ObjectRegistry;
import satisfy.beachparty.registry.RecipeRegistry;



public class TaskDbpTikiBar extends TaskLdBaseContainerCook<TikiBarBlockEntity, TikiBarRecipe> {
    @Override
    public int getOutputSlot() {
        return 0;
    }

    @Override
    public int getInputStartSlot() {
        return 1;
    }

    @Override
    public int getInputSize() {
        return 2;
    }

    @Override
    public boolean isCookBE(BlockEntity blockEntity) {
        return blockEntity instanceof TikiBarBlockEntity;
    }

    @Override
    public RecipeType<TikiBarRecipe> getRecipeType() {
        return RecipeRegistry.TIKI_BAR_RECIPE_TYPE.get();
    }

    @Override
    public ResourceLocation getUid() {
        return TaskInfo.DBP_TIKI_BAR.uid;
    }

    @Override
    public ItemStack getIcon() {
        return ObjectRegistry.TIKI_BAR.get().asItem().getDefaultInstance();
    }

    @Override
    public boolean isHeated(TikiBarBlockEntity be) {
        return true;
    }

    @Override
    public Container getContainer(TikiBarBlockEntity be) {
        return be;
    }

    @Override
    public TaskDataKey<CookData> getCookDataKey() {
        return RegisterData.DBP_MINE_FRIDGE;
    }

}
