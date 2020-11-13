package net.adriantodt.elytraboosters

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry

fun identifier(path: String) = Identifier("elytraboosters", path)

fun Identifier.item(item: Item) = apply {
    Registry.register(Registry.ITEM, this, item)
}

var CompoundTag.active
    get() = getBoolean("Active")
    set(value) = putBoolean("Active", value)

fun toolSettings(): Item.Settings = Item.Settings().group(ItemGroup.TRANSPORTATION)

fun itemSettings(): Item.Settings = Item.Settings().group(ItemGroup.MISC)

operator fun Vec3d.times(other: Double): Vec3d = multiply(other)

operator fun Vec3d.plus(other: Vec3d): Vec3d = add(other)