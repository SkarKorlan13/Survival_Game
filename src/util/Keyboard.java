package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {

    // ideally would not be a magic number, but some keys (i.e. VK_PLUS) are above KEY_LAST
    public static final Key[] keys = new Key[1024];

    static {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Key();
        }
    }

    public static KeyListener getListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { Keyboard.keys[e.getKeyCode()].down = true; }

            @Override
            public void keyReleased(KeyEvent e) { Keyboard.keys[e.getKeyCode()].down = false; }
        };
    }

    public static int index(char key) { return KeyEvent.getExtendedKeyCodeForChar(key); }

    public static void update() {
        for (Key key : keys) {
            key.pressed = key.down && !key.last;
            key.last = key.down;
        }
    }

    public static void tick() {
        for (Key key : keys) {
            key.pressedTick = key.down && !key.lastTick;
            key.lastTick = key.down;
        }
    }

    public static class Key {
        public boolean down, pressed, pressedTick, last, lastTick;
    }
}