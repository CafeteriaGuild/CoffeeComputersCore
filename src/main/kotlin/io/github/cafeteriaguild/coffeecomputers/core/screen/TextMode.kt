package io.github.cafeteriaguild.coffeecomputers.core.screen

import io.github.cafeteriaguild.coffeecomputers.core.Computer
import io.github.cafeteriaguild.coffeecomputers.core.consts.fontFmt
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LInt
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.utils.returningUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class TextMode(private val computer: Computer) : LinNativeObj() {
    private val _posX = declareMutableProperty("posX")
    private val _posY = declareMutableProperty("posY")
    private val _offX = declareMutableProperty("offX")
    private val _offY = declareMutableProperty("offY")
    private val _fg = declareMutableProperty("fg")
    private val _bg = declareMutableProperty("bg")

    private val lock = ReentrantLock()

    private val textHeight = computer.screen.size.height / 9
    private val textWidth = computer.screen.size.width / 6

    private val offsetHeight = computer.screen.size.height % 9 / 2
    private val offsetWidth = computer.screen.size.width % 6 / 2

    data class CharData(val char: Char, val fg: Int?, val bg: Int?)

    private fun newLineBuffer() = Array(textWidth) { CharData(' ', 0, 15) }

    private val textBuffer = Array(textHeight) { newLineBuffer() }

    init {
        _posX.set(LInt(0))
        _posY.set(LInt(0))
        _offX.set(LInt(offsetWidth))
        _offY.set(LInt(offsetHeight))
        _fg.set(LInt(0))
        _bg.set(LInt(15))
        declareFunction("print") { returningUnit { print(it.first().toString()) } }
        declareFunction("println") { returningUnit { print(it.first().toString() + '\n') } }
    }

    fun print(string: String) {
        lock.withLock {
            //region var posX = ..., posY = ..., offX = ..., offY = ..., fg = ..., bg = ...
            var posX = (_posX.get() as LNumber).value.toInt()
            var posY = (_posY.get() as LNumber).value.toInt()
            val offX = (_offX.get() as LNumber).value.toInt()
            val offY = (_offY.get() as LNumber).value.toInt()
            val fg = (_fg.get() as? LNumber)?.value?.toInt()
            val bg = (_bg.get() as? LNumber)?.value?.toInt()
            //endregion

            for (char in string) {
                if (char == '\n') {
                    posX = 0
                    if (posY + 1 < textHeight) {
                        posY += 1
                    } else {
                        textBuffer.copyInto(textBuffer, startIndex = 1)
                        textBuffer[textBuffer.lastIndex] = newLineBuffer()
                    }
                } else if (posX < textWidth) {
                    textBuffer[posY][posX++] = CharData(char, fg, bg)
                } else if (posY + 1 < textHeight) {
                    posY += 1
                    posX = 0
                    textBuffer[posY][posX++] = CharData(char, fg, bg)
                } else {
                    textBuffer.copyInto(textBuffer, startIndex = 1)
                    textBuffer[textBuffer.lastIndex] = newLineBuffer()
                    textBuffer[posY][posX++] = CharData(char, fg, bg)
                }
            }

            for ((y, line) in textBuffer.withIndex()) {
                for ((x, data) in line.withIndex()) {
                    for ((fy, chunk) in fontFmt[data.char.toInt()].chunked(8).withIndex()) {
                        for ((fx, raw) in chunk.withIndex()) {
                            val color = if (raw == '1') data.fg else data.bg
                            if (color != null) {
                                computer.screen.setPixel(x * 6 + fx + offX, y * 9 + fy + offY, color)
                            }
                        }
                    }
                }
            }

            //region posX |> _posX, posY |> _posY
            _posX.set(LInt(posX))
            _posY.set(LInt(posY))
            //endregion
        }
        computer.outputEvents.onSuggestRender(computer)
    }

//    private fun doChars(s: String) {
//        var posX = (_posX.get() as LNumber).value.toInt()
//        var posY = (_posY.get() as LNumber).value.toInt()
//        val offX = (_offX.get() as LNumber).value.toInt()
//        val offY = (_offY.get() as LNumber).value.toInt()
//        val fg = (_fg.get() as? LNumber)?.value?.toByte()
//        val bg = (_bg.get() as? LNumber)?.value?.toByte()
//        for (c in s) {
//            if (c == '\n') {
//                posX = 0
//                posY += 1
//                screen.commitChanges()
//                return
//            }
//            for ((fy, chunk) in fontFmt[c.toInt()].chunked(8).withIndex()) {
//                for ((fx, raw) in chunk.withIndex()) {
//                    val color = if (raw == '1') fg else bg
//                    if (color != null) {
//                        framebuffer.setPixel(posX * 6 + fx + offX, posY * 9 + fy + offY, color)
//                    }
//                }
//            }
//            posX++
//        }
//        p_posX.set(LInt(posX))
//        p_posY.set(LInt(posY))
//    }
}