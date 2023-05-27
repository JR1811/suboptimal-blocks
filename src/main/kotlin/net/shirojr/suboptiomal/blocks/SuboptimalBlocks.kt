package net.shirojr.suboptiomal.blocks

import net.fabricmc.api.ModInitializer
import net.shirojr.suboptiomal.blocks.register.ModBlockItems
import net.shirojr.suboptiomal.blocks.register.ModBlocks
import net.shirojr.suboptiomal.blocks.register.RegistrarPipeline
import org.slf4j.LoggerFactory

object SuboptimalBlocks : ModInitializer {
    private const val MOD_ID = "suboptiomal-blocks"
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {

        RegistrarPipeline(MOD_ID) {
            add(ModBlocks)
            add(ModBlockItems)
        }.registerAll()
    }
}