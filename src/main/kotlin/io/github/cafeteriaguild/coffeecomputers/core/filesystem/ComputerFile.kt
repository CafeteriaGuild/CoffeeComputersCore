package io.github.cafeteriaguild.coffeecomputers.core.filesystem

import java.io.InputStream
import java.io.OutputStream
import java.io.Reader
import java.io.Writer

class ComputerFile(val fs: ComputerFileSystem, val path: String, val fileNode: Node) {
    val isFile = fileNode is FileNode
    val isDirectory = fileNode is DirNode
    val parentPath = path.substringBeforeLast('/')
    val name = path.substringAfterLast('/')
    val handler = (fileNode as? FileNode)?.handler

    fun parent() = fs[parentPath]

    fun childrenNames(): List<String> {
        return (fileNode as? DirNode)?.children?.keys?.toList()
            ?: throw IllegalStateException("'$path' is not a Directory")
    }

    fun child(name: String) = fs["$path/$name"]

    fun children() = childrenNames().map(this::child)

    fun reader(): Reader {
        return (fileNode as? FileNode)?.handler?.reader()
            ?: throw IllegalStateException("'$path' is not a File")
    }

    fun inputStream(): InputStream {
        return (fileNode as? FileNode)?.handler?.inputStream()
            ?: throw IllegalStateException("'$path' is not a File")
    }

    fun writer(): Writer {
        return ((fileNode as? FileNode)?.handler as? FileInterface.Mutable)?.writer()
            ?: throw IllegalStateException("'$path' is not a File")
    }

    fun outputStream(): OutputStream {
        return ((fileNode as? FileNode)?.handler as? FileInterface.Mutable)?.outputStream()
            ?: throw IllegalStateException("'$path' is not a File")
    }

}