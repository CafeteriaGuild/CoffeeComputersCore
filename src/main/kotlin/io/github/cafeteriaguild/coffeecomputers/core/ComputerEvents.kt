package io.github.cafeteriaguild.coffeecomputers.core

interface ComputerEvents {
    fun onSuggestRender(computer: Computer) = Unit

    fun onFinished(computer: Computer, errored: Throwable?) = Unit

    object Ignore : ComputerEvents
}