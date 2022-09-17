package io.github.cafeteriaguild.coffeecomputers.archapi.v0;

public interface Computer {
    void eventKeyDown(int key);

    void eventKeyUp(int key);

    void eventChar(char ch);

    void tick();

    void end();
}
