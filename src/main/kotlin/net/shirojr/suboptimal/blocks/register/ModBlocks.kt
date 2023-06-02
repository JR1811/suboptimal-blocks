package net.shirojr.suboptimal.blocks.register

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.shirojr.suboptimal.blocks.block.GnidloffacsBlock
import net.shirojr.suboptimal.blocks.item.GnidloffacsItem

object ModBlocks: BlockRegistrar(ModBlockItems) {    // <- rly daanghreroiuse - JoeMama 2023

    /*val TestBlock: Block by registerWithItem("test_block") {
        Block(FabricBlockSettings.copyOf(Blocks.SPRUCE_PLANKS))
    }*/

    val GnidloffacsBlock: GnidloffacsBlock by registerAndThen("gnidloffacs", {
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