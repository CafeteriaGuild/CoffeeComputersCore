package io.github.cafeteriaguild.coffeecomputers.core

import com.github.adriantodt.tartar.api.lexer.Source
import io.github.cafeteriaguild.coffeecomputers.core.bridge.ComputerScreenLObj
import io.github.cafeteriaguild.coffeecomputers.core.bridge.EventQueueLObj
import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.lin.lexer.linStdLexer
import io.github.cafeteriaguild.lin.parser.linStdParser
import io.github.cafeteriaguild.lin.rt.LinInterpreter
import io.github.cafeteriaguild.lin.rt.lib.LObj
import io.github.cafeteriaguild.lin.rt.lib.lang.LChar
import io.github.cafeteriaguild.lin.rt.lib.lang.LString
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LInt
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.scope.UserScope
import io.github.cafeteriaguild.lin.rt.utils.returningUnit
import pw.aru.libs.eventpipes.EventPipes
import java.io.Reader
import kotlin.concurrent.thread

class Computer(val screen: ComputerScreen, val outputEvents: ComputerEvents = ComputerEvents.Ignore) : LinNativeObj() {
    val scope = UserScope()
    val internalEvents = EventPipes.newPipe<Pair<String, LObj>>()

    fun sendCharEvent(char: Char) {
        internalEvents.publish("char" to LChar(char))
    }

    fun sendKeyEvent(i: Int) {
        internalEvents.publish("key" to LInt(i))
    }

    fun sendKeyUpEvent(i: Int) {
        internalEvents.publish("key_up" to LInt(i))
    }

    init {
        declareFunction("eventQueue") { EventQueueLObj(this) }
        declareFunction("sleep") {
            returningUnit { Thread.sleep((it.first() as LNumber).value.toLong()) }
        }
        declareFunction("freeByName") {
            returningUnit { scope.properties.remove((it.first() as LString).value) }
        }
        lazyImmutableProperty("screen") { ComputerScreenLObj(this) }

        declareToString { "hw" }
        scope["hw"] = this
    }

    fun start(reader: Reader) {
        thread {
            try {
                val expr = linStdParser.parse(Source(reader, "bios.lin", ""), linStdLexer)
                LinInterpreter().execute(expr, scope)
                outputEvents.onFinished(this, null)
            } catch (e: Exception) {
                outputEvents.onFinished(this, e)
            }
        }
    }
}

