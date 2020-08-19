package io.github.cafeteriaguild.coffeecomputers.core.bridge

import io.github.cafeteriaguild.lin.rt.lib.LObj
import io.github.cafeteriaguild.lin.rt.lib.lang.LString
import io.github.cafeteriaguild.lin.rt.lib.nativelang.LinNativeObj
import java.util.*

data class EventLObj(val type: LString, val data: LObj) : LinNativeObj() {
    init {
        setImmutableProperty("type", type)
        setImmutableProperty("data", data)
        declareEquals<EventLObj> { type == it.type && data == it.data }
        declareHashCode { Objects.hash(type, data) }
        declareToString { "Event{type: $type, data: $data}" }
        declareFunction("component1") { type }
        declareFunction("component2") { data }
    }
}