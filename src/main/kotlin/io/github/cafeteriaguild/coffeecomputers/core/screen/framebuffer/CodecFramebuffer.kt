package io.github.cafeteriaguild.coffeecomputers.core.screen.framebuffer

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize
import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSizes
import it.unimi.dsi.fastutil.ints.Int2ByteAVLTreeMap
import it.unimi.dsi.fastutil.ints.Int2ByteMap
import java.io.ByteArrayOutputStream
import kotlin.math.min

@OptIn(ExperimentalUnsignedTypes::class)
open class CodecFramebuffer(size: ScreenSize) : BaseFramebuffer<ByteArray>(size) {
    /*
     * TODO:
     *  - Implement "skip/draw nth * 16" to compress up even more
     *  - Make "genOpsUntil" detect between NTH_LINES, 16NTH AND NTH
     *      - Calculate how many pixels each instruction would cover
     *      - Select instruction with most pixels
     */

    override fun render() = frameData { codecFrame(size, it) }

    override fun renderChanges() = lastChanges { codecFrame(size, it) }

    companion object {
        fun codecFrame(s: ScreenSize, from: Int2ByteMap): ByteArray {
            val map = Int2ByteAVLTreeMap(from)
            val out = ByteArrayOutputStream(256)
            map.defaultReturnValue(-1)

            var p = 0 // Where we are at the image
            var c: Byte = 15 // Set color
            fun x(v: Int = p) = v % s.width
            fun y(v: Int = p) = v / s.width
            fun genOpsUntil(pDest: Int, nthLines: UByte, nth: UByte) {
                // This function generates all the required NTH/NTH_LINES operations automatically
                while (p < pDest) {
                    if (y() != y(pDest)) {
                        val skip = min(
                            (y(pDest) - y()),
                            ARG_MAX
                        )
                        out.write(nthLines.toInt() and (skip - 1)) // println("OP02_SKIP_NTH_LINES $skip")
                        p = (y() + skip) * ScreenSizes.computer.height
                    } else {
                        val skip = min(
                            pDest - p,
                            ARG_MAX
                        )
                        out.write(nth.toInt() and (skip - 1)) // println("OP01_SKIP_NTH $skip")
                        p += skip
                    }
                }
            }

            fun skipUntil(pDest: Int) = genOpsUntil(
                pDest,
                OP02_SKIP_NTH_LINES,
                OP01_SKIP_NTH
            )

            fun drawUntil(pDest: Int) = genOpsUntil(
                pDest,
                OP02_SKIP_NTH_LINES,
                OP04_DRAW_NTH
            )

            while (map.isNotEmpty()) {
                val k = map.firstIntKey()
                skipUntil(k)
                val v = map.remove(k)
                var len = 1
                while (map.isNotEmpty()) {
                    val nextK = map.firstIntKey()
                    if (nextK != k + len || map.get(nextK) != v) break
                    len++
                    map.remove(k)
                }

                if (len == 1) {
                    out.write(OP06_DRAW_ONCE.toInt() and v.toInt()) // println("OP06_DRAW_ONCE v")
                    p++
                } else {
                    out.write(OP03_SET_COLOR.toInt() and v.toInt()) // println("OP03_SET_COLOR v")
                    drawUntil(p + len)
                }
            }

            return out.toByteArray()
        }

        const val ARG_MAX = 16

        const val OP01_SKIP_NTH: UByte = 0b00000000u
        const val OP02_SKIP_NTH_LINES: UByte = 0b10000000u
        const val OP03_SET_COLOR: UByte = 0b01000000u
        const val OP04_DRAW_NTH: UByte = 0b11000000u
        const val OP05_DRAW_NTH_LINES: UByte = 0b00100000u
        const val OP06_DRAW_ONCE: UByte = 0b10100000u
        const val OP07_UNUSED: UByte = 0b01100000u
        const val OP08_UNUSED: UByte = 0b11100000u
        const val OP09_UNUSED: UByte = 0b00010000u
        const val OP10_UNUSED: UByte = 0b10010000u
        const val OP11_UNUSED: UByte = 0b01010000u
        const val OP12_UNUSED: UByte = 0b11010000u
        const val OP13_UNUSED: UByte = 0b00110000u
        const val OP14_UNUSED: UByte = 0b10110000u
        const val OP15_UNUSED: UByte = 0b01110000u
        const val OP16_UNUSED: UByte = 0b11110000u
    }
}