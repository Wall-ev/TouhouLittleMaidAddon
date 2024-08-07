package com.github.catbert.tlma.compat.builder.task.builder;

import com.github.catbert.tlma.compat.builder.task.ab.CustomTaskABFeedBuilder;
import net.minecraft.resources.ResourceLocation;

public class CustomTaskFeedBuilder extends CustomTaskABFeedBuilder<CustomTaskFeed> {

    protected CustomTaskFeedBuilder(ResourceLocation id) {
        super(id);
    }

    public CustomTaskFeedBuilder(ResourceLocation id, boolean debug) {
        super(id, debug);
    }

    public static CustomTaskFeedBuilder create(ResourceLocation id) {
        return new CustomTaskFeedBuilder(id);
    }

    @Override
    protected CustomTaskFeed getTask() {
        return new CustomTaskFeed(this);
    }
}
