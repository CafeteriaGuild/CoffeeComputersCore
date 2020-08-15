package io.github.cafeteriaguild.coffeecomputers.core.screen

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@OptIn(ExperimentalUnsignedTypes::class)
class ComputerScreen(val size: ScreenSize) {
    private val screenSize = size.height * size.width
    private val maxColorValue = 15

    private val frameLock = ReentrantLock(true)
    private val framebuffer = ByteArray(screenSize) { 15 }

    fun setPixel(pos: Int, color: Byte) {
        if (pos in 0 until screenSize) {
            check(color in 0..maxColorValue) { "Invalid color value" }
            frameLock.withLock {
                framebuffer[pos] = color
            }
        }
    }

    fun setPixel(x: Int, y: Int, color: Byte) {
        setPixel(x + y * size.width, color)
    }

    fun copyOfFrame() = frameLock.withLock { framebuffer.copyOf() }
}