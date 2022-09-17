package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.ElytraBoostersItems.fastBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.slowBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.standardBooster
import net.adriantodt.elytraboosters.data.ElytraBoostersConfig
import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType
import net.adriantodt.elytraboosters.data.ElytraBoostersData.BoosterType.*

class ElytraBoostersDataImpl(config: ElytraBoostersConfig) : ElytraBoostersData {
    override val launcherVelocity = BoosterVelocityImpl(config.forwardLauncher)

    private val fuelPelletMap = mapOf(
        STANDARD to FuelCartridgeDataImpl(3, 0, 0, fun() = standardBooster),
        FAST to FuelCartridgeDataImpl(2, 1, 0, fun() = fastBooster),
        SLOW to FuelCartridgeDataImpl(2, 0, 1, fun() = slowBooster)
    )

    private val boosterMap = mapOf(
        STANDARD to BoosterDataImpl(STANDARD.booster(config)),
        SLOW to BoosterDataImpl(SLOW.booster(config)),
        FAST to BoosterDataImpl(FAST.booster(config))
    )

    override fun booster(type: BoosterType) = boosterMap[type] ?: error("Impossible.")
    override fun fuelCartridge(type: BoosterType) = fuelPelletMap[type] ?: error("Impossible.")

    private fun BoosterType.booster(config: ElytraBoostersConfig) = when (this) {
        STANDARD -> config.standardBooster
        FAST -> config.fastBooster
        SLOW -> config.slowBooster
    }
}

