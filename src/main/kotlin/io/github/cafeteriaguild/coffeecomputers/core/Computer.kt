package io.github.cafeteriaguild.coffeecomputers.core

import asmble.io.SExprToAst
import asmble.run.jvm.interpret.Interpreter
import asmble.run.jvm.interpret.RunModule
import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.coffeecomputers.core.wasm.ScreenWasm
import io.github.cafeteriaguild.coffeecomputers.core.wasm.helper.scriptContext
import java.io.Reader
import kotlin.concurrent.thread

class Computer(val screen: ComputerScreen, val outputEvents: ComputerEvents = ComputerEvents.Ignore) {
    fun start(reader: Reader) {
        thread {
            try {
                val (_, module) = SExprToAst.toModuleFromQuotedString(reader.readText())

                val scrCtx = scriptContext {
                    nativeModule("screen", ScreenWasm(screen))
                    nativeModule("env", object {
                        fun puts() {

                        }
                    })
                }
                val ctx = Interpreter.Context(
                    module,
                    imports = RunModule.ResolverImports(scrCtx)
                )
                println("RESULT | " + Interpreter.execFunc(ctx))
                outputEvents.onFinished(this, null)
            } catch (e: Exception) {
                outputEvents.onFinished(this, e)
            }
        }
    }
}

