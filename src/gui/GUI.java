package gui;

import java.awt.*;

public interface GUI {
    void tick();
    void render(Graphics2D g2);
    void updateDim();
}
