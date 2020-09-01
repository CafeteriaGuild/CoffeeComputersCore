package io.github.cafeteriaguild.coffeecomputers.core

import com.github.adriantodt.tartar.api.lexer.Source
import io.github.cafeteriaguild.coffeecomputers.core.bridge.ComputerScreenLObj
import io.github.cafeteriaguild.coffeecomputers.core.bridge.EventQueueLObj
import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.lin.lexer.linStdLexer
import io.github.cafeteriaguild.lin.parser.linStdParser
import io.github.cafeteriaguild.lin.rt.LinInterpreter
import io.github.cafeteriaguild.lin.rt.exceptions.LinIllegalArgumentException
import io.github.cafeteriaguild.lin.rt.lib.LObj
import io.github.cafeteriaguild.lin.rt.lib.lang.LBoolean
import io.github.cafeteriaguild.lin.rt.lib.lang.LChar
import io.github.cafeteriaguild.lin.rt.lib.lang.LString
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LInt
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LLong
import io.github.cafeteriaguild.lin.rt.lib.lang.number.LNumber
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.scope.UserScope
import io.github.cafeteriaguild.lin.rt.utils.returningUnit
import pw.aru.libs.eventpipes.EventPipes
import java.io.Reader
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlin.math.roundToLong

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

    val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(0)
    val timers = ConcurrentHashMap<LObj, Future<*>>()

    init {
        declareFunction("eventQueue") {
            if (it.isNotEmpty()) {
                throw LinIllegalArgumentException("'eventQueue' takes no arguments.")
            }
            EventQueueLObj(this, false)
        }
        declareFunction("eventQueueRaw") {
            if (it.isNotEmpty()) {
                throw LinIllegalArgumentException("'eventQueueRaw' takes no arguments.")
            }
            EventQueueLObj(this, true)
        }
        val timerCount = AtomicLong()
        declareFunction("startTimer") {
            if (it.size == 1) throw LinIllegalArgumentException("'startTimer' takes 1 argument.")
            val timeout = it.single() as? LNumber
                ?: throw LinIllegalArgumentException("'startTimer' takes a Number as argument.")
            val millis = (timeout.value.toDouble() * 1000).roundToLong()
            val count = LLong(timerCount.getAndIncrement())
            timers[count] = executor.schedule({
                internalEvents.publish("timer" to count)
                timers.remove(count)
            }, millis, TimeUnit.MILLISECONDS)
            count
        }
        declareFunction("cancelTimer") {
            if (it.size == 1) throw LinIllegalArgumentException("'cancelTimer' takes 1 argument.")
            val id = it.single() as? LNumber
                ?: throw LinIllegalArgumentException("'cancelTimer' takes a Number as argument.")
            LBoolean.of(timers.remove(id)?.cancel(true) == true)
        }
        declareFunction("freeByName") {
            returningUnit { scope.properties.remove((it.single() as LString).value) }
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

