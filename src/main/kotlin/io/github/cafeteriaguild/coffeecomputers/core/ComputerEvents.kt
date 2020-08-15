package io.github.cafeteriaguild.coffeecomputers.core

interface ComputerEvents {
    fun onSuggestRender(computer: Computer) = Unit

    object Ignore : ComputerEvents
}