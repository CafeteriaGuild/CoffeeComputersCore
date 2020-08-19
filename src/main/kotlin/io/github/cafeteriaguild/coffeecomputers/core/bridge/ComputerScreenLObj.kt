package io.github.cafeteriaguild.coffeecomputers.core.bridge

import io.github.cafeteriaguild.coffeecomputers.core.Computer
import io.github.cafeteriaguild.coffeecomputers.core.screen.TextMode
import io.github.cafeteriaguild.lin.rt.exceptions.LinThrownException
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.utils.returningUnit

class ComputerScreenLObj(private val computer: Computer) : LinNativeObj() {
    init {
        lazyImmutableProperty("width") { LNumber.box(computer.screen.size.width) }
        lazyImmutableProperty("height") { LNumber.box(computer.screen.size.height) }
        declareFunction("setPixel") {
            val (_x, _y, _b) = it
            returningUnit {
                val x = (_x as LNumber).value.toInt()
                val y = (_y as LNumber).value.toInt()
                val b = (_b as LNumber).value.toByte()
                if (b !in 0..15) {
                    throw LinThrownException("illegal_argument", "Invalid color value")
                }
                computer.screen.setPixel(x, y, b)
            }
        }
        declareFunction("flush") {
            returningUnit { computer.outputEvents.onSuggestRender(computer) }
        }
        declareFunction("textMode") { TextMode(computer) }
        declareToString { "bios.screen" }
    }
}