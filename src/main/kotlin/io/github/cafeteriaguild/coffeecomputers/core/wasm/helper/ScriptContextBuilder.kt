package io.github.cafeteriaguild.coffeecomputers.core.wasm.helper

import asmble.run.jvm.Module
import asmble.run.jvm.ScriptContext

class ScriptContextBuilder {
    private val modules: MutableList<Module> = ArrayList()
    private val registrations: MutableMap<String, Module> = LinkedHashMap()

    fun register(module: Module) {
        modules += module
        module.name?.let { registrations[it] = module }
    }

    fun nativeModule(cls: Class<*>, name: String?, inst: Any) {
        register(Module.Native(cls, name, inst))
    }

    fun nativeModule(name: String?, inst: Any) {
        register(Module.Native(name, inst))
    }

    fun build(): ScriptContext {
        return ScriptContext(modules.toList(), registrations.toMap())
    }
}

fun scriptContext(block: ScriptContextBuilder.() -> Unit) = ScriptContextBuilder().also(block).build()