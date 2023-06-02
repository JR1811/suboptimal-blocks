package net.shirojr.suboptimal.blocks.item

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.shirojr.suboptimal.blocks.register.ModBlocks
import net.shirojr.suboptimal.blocks.util.ifNotClient

class GnidloffacsItem(block: Block?, settings: Settings?) : BlockItem(block, settings) {
    override fun getPlacementContext(context: ItemPlacementContext): ItemPlacementContext? {
        if (context.side != Direction.UP) {
            return context
        }

        val world = context.world
        val blockPos = context.blockPos.offset(context.side.opposite)
        val blockState = world.getBlockState(blockPos)

        return world.ifNotClient {
            if (!blockState.isOf(ModBlocks.GnidloffacsBlock) && !blockState.isOf(Blocks.SCAFFOLDING)) {
                return@ifNotClient context
            }
            val mutPos : BlockPos.Mutable = blockPos.mutableCopy()
            //TODO: Make tag
            while (world.getBlockState(mutPos).isOf(ModBlocks.GnidloffacsBlock)
                || world.getBlockState(mutPos).isOf(Blocks.SCAFFOLDING)) {
                mutPos.y--
            }

            if (!world.getBlockState(mutPos).isAir) {
                null
            }
            else {
                ItemPlacementContext(context.player, context.hand, context.stack, BlockHitResult(mutPos.toCenterPos(),
                    Direction.UP, mutPos.toImmutable(), false))
            }
        }
    }
}