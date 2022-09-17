package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.adriantodt.elytraboosters.item.BoosterItem

class FuelCartridgeDataImpl(
    override val standardCartridges: Int,
    override val fastCartridges: Int,
    override val slowCartridges: Int,
    lazyResult: () -> BoosterItem
) : ElytraBoostersData.FuelCartridgeData {
    override val resultItem by lazy(lazyResult)
}