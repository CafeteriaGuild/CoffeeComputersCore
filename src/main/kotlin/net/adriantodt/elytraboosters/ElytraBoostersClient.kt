package net.adriantodt.elytraboosters

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry

object ElytraBoostersClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientSidePacketRegistry.INSTANCE.register(ElytraBoostersPacketHandler.sync) { _, attachedData ->
            ElytraBoostersPacketHandler.updateConfigs(attachedData.readCompoundTag()!!)
        }
    }
}