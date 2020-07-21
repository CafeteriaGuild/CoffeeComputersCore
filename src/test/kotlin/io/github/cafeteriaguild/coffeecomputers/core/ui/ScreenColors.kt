package io.github.cafeteriaguild.coffeecomputers.core.ui

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenColors
import java.awt.Color

@OptIn(ExperimentalUnsignedTypes::class)
object AWTScreenColors {
    val white = Color(ScreenColors.white.toInt())
    val orange = Color(ScreenColors.orange.toInt())
    val magenta = Color(ScreenColors.magenta.toInt())
    val lightBlue = Color(ScreenColors.lightBlue.toInt())
    val yellow = Color(ScreenColors.yellow.toInt())
    val lime = Color(ScreenColors.lime.toInt())
    val pink = Color(ScreenColors.pink.toInt())
    val gray = Color(ScreenColors.gray.toInt())
    val lightGray = Color(ScreenColors.lightGray.toInt())
    val cyan = Color(ScreenColors.cyan.toInt())
    val purple = Color(ScreenColors.purple.toInt())
    val blue = Color(ScreenColors.blue.toInt())
    val green = Color(ScreenColors.green.toInt())
    val brown = Color(ScreenColors.brown.toInt())
    val red = Color(ScreenColors.red.toInt())
    val black = Color(ScreenColors.black.toInt())

    val colors = arrayOf(
        white,
        orange,
        magenta,
        lightBlue,
        yellow,
        lime,
        pink,
        gray,
        lightGray,
        cyan,
        purple,
        blue,
        green,
        brown,
        red,
        black
    )
}
