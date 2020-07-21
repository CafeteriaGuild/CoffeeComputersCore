package io.github.cafeteriaguild.coffeecomputers.core.ui

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize
import io.github.cafeteriaguild.coffeecomputers.core.screen.framebuffer.BaseFramebuffer
import it.unimi.dsi.fastutil.ints.Int2ByteAVLTreeMap
import it.unimi.dsi.fastutil.ints.Int2ByteMap
import java.awt.image.BufferedImage

class AWTFramebuffer(size: ScreenSize) : BaseFramebuffer<BufferedImage>(size) {
    override fun render() = frameData {
        codecFrame(
            size,
            it
        )
    }

    override fun renderChanges() = lastChanges {
        codecFrame(
            size,
            it
        )
    }

    companion object {
        fun codecFrame(s: ScreenSize, from: Int2ByteMap): BufferedImage {
            fun x(v: Int) = v % s.width
            fun y(v: Int) = v / s.width
            val map = Int2ByteAVLTreeMap(from)
            map.defaultReturnValue(-1)
            val img = BufferedImage(s.width, s.height, BufferedImage.TYPE_INT_ARGB)

            with(img.createGraphics()) {
                color = AWTScreenColors.black
                fillRect(0, 0, s.width, s.height)
            }

            while (map.isNotEmpty()) {
                val k = map.firstIntKey()
                val v = map.remove(k)
                img.setRGB(x(k), y(k), AWTScreenColors.colors[v.toInt()].rgb)
            }

            return img
        }
    }
}