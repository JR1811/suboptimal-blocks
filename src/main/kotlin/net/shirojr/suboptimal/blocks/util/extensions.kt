package net.shirojr.suboptimal.blocks.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

// add methods to existing classes (extension function)
inline fun <T> World.ifNotClient(action: (World) -> T): T? = if (!this.isClient) action(this) else null
fun Vec3d.toBlockPos() = BlockPos(this.x.toInt(), this.y.toInt(), this.z.toInt())