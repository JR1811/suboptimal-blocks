package net.shirojr.suboptimal.blocks

import net.fabricmc.api.ModInitializer
import net.shirojr.suboptimal.blocks.register.ModBlockItems
import net.shirojr.suboptimal.blocks.register.ModBlocks
import net.shirojr.suboptimal.blocks.register.RegistrarPipeline
import org.slf4j.LoggerFactory

object SuboptimalBlocks : ModInitializer {
    private const val MOD_ID = "suboptimal-blocks"
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        RegistrarPipeline(MOD_ID) {
            add(ModBlocks)
            add(ModBlockItems)
        }.registerAll()
    }
}