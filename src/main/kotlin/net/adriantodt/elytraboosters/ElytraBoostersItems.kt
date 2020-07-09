package net.adriantodt.elytraboosters

import net.adriantodt.elytraboosters.ElytraBoosters.data
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType.*
import net.adriantodt.elytraboosters.item.*
import net.minecraft.item.Item

@Suppress("MemberVisibilityCanBePrivate")
object ElytraBoostersItems {
    val emptyBooster = LoreItem(itemSettings())

    val fastBooster = BoosterItem(itemSettings().maxDamage(100), data.booster(FAST))
    val fastBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(100), data.booster(FAST))

    val standardBooster = BoosterItem(itemSettings().maxDamage(200), data.booster(STANDARD))
    val standardBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(200), data.booster(STANDARD))

    val slowBooster = BoosterItem(itemSettings().maxDamage(400), data.booster(SLOW))
    val slowBoosterActive = ActiveBoosterItem(Item.Settings().maxDamage(400), data.booster(SLOW))

    val forwardLauncher = ForwardLauncherItem(itemSettings().maxDamage(128), data.launcherVelocity)

    val standardFuelPellet = FuelPelletItem(itemSettings(), data.fuelPellet(STANDARD))
    val slowFuelPellet = FuelPelletItem(itemSettings(), data.fuelPellet(SLOW))
    val fastFuelPellet = FuelPelletItem(itemSettings(), data.fuelPellet(FAST))

    fun register() {
        identifier("booster_empty").item(emptyBooster)
        identifier("booster_standard").item(standardBooster)
        identifier("booster_standard_active").item(standardBoosterActive)
        identifier("booster_fast").item(fastBooster)
        identifier("booster_fast_active").item(fastBoosterActive)
        identifier("booster_slow").item(slowBooster)
        identifier("booster_slow_active").item(slowBoosterActive)
        identifier("forward_launcher").item(forwardLauncher)

        identifier("fuel_pellet_standard").item(standardFuelPellet)
        identifier("fuel_pellet_fast").item(fastFuelPellet)
        identifier("fuel_pellet_slow").item(slowFuelPellet)
    }
}