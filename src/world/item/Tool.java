package world.item;

public class Tool {
    public enum ToolType {
        HAND,
        AXE,
        PICKAXE,
        SHOVEL,
        HOE,
        KNIFE
    }

    public ToolType toolType;

    public int speed;
}
