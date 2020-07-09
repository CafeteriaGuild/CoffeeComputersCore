package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.ElytraBoostersItems.fastBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.fastBoosterActive
import net.adriantodt.elytraboosters.ElytraBoostersItems.slowBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.slowBoosterActive
import net.adriantodt.elytraboosters.ElytraBoostersItems.standardBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.standardBoosterActive
import net.adriantodt.elytraboosters.data.ElytraBoostersConfig
import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType.*

class ElytraBoostersDataImpl(config: ElytraBoostersConfig) : ElytraBoostersData {
    override val launcherVelocity = BoosterVelocityImpl(config.forwardLauncher)

    private val fuelPelletMap = mapOf(
        STANDARD to FuelPelletDataImpl(3, 0, 0, fun() = standardBooster),
        FAST to FuelPelletDataImpl(2, 1, 0, fun() = fastBooster),
        SLOW to FuelPelletDataImpl(2, 0, 1, fun() = slowBooster)
    )

    private val boosterMap = mapOf(
        STANDARD to BoosterDataImpl(STANDARD.booster(config), fun() = standardBooster, fun() = standardBoosterActive),
        SLOW to BoosterDataImpl(SLOW.booster(config), fun() = slowBooster, fun() = slowBoosterActive),
        FAST to BoosterDataImpl(FAST.booster(config), fun() = fastBooster, fun() = fastBoosterActive)
    )

    override fun booster(type: BoosterType) = boosterMap[type] ?: error("Impossible.")
    override fun fuelPellet(type: BoosterType) = fuelPelletMap[type] ?: error("Impossible.")

    private fun BoosterType.booster(config: ElytraBoostersConfig) = when (this) {
        STANDARD -> config.standardBooster
        FAST -> config.fastBooster
        SLOW -> config.slowBooster
    }
}

