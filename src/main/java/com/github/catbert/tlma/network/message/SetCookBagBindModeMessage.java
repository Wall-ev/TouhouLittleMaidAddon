package com.github.catbert.tlma.network.message;

import com.github.catbert.tlma.item.bauble.ItemCookBag;
import com.github.catbert.tlma.network.NetworkHandler;
import com.github.catbert.tlma.network.message.client.ClientSetCookTaskModeMessage;
import com.github.catbert.tlma.util.MaidTaskDataUtil;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SetCookBagBindModeMessage(String mode) {

    public static void encode(SetCookBagBindModeMessage message, FriendlyByteBuf buf) {
        buf.writeUtf(message.mode);
    }

    public static SetCookBagBindModeMessage decode(FriendlyByteBuf buf) {
        return new SetCookBagBindModeMessage(buf.readUtf());
    }

    public static void handle(SetCookBagBindModeMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide().isServer()) {
            context.enqueueWork(() -> {
                ServerPlayer sender = context.getSender();
                if (sender == null) {
                    return;
                }
                ItemCookBag.setBindModeTag(sender.getMainHandItem(), message.mode);
            });
        }
        context.setPacketHandled(true);
    }
}