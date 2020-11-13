package net.adriantodt.elytraboosters.item

import net.adriantodt.elytraboosters.active
import net.minecraft.client.item.ModelPredicateProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

object BoosterModelPredicateProvider : ModelPredicateProvider {
    override fun call(stack: ItemStack, world: ClientWorld?, entity: LivingEntity?): Float {
        return if (stack.tag?.active == true) 1f else 0f
    }
}
