package io.github.cafeteriaguild.coffeecomputers.core.ui

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize
import java.awt.image.BufferedImage

object AWTRenderer {
    fun renderToImage(s: ScreenSize, frame: ByteArray): BufferedImage {
        val img = BufferedImage(s.width, s.height, BufferedImage.TYPE_INT_ARGB)
        for ((p, v) in frame.withIndex()) {
            img.setRGB(p % s.width, p / s.width, AWTScreenColors.colors[v.toInt()].rgb)
        }
        return img
    }
}