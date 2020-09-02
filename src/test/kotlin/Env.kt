import asmble.io.SExprToAst
import asmble.run.jvm.interpret.Interpreter
import asmble.run.jvm.interpret.RunModule
import asmble.util.Logger
import io.github.cafeteriaguild.coffeecomputers.core.ui.ComputerPanel
import io.github.cafeteriaguild.coffeecomputers.core.wasm.bridge.MemHelper
import io.github.cafeteriaguild.coffeecomputers.core.wasm.helper.scriptContext

class Env {
    val mem = MemHelper()

    fun puts(ptr: Int) {
        println(mem.cstringAt(ptr))
    }

    fun i32sum(ptr: Int, len: Int): Int {
        return mem.i32ArrayAt(ptr, len).sum()
    }

    fun i64sum(ptr: Int, len: Int): Long {
        return mem.i64ArrayAt(ptr, len).sum()
    }

    fun f32sum(ptr: Int, len: Int): Float {
        return mem.f32ArrayAt(ptr, len).sum()
    }

    fun f64sum(ptr: Int, len: Int): Double {
        return mem.f64ArrayAt(ptr, len).sum()
    }

    fun print_i32(i: Int) {
        println("$i : i32")
    }

    fun print_i64(l: Long) {
        println("$l : i64")
    }

    fun print_f32(f: Float) {
        println("%#.0f : f32".format(f))
    }

    fun print_f64(d: Double) {
        println("%#.0f : f64".format(d))
    }
}

fun main() {
    val (_, mod) = SExprToAst.toModuleFromQuotedString(
        ComputerPanel::class.java.getResourceAsStream("/bios.wat").reader().readText()
    )

    val env = Env()
    val scrCtx = scriptContext {
        nativeModule("env", env)
    }
    val ctx = Interpreter.Context(
        mod,
        logger = Logger.Print(Logger.Level.TRACE),
        imports = RunModule.ResolverImports(scrCtx)
    )
    env.mem.ctx = ctx
    println("RESULT | " + Interpreter.execFunc(ctx, mod.exports.first { it.field == "main" }.index))
}