package net.shirojr.suboptimal.blocks.register

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

interface Registrar {
    fun register(modId: String)

}

class RegistrarPipeline(private val modId: String, init: RegistrarPipeline.() -> Unit) {

    // Unit ~= void in java...
    private val registrars = mutableListOf<Registrar>()

    init {
        // primary ctor extension?
        init(this)
    }

    fun add(registrar: Registrar) {
        this.registrars.add(registrar)
    }

    fun registerAll() {
        for(entry in registrars) {
            entry.register(this.modId)
        }
    }
}

open class ObjectRegistrar<T>(private val registry: Registry<T>) : Registrar, Iterable<Pair<String, T>> {
    private val entries = mutableListOf<Pair<String, Lazy<T>>>()

    fun <U> register(path: String, obj: () -> U): Lazy<U> where U : T {
        val created = lazy(obj)
        entries.add(path to created)
        return created
    }

    fun <U> registerAndThen(path: String, obj: () -> U, then: (Pair<String, U>) -> Unit): Lazy<U> where U : T = this.register(path) {
        val created = obj()
        then(Pair(path, created))
        created
    }

    override fun register(modId: String) {

        entries.forEach { (path, obj) ->
            Registry.register(this.registry, Identifier(modId, path), obj.value)
        }
    }

    override fun iterator(): Iterator<Pair<String, T>> =
        this.entries.map { (key, value) -> Pair(key, value.value) }.iterator()
}

abstract class ItemRegistrar : ObjectRegistrar<Item>(Registries.ITEM) {
    abstract fun createDefaultSettings(): Item.Settings
}

open class BlockRegistrar(protected val items: ItemRegistrar?) : ObjectRegistrar<Block>(Registries.BLOCK) {
    fun <U> registerWithItem(id: String, block: () -> U): Lazy<U> where U : Block = this.registerAndThen(id, block) {
        this.items?.register(id) {
            BlockItem(it.second, this.items.createDefaultSettings())
        }
    }
}