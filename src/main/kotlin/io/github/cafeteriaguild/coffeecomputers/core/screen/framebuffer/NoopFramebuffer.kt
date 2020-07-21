package io.github.cafeteriaguild.coffeecomputers.core.screen.framebuffer

import io.github.cafeteriaguild.coffeecomputers.core.screen.ScreenSize

class NoopFramebuffer(size: ScreenSize) : BaseFramebuffer<Unit>(size) {
    override fun render() {
    }

    override fun renderChanges() {
    }
}