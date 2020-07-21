package io.github.cafeteriaguild.coffeecomputers.core

import com.github.adriantodt.tartar.api.lexer.Source
import io.github.cafeteriaguild.coffeecomputers.core.consts.fontFmt
import io.github.cafeteriaguild.coffeecomputers.core.screen.framebuffer.BaseFramebuffer
import io.github.cafeteriaguild.lin.lexer.linStdLexer
import io.github.cafeteriaguild.lin.parser.linStdParser
import io.github.cafeteriaguild.lin.rt.LinInterpreter
import io.github.cafeteriaguild.lin.rt.lib.LInt
import io.github.cafeteriaguild.lin.rt.lib.LNull
import io.github.cafeteriaguild.lin.rt.lib.LNumber
import io.github.cafeteriaguild.lin.rt.lib.LUnit
import io.github.cafeteriaguild.lin.rt.lib.dsl.createLFun
import io.github.cafeteriaguild.lin.rt.lib.dsl.createLObj
import io.github.cafeteriaguild.lin.rt.scope.UserScope
import java.io.Reader
import kotlin.concurrent.thread

class ComputerCore(private val framebuffer: BaseFramebuffer<*>) {
    val scope = UserScope()

    init {
        scope["Native"] = createLObj {
            getOrCreateProperty("sleep").set(createLFun {
                Thread.sleep((it.first() as LNumber).value.toLong())
                LUnit
            })
            getOrCreateProperty("_removeFromScope").set(createLFun {
                scope.properties.remove("Native")
                LUnit
            })
        }
        scope["Hardware"] = createLObj {
            getOrCreateProperty("_removeFromScope").set(createLFun {
                scope.properties.remove("Native")
                LUnit
            })
            getOrCreateProperty("Screen").set(createLObj {
                getOrCreateProperty("width").set(LInt(framebuffer.size.width))
                getOrCreateProperty("height").set(LInt(framebuffer.size.height))
            })
            getOrCreateProperty("setPixel").set(createLFun {
                val (x, y, b) = it
                framebuffer.setPixel(
                    (x as LNumber).value.toInt(),
                    (y as LNumber).value.toInt(),
                    (b as LNumber).value.toByte()
                )
                LUnit
            })
            getOrCreateProperty("flushFrame").set(createLFun {
                framebuffer.commitChanges()
                LUnit
            })
            getOrCreateProperty("textMode").set(createLFun {
                createLObj {
                    val p_posX = getOrCreateMutableProperty("posX")
                    val p_posY = getOrCreateMutableProperty("posY")
                    val p_offX = getOrCreateMutableProperty("offX")
                    val p_offY = getOrCreateMutableProperty("offY")
                    val p_fg = getOrCreateMutableProperty("fg")
                    val p_bg = getOrCreateMutableProperty("bg")
                    p_posX.set(LInt(0))
                    p_posY.set(LInt(0))
                    p_offX.set(LInt(0))
                    p_offY.set(LInt(0))
                    p_fg.set(LInt(0))
                    p_bg.set(LNull)
                    fun doChars(s: String) {
                        var posX = (p_posX.get() as LNumber).value.toInt()
                        var posY = (p_posY.get() as LNumber).value.toInt()
                        val offX = (p_offX.get() as LNumber).value.toInt()
                        val offY = (p_offY.get() as LNumber).value.toInt()
                        val fg = (p_fg.get() as? LNumber)?.value?.toByte()
                        val bg = (p_bg.get() as? LNumber)?.value?.toByte()
                        for (c in s) {
                            if (c == '\n') {
                                posX = 0
                                posY++
                                framebuffer.commitChanges()
                                return
                            }
                            for ((fy, chunk) in fontFmt[c.toInt()].chunked(8).withIndex()) {
                                for ((fx, raw) in chunk.withIndex()) {
                                    val color = if (raw == '1') fg else bg
                                    if (color != null) {
                                        framebuffer.setPixel(posX * 6 + fx + offX, posY * 9 + fy + offY, color)
                                    }
                                }
                            }
                            posX++
                        }
                        p_posX.set(LInt(posX))
                        p_posY.set(LInt(posY))
                    }
                    getOrCreateMutableProperty("println").set(createLFun {
                        doChars(it.first().toString() + '\n')
                        LUnit
                    })
                    getOrCreateMutableProperty("print").set(createLFun {
                        doChars(it.first().toString())
                        LUnit
                    })
                }
            })
        }
    }

    fun start(reader: Reader) {
        thread {
            try {
                val expr = linStdParser.parse(Source(reader, "bios.lin", ""), linStdLexer)
                LinInterpreter().execute(expr, scope)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}