package io.github.cafeteriaguild.coffeecomputers.core

import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSizes
import io.github.cafeteriaguild.coffeecomputers.core.ui.ComputerPanel
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    val computer = Computer(ComputerScreen(ScreenSizes.computer))
    val panel = ComputerPanel(computer.screen)
    computer.start(ComputerPanel::class.java.getResourceAsStream("/bios.txt").reader())

    with(JFrame("CoffeeComputersTest")) {
        //background = AWTScreenColors.black.brighter()
        setSize(computer.screen.size.width * 3, computer.screen.size.height * 3)
        panel.setSize(computer.screen.size.width * 3, computer.screen.size.height * 3)
        add(panel)
        pack()
        setLocationRelativeTo(null)

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}