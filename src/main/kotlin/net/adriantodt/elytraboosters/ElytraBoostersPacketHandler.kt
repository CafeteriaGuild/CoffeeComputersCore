package net.adriantodt.elytraboosters

import io.netty.buffer.Unpooled
import net.adriantodt.elytraboosters.ElytraBoosters.configHolder
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType.*
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity


object ElytraBoostersPacketHandler {
    val sync = identifier("sync")
    fun sendServerConfig(player: ServerPlayerEntity) {
        val tag = CompoundTag()
        tag.putDouble("launcherConstantVelocity", configHolder.config.forwardLauncher.constantVelocity)
        tag.putDouble(
            "launcherInterpolatingVelocity",
            configHolder.config.forwardLauncher.interpolatingVelocity
        )
        tag.putDouble("launcherFrictionFactor", configHolder.config.forwardLauncher.frictionFactor)
        tag.putDouble("standardBoosterConstantVelocity", configHolder.config.standardBooster.constantVelocity)
        tag.putDouble(
            "standardBoosterInterpolatingVelocity",
            configHolder.config.standardBooster.interpolatingVelocity
        )
        tag.putDouble("standardBoosterFrictionFactor", configHolder.config.standardBooster.frictionFactor)
        tag.putInt("standardBoosterTicksPerDamage", configHolder.config.standardBooster.ticksPerDamage)
        tag.putDouble("slowBoosterConstantVelocity", configHolder.config.slowBooster.constantVelocity)
        tag.putDouble(
            "slowBoosterInterpolatingVelocity",
            configHolder.config.slowBooster.interpolatingVelocity
        )
        tag.putDouble("slowBoosterFrictionFactor", configHolder.config.slowBooster.frictionFactor)
        tag.putInt("slowBoosterTicksPerDamage", configHolder.config.slowBooster.ticksPerDamage)
        tag.putDouble("fastBoosterConstantVelocity", configHolder.config.fastBooster.constantVelocity)
        tag.putDouble(
            "fastBoosterInterpolatingVelocity",
            configHolder.config.fastBooster.interpolatingVelocity
        )
        tag.putDouble("fastBoosterFrictionFactor", configHolder.config.fastBooster.frictionFactor)
        tag.putInt("fastBoosterTicksPerDamage", configHolder.config.fastBooster.ticksPerDamage)
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(
            player, sync, PacketByteBuf(Unpooled.buffer()).writeCompoundTag(tag)
        )
    }

    fun updateConfigs(tag: CompoundTag) {
        ElytraBoosters.logger.info("Syncing local configs from the server!")
        ElytraBoosters.data.launcherVelocity.constantVelocity = tag.getDouble("launcherConstantVelocity")
        ElytraBoosters.data.launcherVelocity.interpolatingVelocity = tag.getDouble("launcherInterpolatingVelocity")
        ElytraBoosters.data.launcherVelocity.frictionFactor = tag.getDouble("launcherFrictionFactor")
        ElytraBoosters.data.booster(STANDARD).apply {
            constantVelocity = tag.getDouble("standardBoosterConstantVelocity")
            interpolatingVelocity = tag.getDouble("standardBoosterInterpolatingVelocity")
            frictionFactor = tag.getDouble("standardBoosterFrictionFactor")
            ticksPerDamage = tag.getInt("standardBoosterTicksPerDamage")
        }
        ElytraBoosters.data.booster(SLOW).apply {
            constantVelocity = tag.getDouble("slowBoosterConstantVelocity")
            interpolatingVelocity = tag.getDouble("slowBoosterInterpolatingVelocity")
            frictionFactor = tag.getDouble("slowBoosterFrictionFactor")
            ticksPerDamage = tag.getInt("slowBoosterTicksPerDamage")
        }
        ElytraBoosters.data.booster(FAST).apply {
            constantVelocity = tag.getDouble("fastBoosterConstantVelocity")
            interpolatingVelocity = tag.getDouble("fastBoosterInterpolatingVelocity")
            frictionFactor = tag.getDouble("fastBoosterFrictionFactor")
            ticksPerDamage = tag.getInt("fastBoosterTicksPerDamage")
        }
        ElytraBoosters.logger.info("We're now be in sync.")
    }
}