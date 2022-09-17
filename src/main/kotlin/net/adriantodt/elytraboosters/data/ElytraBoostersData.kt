package net.adriantodt.elytraboosters.data

import net.adriantodt.elytraboosters.item.BoosterItem
import net.adriantodt.elytraboosters.plus
import net.adriantodt.elytraboosters.times
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface ElytraBoostersData {
    enum class BoosterType { STANDARD, FAST, SLOW }

    interface BoosterVelocity {
        // Positive, greater than zero. Default 0.1
        // Velocity being constantly applied, independent of drift factor.
        var constantVelocity: Double

        // Positive, greater than zero. Default 1.5
        // Velocity which is interpolated according to the drift factor.
        var interpolatingVelocity: Double

        // Between 0.0 and 1.0, Default 0.5
        // Increases flying driftness and slows velocity gain.
        var frictionFactor: Double

        fun applyBoost(entity: LivingEntity) {
            val r = entity.rotationVector
            val v = entity.velocity
            val driftFactor = 1.0 - frictionFactor
            entity.velocity = v * driftFactor + r * (constantVelocity + interpolatingVelocity * frictionFactor)
        }
    }

    interface BoosterData : BoosterVelocity {
        var ticksPerDamage: Int

        fun convertTo(stack: ItemStack, item: Item): ItemStack {
            val converted = ItemStack(item)
            converted.damage = stack.damage
            val tag = stack.tag
            tag?.getInt("TicksLeft")?.let { converted.orCreateTag.putInt("TicksLeft", it) }
            tag?.getBoolean("Unbreakable")?.let { converted.orCreateTag.putBoolean("Unbreakable", it) }
            return converted
        }

        fun secondsLeft(stack: ItemStack): Double {
            val damageTicksLeft = (stack.maxDamage - stack.damage) * ticksPerDamage
            val tagTicksLeft = stack.tag?.getInt("TicksLeft") ?: 0
            return (damageTicksLeft + tagTicksLeft) / 20.0
        }
    }

    interface FuelCartridgeData {
        val standardCartridges: Int
        val fastCartridges: Int
        val slowCartridges: Int
        val resultItem: BoosterItem
    }

    val launcherVelocity: BoosterVelocity
    fun booster(type: BoosterType): BoosterData
    fun fuelCartridge(type: BoosterType): FuelCartridgeData
}