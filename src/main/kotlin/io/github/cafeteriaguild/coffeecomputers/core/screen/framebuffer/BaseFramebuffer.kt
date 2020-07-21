package io.github.cafeteriaguild.coffeecomputers.core.screen.framebuffer

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize
import it.unimi.dsi.fastutil.ints.Int2ByteMap
import it.unimi.dsi.fastutil.ints.Int2ByteOpenHashMap
import it.unimi.dsi.fastutil.ints.Int2ByteRBTreeMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

abstract class BaseFramebuffer<R>(val size: ScreenSize) {
    val maxPixelValue = size.height * size.width
    val maxColorValue = 15

    // All the pixels on the screen
    // Guarded by the main lock
    private val frameData = Int2ByteRBTreeMap()

    // Last changes commited
    // Guarded by the main lock
    private val lastChanges = Int2ByteOpenHashMap(maxPixelValue / 8)

    // Changes not yet sent to frame
    // Guarded by the user lock
    private val currentChanges = Int2ByteOpenHashMap(maxPixelValue / 8)

    // Locks
    private val mainLock = ReentrantLock()
    private val userLock = ReentrantLock()

    fun setPixel(pos: Int, color: Byte) {
        if (pos in 0 until maxPixelValue) {
            check(color in 0 until maxColorValue) { "Invalid color value" }
            userLock.withLock { currentChanges.put(pos, color) }
        }
    }

    fun setPixel(x: Int, y: Int, color: Byte) {
        setPixel(x + y * size.width, color)
    }

    fun <R> directAccess(block: (Int2ByteMap) -> R): R {
        return userLock.withLock { block(currentChanges) }
    }

    protected open fun suggestRender() = Unit

    fun commitChanges() {
        userLock.withLock {
            mainLock.withLock {
                lastChanges.clear()
                lastChanges.putAll(currentChanges)
                frameData.putAll(currentChanges)
            }
            currentChanges.clear()
        }
        suggestRender()
    }

    // Implementation details

    abstract fun render(): R

    abstract fun renderChanges(): R

    protected fun frameData(block: (Int2ByteMap) -> R): R {
        return mainLock.withLock { block(frameData) }
    }

    protected fun lastChanges(block: (Int2ByteMap) -> R): R {
        return mainLock.withLock { block(lastChanges) }
    }
}