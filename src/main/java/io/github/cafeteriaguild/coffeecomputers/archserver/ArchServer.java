package io.github.cafeteriaguild.coffeecomputers.archserver;

import io.github.cafeteriaguild.coffeecomputers.archapi.v0.Architecture;

public class ArchServer {
    private final Architecture arch;

    public ArchServer(Architecture arch, int port) {
        this.arch = arch;
    }
}
