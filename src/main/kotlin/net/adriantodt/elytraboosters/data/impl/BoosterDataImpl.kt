package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.data.ElytraBoostersConfig
import net.adriantodt.elytraboosters.data.ElytraBoostersData

class BoosterDataImpl(
    override var constantVelocity: Double,
    override var interpolatingVelocity: Double,
    override var frictionFactor: Double,
    override var ticksPerDamage: Int,
) : ElytraBoostersData.BoosterData {
    constructor(c: ElytraBoostersConfig.BoosterValues) : this(
        c.constantVelocity, c.interpolatingVelocity, c.frictionFactor, c.ticksPerDamage
    )
}