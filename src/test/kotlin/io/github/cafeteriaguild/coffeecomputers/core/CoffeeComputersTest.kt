package io.github.cafeteriaguild.coffeecomputers.core

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSizes
import io.github.cafeteriaguild.coffeecomputers.core.ui.ComputerPanel
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    val screenSize = ScreenSizes.computer
    val panel = ComputerPanel(screenSize)
    val framebuffer = panel.framebuffer
    val c = ComputerCore(framebuffer)
    c.start(ComputerPanel::class.java.getResourceAsStream("/bios.txt").reader())

    with(JFrame("CoffeeComputersTest")) {
        //background = AWTScreenColors.black.brighter()
        setSize(screenSize.width * 3, screenSize.height * 3)
        panel.setSize(screenSize.width * 3, screenSize.height * 3)
        add(panel)
        pack()
        setLocationRelativeTo(null)

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}