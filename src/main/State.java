package main;

import java.awt.*;

public interface State {
    void tick();
    void update();
    void render(Graphics2D g2);
}