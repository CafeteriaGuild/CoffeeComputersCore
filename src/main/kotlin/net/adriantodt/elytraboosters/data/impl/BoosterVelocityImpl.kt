package net.adriantodt.elytraboosters.data.impl

import net.adriantodt.elytraboosters.data.ElytraBoostersConfig
import net.adriantodt.elytraboosters.data.ElytraBoostersData

class BoosterVelocityImpl(
    override var constantVelocity: Double,
    override var interpolatingVelocity: Double,
    override var frictionFactor: Double
) : ElytraBoostersData.BoosterVelocity {
    constructor(c: ElytraBoostersConfig.ForwardLauncherValues) : this(
        c.constantVelocity, c.interpolatingVelocity, c.frictionFactor
    )
}