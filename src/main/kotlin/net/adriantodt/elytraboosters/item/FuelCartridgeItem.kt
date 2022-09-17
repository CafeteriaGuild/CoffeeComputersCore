package net.adriantodt.elytraboosters.item

import net.adriantodt.elytraboosters.ElytraBoostersItems.emptyBooster
import net.adriantodt.elytraboosters.ElytraBoostersItems.fastFuelCartridge
import net.adriantodt.elytraboosters.ElytraBoostersItems.slowFuelCartridge
import net.adriantodt.elytraboosters.ElytraBoostersItems.standardFuelCartridge
import net.adriantodt.elytraboosters.ExpandedPlayerInventory
import net.adriantodt.elytraboosters.data.ElytraBoostersData
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class FuelCartridgeItem(settings: Settings, private val data: ElytraBoostersData.FuelCartridgeData) : Item(settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (user.isSneaking && user.inventory is ExpandedPlayerInventory) {
            val inv = user.inventory as ExpandedPlayerInventory
            if (
                inv.count(standardFuelCartridge) >= data.standardCartridges &&
                inv.count(fastFuelCartridge) >= data.fastCartridges &&
                inv.count(slowFuelCartridge) >= data.slowCartridges &&
                inv.takeOneAndReplace(emptyBooster, data.resultItem)
            ) {
                inv.takeFromInventory(standardFuelCartridge, data.standardCartridges)
                inv.takeFromInventory(fastFuelCartridge, data.fastCartridges)
                inv.takeFromInventory(slowFuelCartridge, data.slowCartridges)
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand))
    }

    @Environment(EnvType.CLIENT)
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text?>, ctx: TooltipContext?) {
        tooltip += TranslatableText("$translationKey.description")
        tooltip += TranslatableText("tooltip.elytraboosters.craft_booster1")
        tooltip += TranslatableText(
            "tooltip.elytraboosters.craft_booster2",
            TranslatableText(data.resultItem.translationKey)
        )
    }
}