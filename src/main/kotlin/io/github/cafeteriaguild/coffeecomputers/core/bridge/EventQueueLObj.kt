package io.github.cafeteriaguild.coffeecomputers.core.bridge

import io.github.cafeteriaguild.coffeecomputers.core.Computer
import io.github.cafeteriaguild.lin.rt.lib.LObj
import io.github.cafeteriaguild.lin.rt.lib.lang.LString
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import io.github.cafeteriaguild.lin.rt.lib.nativelang.routes.LinNativeIterator
import io.github.cafeteriaguild.lin.rt.utils.returningUnit
import java.util.concurrent.LinkedBlockingQueue

class EventQueueLObj(private val computer: Computer) : LinNativeObj(), LinNativeIterator {
    val queue = LinkedBlockingQueue<Pair<String, LObj>>()
    val subscription = computer.internalEvents.subscribe { queue.offer(it) }

    init {
        declareIteratorFromNative()
        declareFunction("close") {
            returningUnit { subscription.close() }
        }
    }

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): LObj {
        val (type, data) = queue.take()
        return EventLObj(LString(type), data)
    }
}