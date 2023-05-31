package net.shirojr.suboptiomal.blocks.item

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.shirojr.suboptiomal.blocks.register.ModBlocks

class GnidloffacsItem(block: Block?, settings: Settings?) : BlockItem(block, settings) {
    override fun getPlacementContext(context: ItemPlacementContext): ItemPlacementContext? {
        val world = context.world
        val blockPos = context.blockPos
        val blockState = world.getBlockState(blockPos)

        if (!blockState.isOf(ModBlocks.GnidloffacsBlock)) {
            return context
        }

        val mutPos : BlockPos.Mutable = blockPos.mutableCopy()
        //TODO: Make tag
        while (world.getBlockState(mutPos).isOf(ModBlocks.GnidloffacsBlock)
            || world.getBlockState(mutPos).isOf(Blocks.SCAFFOLDING)) {
            mutPos.y--
        }


        return super.getPlacementContext(context)
    }

}