package io.github.cafeteriaguild.coffeecomputers.core.ui

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import javax.swing.BorderFactory
import javax.swing.JPanel
import kotlin.math.min


class ComputerPanel(val screenSize: ScreenSize) : JPanel() {
    private val frameLock = Semaphore(1)
    private val frameCount = AtomicLong()
    private val framerate = AtomicLong()
    val framebuffer = AWTFramebuffer(screenSize)

    init {

        border = BorderFactory.createLineBorder(Color.black)
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate({
                framebuffer.commitChanges()
                frameLock.release()
                repaint()
            }, 0, 16666, TimeUnit.MICROSECONDS)

        val lastFrameCount = AtomicLong()
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate({
                val now = frameCount.get()
                val last = lastFrameCount.getAndSet(now)
                framerate.set(now - last)
            }, 0, 1000, TimeUnit.MILLISECONDS)
    }

    override fun getPreferredSize() = size!!

    override fun paintComponent(g: Graphics) {
        if (!frameLock.tryAcquire()) return
        val asyncImage = supplyAsync(framebuffer::render)
        frameCount.incrementAndGet()

        (g as? Graphics2D)?.apply {
            setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        }

        with(g) {
            val w = size.width
            val h = size.height
            val ratio = min(w.toDouble() / screenSize.width, h.toDouble() / screenSize.height)
            val imgW = screenSize.width * ratio
            val imgH = screenSize.height * ratio

            color = AWTScreenColors.black.brighter()
            fillRect(0, 0, w, h)

            drawImage(
                asyncImage.join(),
                ((w - imgW) / 2).toInt(), ((h - imgH) / 2).toInt(),
                imgW.toInt(), imgH.toInt(),
                AWTScreenColors.black,
                null
            )

            // Last thing to render, the framerate
            color = Color(0x5D6265)
            drawString(framerate.get().toString() + " FPS", 2, 12)
        }
    }
}
