package util;

import java.awt.event.KeyEvent;

public class ControlHandler {
    public static final Control UP = new Control(KeyEvent.VK_W, KeyEvent.VK_UP);
    public static final Control DOWN = new Control(KeyEvent.VK_S, KeyEvent.VK_DOWN);
    public static final Control LEFT = new Control(KeyEvent.VK_A, KeyEvent.VK_LEFT);
    public static final Control RIGHT = new Control(KeyEvent.VK_D, KeyEvent.VK_RIGHT);
}