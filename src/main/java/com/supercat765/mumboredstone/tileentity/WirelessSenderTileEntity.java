package com.supercat765.mumboredstone.tileentity;

import com.supercat765.mumboredstone.init.MRTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

import static net.minecraft.state.properties.BlockStateProperties.POWERED;

public class WirelessSenderTileEntity extends WirelessRedstoneTileEntity implements ITickableTileEntity {

    public WirelessSenderTileEntity() {
        this(MRTileEntityTypes.WIRELESS_REDSTONE_SENDER.get());
    }

    public WirelessSenderTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;
        BlockState blockState = getBlockState();
        if (!blockState.hasProperty(POWERED))
            return;
        boolean currentlyPowered = blockState.get(POWERED);
        // TODO: This doesn't work with more than 1 emitter, maybe this should be changed to an OR and reset at the end/start of each tick?
        notifyListeners(getChannel(), currentlyPowered);
    }

    @Override
    public void remove() {
        notifyListeners(getChannel(), false);
    }
}
