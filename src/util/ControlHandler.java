package util;

import java.awt.event.KeyEvent;

public class ControlHandler {
    public static final Control UP = new Control(KeyEvent.VK_W, KeyEvent.VK_UP);
    public static final Control DOWN = new Control(KeyEvent.VK_S, KeyEvent.VK_DOWN);
    public static final Control LEFT = new Control(KeyEvent.VK_A, KeyEvent.VK_LEFT);
    public static final Control RIGHT = new Control(KeyEvent.VK_D, KeyEvent.VK_RIGHT);
    public static final Control SELECT = new Control(KeyEvent.VK_ENTER);
    public static final Control BACK = new Control(KeyEvent.VK_BACK_SPACE);
    public static final Control DELETE = new Control(KeyEvent.VK_DELETE);
    public static final Control ESC = new Control(KeyEvent.VK_ESCAPE);
    public static final Control INTERACT = new Control(KeyEvent.VK_SPACE);


    //Testing purposes only
    public static final Control TEST1 = new Control(KeyEvent.VK_1);
    public static final Control TEST2 = new Control(KeyEvent.VK_2);
    public static final Control TEST3 = new Control(KeyEvent.VK_3);
}