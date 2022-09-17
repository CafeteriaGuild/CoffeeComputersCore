package net.adriantodt.elytraboosters

import net.adriantodt.elytraboosters.ElytraBoosters.data
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType.*
import net.adriantodt.elytraboosters.item.BoosterItem
import net.adriantodt.elytraboosters.item.ForwardLauncherItem
import net.adriantodt.elytraboosters.item.FuelCartridgeItem
import net.adriantodt.elytraboosters.item.LoreItem

@Suppress("MemberVisibilityCanBePrivate")
object ElytraBoostersItems {
    val emptyBooster = LoreItem(toolSettings())

    val fastBooster = BoosterItem(toolSettings().maxDamage(100), data.booster(FAST))
    //val fastBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(100), data.booster(FAST))

    val standardBooster = BoosterItem(toolSettings().maxDamage(200), data.booster(STANDARD))
    //val standardBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(200), data.booster(STANDARD))

    val slowBooster = BoosterItem(toolSettings().maxDamage(400), data.booster(SLOW))
    //val slowBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(400), data.booster(SLOW))

    val forwardLauncher = ForwardLauncherItem(toolSettings().maxDamage(128), data.launcherVelocity)

    val standardFuelCartridge = FuelCartridgeItem(itemSettings(), data.fuelCartridge(STANDARD))
    val slowFuelCartridge = FuelCartridgeItem(itemSettings(), data.fuelCartridge(SLOW))
    val fastFuelCartridge = FuelCartridgeItem(itemSettings(), data.fuelCartridge(FAST))

    fun register() {
        identifier("booster_empty").item(emptyBooster)
        identifier("booster_standard").item(standardBooster)
        identifier("booster_fast").item(fastBooster)
        identifier("booster_slow").item(slowBooster)
        identifier("forward_launcher").item(forwardLauncher)

        identifier("fuel_cartridge_standard").item(standardFuelCartridge)
        identifier("fuel_cartridge_fast").item(fastFuelCartridge)
        identifier("fuel_cartridge_slow").item(slowFuelCartridge)
    }

    fun boosterItems() = listOf(fastBooster, standardBooster, slowBooster)
}