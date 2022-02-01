package world.item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private HashMap<String, Integer> items = new HashMap<>();

    public Inventory() {
        add("testItem", 69);
    }

    public void add(String itemID, int amount) {
        if (items.containsKey(itemID)) {
            amount += items.get(itemID);
        }

        items.put(itemID, amount);
    }

    public String[] getItemsArray() {
        String[] itemsArray = new String[items.size()];
        int index=0;

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            itemsArray[index] = entry.getKey() + ": " + entry.getValue();
            index++;
        }

        return itemsArray;
    }
}
