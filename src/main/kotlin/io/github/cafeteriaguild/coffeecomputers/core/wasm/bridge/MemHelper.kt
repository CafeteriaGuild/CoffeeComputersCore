package io.github.cafeteriaguild.coffeecomputers.core.wasm.bridge

import asmble.run.jvm.interpret.Interpreter
import java.nio.*

class MemHelper {
    lateinit var ctx: Interpreter.Context

    fun ptrAccess(ptr: Int): ByteBuffer? {
        val mem = ctx.maybeMem ?: return null
        return mem.asReadOnlyBuffer().position(ptr)
    }

    fun i32PtrAccess(ptr: Int): IntBuffer? {
        return ptrAccess(ptr)?.asIntBuffer()
    }

    fun i64PtrAccess(ptr: Int): LongBuffer? {
        return ptrAccess(ptr)?.asLongBuffer()
    }

    fun f32PtrAccess(ptr: Int): FloatBuffer? {
        return ptrAccess(ptr)?.asFloatBuffer()
    }

    fun f64PtrAccess(ptr: Int): DoubleBuffer? {
        return ptrAccess(ptr)?.asDoubleBuffer()
    }

    fun cstringAt(ptr: Int): String? {
        val mem = ptrAccess(ptr) ?: return null
        val s = StringBuilder()
        while (true) {
            val b = mem.get()
            if (b == 0.toByte()) break
            s.append(b.toChar())
        }
        return s.toString()
    }

    fun i32ArrayAt(ptr: Int, len: Int): IntArray {
        val arr = IntArray(len)
        i32PtrAccess(ptr)?.get(arr)
        return arr
    }

    fun i64ArrayAt(ptr: Int, len: Int): LongArray {
        val arr = LongArray(len)
        i64PtrAccess(ptr)?.get(arr)
        return arr
    }

    fun f32ArrayAt(ptr: Int, len: Int): FloatArray {
        val arr = FloatArray(len)
        f32PtrAccess(ptr)?.get(arr)
        return arr
    }

    fun f64ArrayAt(ptr: Int, len: Int): DoubleArray {
        val arr = DoubleArray(len)
        f64PtrAccess(ptr)?.get(arr)
        return arr
    }
}