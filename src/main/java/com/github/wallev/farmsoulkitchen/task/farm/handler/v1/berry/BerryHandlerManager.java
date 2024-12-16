package com.github.wallev.farmsoulkitchen.task.farm.handler.v1.berry;


import com.github.wallev.farmsoulkitchen.task.farm.handler.v1.IFarmHandlerManager;

public enum BerryHandlerManager implements IFarmHandlerManager<BerryHandler> {

    MINECRAFT(new VanillaBerryHandler()),
    SIMPLE_FARMING(new SimpleFarmingBerryHandler()),
    COMPAT(new CompatBerryHandler());

    private final BerryHandler berryHandler;

    BerryHandlerManager(BerryHandler berryHandler) {
        this.berryHandler = berryHandler;
    }

    public BerryHandler getFarmHandler() {
        return berryHandler;
    }
}
