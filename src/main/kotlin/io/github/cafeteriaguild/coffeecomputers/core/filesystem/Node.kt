package io.github.cafeteriaguild.coffeecomputers.core.filesystem

import java.util.concurrent.ConcurrentHashMap

sealed class Node {
    abstract val name: String
}

class FileNode(override val name: String, val handler: FileInterface) : Node()

class DirNode(override val name: String) : Node() {
    val children = ConcurrentHashMap<String, Node>()
}
