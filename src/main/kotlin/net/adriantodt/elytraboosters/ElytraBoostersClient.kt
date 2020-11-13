package net.adriantodt.elytraboosters

import net.adriantodt.elytraboosters.item.BoosterModelPredicateProvider
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry

object ElytraBoostersClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientSidePacketRegistry.INSTANCE.register(ElytraBoostersPacketHandler.sync) { _, attachedData ->
            ElytraBoostersPacketHandler.updateConfigs(attachedData.readCompoundTag()!!)
        }

        for (item in ElytraBoostersItems.boosterItems()) {
            FabricModelPredicateProviderRegistry.register(
                item, identifier("active_booster"), BoosterModelPredicateProvider
            )
        }
    }
}