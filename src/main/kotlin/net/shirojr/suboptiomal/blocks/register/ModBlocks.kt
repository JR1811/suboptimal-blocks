package net.shirojr.suboptiomal.blocks.register

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.ScaffoldingBlock
import net.minecraft.item.Item
import net.shirojr.suboptiomal.blocks.block.GnidloffacsBlock
import net.shirojr.suboptiomal.blocks.item.GnidloffacsItem

object ModBlocks: BlockRegistrar(ModBlockItems) {    // <- rly daanghreroiuse - JoeMama 2023
    val TestBlock: Block by registerWithItem("test_block") {
        Block(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS))
    }


    val GnidloffacsBlock: GnidloffacsBlock by registerAndThen("gnidloffacs_block", {
        GnidloffacsBlock(FabricBlockSettings.copyOf(Blocks.SCAFFOLDING))
    }) {(id, block) ->
        this.items?.register(id) {
            GnidloffacsItem(block, this.items.createDefaultSettings())
        }
    }
}

object ModBlockItems: ItemRegistrar() {
    override fun createDefaultSettings(): Item.Settings {
        // TODO("Not yet implemented")
        return Item.Settings()
    }
}