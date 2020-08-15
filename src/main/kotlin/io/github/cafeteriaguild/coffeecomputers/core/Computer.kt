package io.github.cafeteriaguild.coffeecomputers.core

import com.github.adriantodt.tartar.api.lexer.Source
import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.coffeecomputers.core.screen.TextMode
import io.github.cafeteriaguild.lin.lexer.linStdLexer
import io.github.cafeteriaguild.lin.parser.linStdParser
import io.github.cafeteriaguild.lin.rt.LinInterpreter
import io.github.cafeteriaguild.lin.rt.lib.lang.LString
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber.Companion.box
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.scope.UserScope
import io.github.cafeteriaguild.lin.rt.utils.returningUnit
import java.io.Reader
import kotlin.concurrent.thread

class Computer(val screen: ComputerScreen, val events: ComputerEvents = ComputerEvents.Ignore) : LinNativeObj() {
    val scope = UserScope()

    init {
        declareFunction("sleep") {
            returningUnit { Thread.sleep((it.first() as LNumber).value.toLong()) }
        }
        declareFunction("freeByName") {
            returningUnit { scope.properties.remove((it.first() as LString).value) }
        }
        lazyImmutableProperty("screen") {
            object : LinNativeObj() {
                init {
                    lazyImmutableProperty("width") { box(screen.size.width) }
                    lazyImmutableProperty("height") { box(screen.size.height) }
                    declareFunction("setPixel") {
                        val (x, y, b) = it
                        returningUnit {
                            screen.setPixel(
                                (x as LNumber).value.toInt(),
                                (y as LNumber).value.toInt(),
                                (b as LNumber).value.toByte()
                            )
                        }
                    }
                    declareFunction("flush") {
                        returningUnit { events.onSuggestRender(this@Computer) }
                    }
                    declareFunction("textMode") { TextMode(this@Computer) }
                    declareToString { "bios.screen" }
                }
            }
        }

        declareToString { "hw" }
        scope["hw"] = this
    }

    init {
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