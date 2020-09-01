package io.github.cafeteriaguild.coffeecomputers.core.filesystem

class ComputerFileSystem {
    val rootNode = DirNode("")

    operator fun get(path: String): ComputerFile {
        var node: Node = rootNode
        for (s in path.removePrefix("/").split('/')) {
            node = (node as? DirNode)?.children?.get(s) ?: throw IllegalStateException("'$path' does not exist.")
        }
        return ComputerFile(this, path, node)
    }

    fun exists(path: String): Boolean {
        var node: Node = rootNode
        for (s in path.removePrefix("/").split('/')) {
            node = (node as? DirNode)?.children?.get(s) ?: return false
        }
        return true
    }
}
