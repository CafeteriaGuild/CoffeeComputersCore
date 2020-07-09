package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.data.ElytraBoostersConfig
import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.adriantodt.elytraboosters.item.ActiveBoosterItem
import net.adriantodt.elytraboosters.item.BoosterItem

class BoosterDataImpl(
    override var constantVelocity: Double,
    override var interpolatingVelocity: Double,
    override var frictionFactor: Double,
    override var ticksPerDamage: Int,
    lazyBooster: () -> BoosterItem,
    lazyActiveBooster: () -> ActiveBoosterItem
) : ElytraBoostersData.BoosterData {
    override val boosterItem by lazy(lazyBooster)
    override val activeBoosterItem by lazy(lazyActiveBooster)

    constructor(
        c: ElytraBoostersConfig.BoosterValues,
        lazyBooster: () -> BoosterItem,
        lazyActiveBooster: () -> ActiveBoosterItem
    ) : this(
        c.constantVelocity, c.interpolatingVelocity, c.frictionFactor, c.ticksPerDamage, lazyBooster, lazyActiveBooster
    )
}