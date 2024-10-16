package com.github.catbert.tlma.item;

import com.github.catbert.tlma.init.InitItems;
import com.github.catbert.tlma.inventory.container.item.BagType;
import com.github.catbert.tlma.inventory.container.item.CookBagAbstractContainer;
import com.github.catbert.tlma.inventory.container.item.CookBagConfigContainer;
import com.github.catbert.tlma.inventory.container.item.CookBagContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemCookBag extends Item implements MenuProvider {
    private static final int COOK_BAG_SIZE = getCookBagSize();
    private static final String CONTAINER_TAG = "CookBagContainer";
    private static final String BIND_MODE_TAG = "CookBagBindMode";
    private static final String BIND_POS_TAG = "CookBagBindPos";
    private static final int BIND_SIZE = 3;

    public ItemCookBag() {
        super(new Item.Properties().stacksTo(1));
    }

    public static void actionModePos(ItemStack stack, String mode, BlockPos blockPos) {
        if (stack.is(InitItems.COOK_BAG.get()) && !mode.isEmpty()) {
            CompoundTag tag = stack.getOrCreateTag();
            CompoundTag compound = tag.getCompound(BIND_POS_TAG);
            ListTag list = compound.getList(mode, Tag.TAG_COMPOUND);
            AtomicBoolean remove = new AtomicBoolean(false);
            list.removeIf(tag1 -> {
                if (NbtUtils.readBlockPos((CompoundTag) tag1).equals(blockPos)) {
                    remove.set(true);
                    return true;
                }
                return false;
            });

            if (!remove.get()) {
                list.add(NbtUtils.writeBlockPos(blockPos));
                compound.put(mode, list);
            }
            tag.put(BIND_POS_TAG, compound);
        }
    }

    public static List<BlockPos> getBindModePoses(ItemStack stack, String mode) {
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(BIND_POS_TAG, Tag.TAG_COMPOUND)) {
                CompoundTag tag1 = tag.getCompound(BIND_POS_TAG);
                ListTag list = tag1.getList(mode, Tag.TAG_COMPOUND);
                return list.stream().map(tag2 -> NbtUtils.readBlockPos((CompoundTag) tag2)).toList();
            }
        }
        return Collections.emptyList();
    }

    public static String getBindMode(ItemStack stack) {
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                return tag.getString(BIND_MODE_TAG);
            }
        }
        return "";
    }

    public static void setBindModeTag(ItemStack stack, String mode) {
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putString(BIND_MODE_TAG, mode);
        }
    }

    private static int getCookBagSize() {
        int size = 0;
        for (BagType value : BagType.values()) {
            size += value.size * 9;
        }
        return size;
    }

    public static Map<BagType, ItemStackHandler> getContainers(ItemStack stack) {
        Map<BagType, ItemStackHandler> bagTypeItemStackHandlerHashMap = new HashMap<>();
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag tag = stack.getTag();
            if (tag == null || !tag.contains(CONTAINER_TAG, Tag.TAG_COMPOUND)) {
                for (BagType value : BagType.values()) {
                    ItemStackHandler handler = new ItemStackHandler(value.size * 9);
                    bagTypeItemStackHandlerHashMap.put(value, handler);
                }
            } else {
                CompoundTag compound = tag.getCompound(CONTAINER_TAG);
                for (BagType value : BagType.values()) {
                    ItemStackHandler handler = new ItemStackHandler(value.size * 9);
                    if (compound.contains(value.name, Tag.TAG_COMPOUND)) {
                        handler.deserializeNBT(compound.getCompound(value.name));
                    }
                    bagTypeItemStackHandlerHashMap.put(value, handler);
                }
            }
        }
        return bagTypeItemStackHandlerHashMap;
    }

    public static void setContainer(ItemStack stack, Map<BagType, ItemStackHandler> handlers) {
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag orCreateTag = stack.getOrCreateTag();
            CompoundTag compound = orCreateTag.getCompound(CONTAINER_TAG);
            handlers.forEach((bagType, itemStackHandler) -> {
                compound.put(bagType.name, itemStackHandler.serializeNBT());
            });
            orCreateTag.put(CONTAINER_TAG, compound);
        }
    }

    public static ItemStackHandler getContainer(ItemStack stack) {
        ItemStackHandler handler = new ItemStackHandler(COOK_BAG_SIZE);
        if (stack.is(InitItems.COOK_BAG.get())) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(CONTAINER_TAG, Tag.TAG_COMPOUND)) {
                handler.deserializeNBT(tag.getCompound(CONTAINER_TAG));
            }
        }
        return handler;
    }

    public static void setContainer(ItemStack stack, ItemStackHandler itemStackHandler) {
        if (stack.is(InitItems.COOK_BAG.get())) {
            stack.getOrCreateTag().put(CONTAINER_TAG, itemStackHandler.serializeNBT());
        }
    }

    public static boolean openCookBagGuiFromSideTab(Player player, int tabIndex) {
        if (player instanceof ServerPlayer) {
            NetworkHooks.openScreen((ServerPlayer) player, getGuiProviderFromSideTab(tabIndex), (buffer) -> buffer.writeItem(player.getMainHandItem()));
        }
        return true;
    }

    private static MenuProvider getGuiProviderFromSideTab(int tabIndex) {
        if (tabIndex == 0) {
            return getCookBagConfigContainer();
        } else {
            return getCookBagContainer();
        }
    }

    private static MenuProvider getCookBagContainer() {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Maid Cook Container");
            }

            @Override
            public CookBagAbstractContainer createMenu(int index, Inventory playerInventory, Player player) {
                return new CookBagContainer(index, playerInventory, player.getMainHandItem());
            }
        };
    }

    private static MenuProvider getCookBagConfigContainer() {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Maid Cook Config Container");
            }

            @Override
            public CookBagAbstractContainer createMenu(int index, Inventory playerInventory, Player player) {
                return new CookBagConfigContainer(index, playerInventory, player.getMainHandItem());
            }
        };
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        BlockEntity te = worldIn.getBlockEntity(pos);

        if (hand != InteractionHand.MAIN_HAND) {
            return super.useOn(context);
        }
        if (player == null) {
            return super.useOn(context);
        }

        if (te instanceof RandomizableContainerBlockEntity rbe && rbe.canOpen(player)) {
            ItemStack stack = player.getMainHandItem();
            String bindMode = getBindMode(stack);
            if (!bindMode.isEmpty()) {
                actionModePos(stack, bindMode, pos);
                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            }
        }

        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (handIn == InteractionHand.MAIN_HAND && playerIn instanceof ServerPlayer) {
            NetworkHooks.openScreen((ServerPlayer) playerIn, this, (buffer) -> buffer.writeItem(playerIn.getMainHandItem()));
            return InteractionResultHolder.success(playerIn.getMainHandItem());
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Cook Bag Container");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CookBagConfigContainer(pContainerId, pPlayerInventory, pPlayer.getMainHandItem());
    }
}
