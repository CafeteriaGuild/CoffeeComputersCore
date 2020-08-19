package io.github.cafeteriaguild.coffeecomputers.core

import io.github.cafeteriaguild.coffeecomputers.core.screen.ComputerScreen
import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSizes
import io.github.cafeteriaguild.coffeecomputers.core.ui.ComputerPanel
import net.adriantodt.awt2lwjgl.KeyboardRemap
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

    val frame = JFrame("CoffeeComputersTest")
    val screen = ComputerScreen(ScreenSizes.computer)
    val panel = ComputerPanel(screen)
    val computer = Computer(screen, object : ComputerEvents {
        override fun onSuggestRender(computer: Computer) {
            panel.refreshFrame()
        }

        override fun onFinished(computer: Computer, errored: Throwable?) {
            frame.dispose()
        }
    })
    computer.start(ComputerPanel::class.java.getResourceAsStream("/bios.txt").reader())

    with(frame) {
        //background = AWTScreenColors.black.brighter()
        setSize(computer.screen.size.width * 3, computer.screen.size.height * 3)
        panel.setSize(computer.screen.size.width * 3, computer.screen.size.height * 3)
        add(panel)
        isFocusable = true
        addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent) {
                val ch = e.keyChar
                if ((ch >= 32.toChar() && ch <= 126.toChar()) || (ch >= 160.toChar() && ch <= 255.toChar())) {
                    computer.sendCharEvent(ch)
                }
            }

            override fun keyPressed(e: KeyEvent) {
                val i = KeyboardRemap.remapFromAWT(e.keyCode)
                if (i != 0) {
                    computer.sendKeyEvent(i)
                }
            }

            override fun keyReleased(e: KeyEvent) {
                val i = KeyboardRemap.remapFromAWT(e.keyCode)
                if (i != 0) {
                    computer.sendKeyUpEvent(i)
                }
            }
        })
        pack()
        setLocationRelativeTo(null)

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}