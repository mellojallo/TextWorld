import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {

    private HashMap<String, Room> rooms;
    private ArrayList<Creature> creatures;
    private ArrayList<Chicken> chickens;
    private Wumpus wumpus;
    private PopStar popStar;

    public Level(Player player) {
        rooms = new HashMap<>();
        creatures = new ArrayList<>();
        chickens = new ArrayList<>();
        wumpus = new Wumpus(getRoom("bedroom"), this, player);
        popStar = new PopStar(getRoom("play-room"), this, player);
        for (Chicken chicken : chickens) {
            creatures.add(chicken);
        }
        creatures.add(wumpus);
        creatures.add(popStar);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public Wumpus getWumpus() {
        return wumpus;
    }
    public PopStar getPopStar() {
        return popStar;
    }

    public void setWumpusRoom(Room room) {
        wumpus.setCurrentRoom(room);
    }

    public void setPopStarRoom(Room room) {
        popStar.setCurrentRoom(room);
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public void addRoom(String name, String description) {
        Room temp = new Room(name, description);
        rooms.put(name, temp);
    }

    public boolean containsRoom(String name) {
        return rooms.containsKey(name);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room one = getRoom(name1);
        Room two = getRoom(name2);

        one.addNeighbor(two);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public Room getRandomNeighboringRoom(Room room) {
        int index = (int)(Math.random()*room.getNeighbors().size());
        return room.getNeighbors().get(index);
    }

    public ArrayList<Room> getRooms() {
        return new ArrayList<Room>(rooms.values());
    }

    public Room getRandomRoom(){
        ArrayList<Room> rooms1 = new ArrayList<>(rooms.values());
        int index = (int)(Math.random()*rooms1.size());
        return rooms1.get(index);
    }
}

class Room {
    private ArrayList<Item> items;
    private String name;
    private String description;
    private ArrayList<Room> neighbors;
    private ArrayList<Chicken> chickens;

    Room(String name, String description) {
        this.name = name;
        this.description = description;
        neighbors = new ArrayList<>();
        items = new ArrayList<>();
        chickens = new ArrayList<>();
    }

    public void addNeighbor(Room room) {
        neighbors.add(room);
    }

    public ArrayList<Room> getNeighbors() {
        return neighbors;
    }

    public String getNeighborNames() {
        String output = "";
        for (Room neighbor : neighbors) {
            output += neighbor.getName().toUpperCase() + ", ";
        }
        return output;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemNames() {
        String output = "";
        for (Item item : items) {
            output += item.getName() + ", ";
        }
        return output;
    }

    public void addItem(Item item) {
        items.add(item);
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

    public boolean containsItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
    }
    public void removeChicken(Chicken chicken) {
        chickens.remove(chicken);
    }
    public int getChickenCount() {
        return chickens.size();
    }
    public boolean isNeighborsWith(Room room) {
        if (neighbors.contains(room)) {
            return true;
        }
        return false;
    }
}
