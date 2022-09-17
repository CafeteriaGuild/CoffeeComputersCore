package io.github.cafeteriaguild.coffeecomputers.archapi.v0;

public interface Environment {
    int screenWidth();

    int screenHeight();

    void updateScreen(byte[] frame);
}
