package io.github.cafeteriaguild.coffeecomputers.core.filesystem

import java.io.InputStream
import java.io.OutputStream
import java.io.Reader
import java.io.Writer

interface FileInterface {
    fun reader(): Reader
    fun inputStream(): InputStream

    interface Mutable : FileInterface {
        fun writer(): Writer
        fun outputStream(): OutputStream
    }
}
