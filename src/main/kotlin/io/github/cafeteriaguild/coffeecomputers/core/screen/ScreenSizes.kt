package io.github.cafeteriaguild.coffeecomputers.core.screen

object ScreenSizes {
    val computer = ScreenSize(
        Values.computerWidth,
        Values.computerHeight
    )

    val turtle = ScreenSize(
        Values.turtleWidth,
        Values.turtleHeight
    )

    val pocketComputer = ScreenSize(
        Values.pocketComputerWidth,
        Values.pocketComputerHeight
    )

    object Values {
        const val computerWidth = 51 * 6 + 4
        const val computerHeight = 19 * 9 + 4
        const val turtleWidth = 39 * 6 + 4
        const val turtleHeight = 13 * 9 + 4
        const val pocketComputerWidth = 26 * 6 + 4
        const val pocketComputerHeight = 20 * 9 + 4
    }
}
