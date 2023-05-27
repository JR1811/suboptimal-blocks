package net.shirojr.suboptiomal.blocks.register

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups

object ModBlocks: BlockRegistrar(ModBlockItems) {    // <- rly daanghreroiuse - JoeMama 2023
    val testBlock: Block by registerWithItem("test_block") {
        Block(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS))
    }
}

object ModBlockItems: ItemRegistrar() {
    override fun createDefaultSettings(): Item.Settings {
        // TODO("Not yet implemented")
        return Item.Settings()
    }
}