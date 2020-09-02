package io.github.cafeteriaguild.coffeecomputers.core.wasm

import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen

class ScreenWasm(val screen: ComputerScreen) {
    fun vget(pos: Int) {
        screen.getPixel(pos)
    }

    fun vset(pos: Int, value: Int) {
        screen.setPixel(pos, value)
    }
}