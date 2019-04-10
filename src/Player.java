import java.lang.reflect.Array;
import java.util.ArrayList;
public class Player{
    private String name;
    private String description;
    private ArrayList<Item> items;
    private Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        this.currentRoom = currentRoom;
    }
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void move(Room nextRoom) {
        currentRoom = nextRoom;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public Item removeItem(String name) {
        Item itemToRemove = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                itemToRemove = items.get(i);
            }
        }
        items.remove(itemToRemove);
        return itemToRemove;
    }
    public boolean destroyItem(String name) {
        Item itemToRemove = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                itemToRemove = items.get(i);
            }
        }
        return items.remove(itemToRemove);
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void displayInventory() {
        System.out.println("Inventory");
        for (Item item : items) {
            System.out.println(item.getName() + ": " + item.getDescription());
        }
    }

    public String getItemNames() {
        String output = "";
        for (Item item : items) {
            output += item.getName() + " || ";
        }
        return output;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean containsItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}
