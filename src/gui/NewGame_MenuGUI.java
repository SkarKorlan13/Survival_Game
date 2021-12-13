package gui;

import main.Global;
import util.ControlHandler;

public class NewGame_MenuGUI extends MenuGUI{

    public NewGame_MenuGUI() {
        lines = new String[] {
                "World Size",
                "Create New World",
        };
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                //case 0 -> Change world size
                case 1 -> Global.setState(Global.StateType.GAME);
            }
        }
    }
}
