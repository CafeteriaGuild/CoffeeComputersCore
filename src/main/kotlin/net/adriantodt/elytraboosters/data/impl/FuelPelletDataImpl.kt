package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.adriantodt.elytraboosters.item.BoosterItem

class FuelPelletDataImpl(
    override val standardPellets: Int,
    override val fastPellets: Int,
    override val slowPellets: Int,
    lazyResult: () -> BoosterItem
) : ElytraBoostersData.FuelPelletData {
    override val resultItem by lazy(lazyResult)
}