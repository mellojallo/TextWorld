import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Player player = new Player("Player", "a middle-aged man");
        Level level = new Level(player);
        setUpGame(level);
        player.setCurrentRoom(level.getRoom("hall"));
        level.setPopStarRoom(level.getRoom("play-room"));
        level.setWumpusRoom(level.getRoom("bedroom"));
        addItemsIntoRooms(level);
        createAndAssignChickens(level.getChickens(), level, player);
        runGame(player, level, level.getWumpus(), level.getChickens(), level.getPopStar(), level.getCreatures());
    }
    public static void createAndAssignChickens(ArrayList<Chicken> chickens, Level level, Player player) {
        for (int i = 0; i < 1; i++) {
            Room room = level.getRandomRoom();
            Chicken temp = new Chicken(room, level, player);
            chickens.add(temp);
            level.getRoom(room.getName()).addChicken(temp);
        }
    }


    public static void runGame(Player player, Level level, Wumpus wumpus, ArrayList<Chicken> chickens, PopStar popStar,
                               ArrayList<Creature> creatures){
        String response = "";
        Scanner s = new Scanner(System.in);

        System.out.println("\nWelcome " + player.getName() + "!");

        do {
            printGameDetails(level, wumpus, popStar);
            System.out.println("\n\nYou are in the " + player.getCurrentRoom().getName().toUpperCase());
            System.out.println("Wumpus is in Room " + wumpus.getCurrentRoom().getName().toUpperCase());
            System.out.println("PopStar is in Room " + popStar.getCurrentRoom().getName().toUpperCase() + "\n");
            System.out.println("What do you want to do? >" + "\n"
                    + "Possible Commands: go <room name>, look, add room <room name>, take <item name>, drop <item name>, quit");
            response = s.nextLine();

            String[] words = response.split(" ");
            String firstWord = words[0];

            if (firstWord.equals("go") && level.containsRoom(words[1])) {
                player.move(level.getRoom(words[1]));
                for (Creature creature : creatures) {
                    creature.move();
                }
            } else if (firstWord.equals("look")) {
                System.out.println("Items in room: " + player.getCurrentRoom().getItemNames());
                System.out.println("Neighbors: " + player.getCurrentRoom().getNeighborNames());
            } else if (firstWord.equals("add")) {
                if (words[1].equals("room")) {
                    String roomName = words[2];
                    if (level.containsRoom(words[2])) {
                        level.addDirectedEdge(player.getCurrentRoom().getName(), words[2]);
                    } else {
                        level.addRoom(words[2], "");
                        level.addDirectedEdge(player.getCurrentRoom().getName(), words[2]);
                    }
                }
            } else if (firstWord.equals("quit")) {
                break;
            } else if (firstWord.equals("take")) {
                if (player.getCurrentRoom().containsItem(words[1])) {
                    Item item = player.getCurrentRoom().removeItem(words[1]);
                    player.addItem(item);
                    System.out.println(player.getName() + " has obtained " + words[1] + "!");
                } else {
                    System.out.println("item " + words[1] + " is not in the current room");
                }
            } else if (firstWord.equals("drop")) {
                if (player.containsItem(words[1])) {
                    Item item = player.removeItem(words[1]);
                    player.getCurrentRoom().addItem(item);
                    System.out.println(words[1] + " is now in " + player.getCurrentRoom().getName().toUpperCase());
                } else {
                    System.out.println("item " + words[1] + " is not in the player's possession");
                }
            }
            System.out.println();
        } while (!response.equals("quit"));
    }

    public static void printGameDetails(Level level, Wumpus wumpus, PopStar popStar) {
        System.out.println();
        for (Room room : level.getRooms()) {
            System.out.println("Room " + room.getName().toUpperCase() + " - Items: " + room.getItemNames());
        }
        System.out.println();
        for (Room room : level.getRooms()) {
            System.out.println("Room " + room.getName().toUpperCase() + " - Number of Chickens: " + room.getChickenCount());
        }
    }

    public static void addItemsIntoRooms(Level level) {
        Item picture = new Item("picture", "an old photograph of a dog and a man");
        Item wood = new Item("pillow", "a soft cushion used for sleeping");
        Item pencil = new Item("pencil", "a writing tool that belongs to a young boy");
        level.getRoom("hall").addItem(picture);
        level.getRoom("hall").addItem(wood);
        level.getRoom("hall").addItem(pencil);


        Item rope = new Item("rope", "a rope kept for emergencies");
        Item whiteboard = new Item("whiteboard", "a surface to write on plan on");
        level.getRoom("dungeon").addItem(rope);
        level.getRoom("dungeon").addItem(whiteboard);

        Item coat = new Item("coat", "a larget jacket that keeps its user warm");
        Item shoes = new Item("shoes", "fancy high heels used for partying and dancing");
        Item backpack = new Item("backpack", "a bag meant for carrying books and other supplies");
        Item necklace = new Item("necklace", "a type of jewelry that is to be worn around the neck");
        level.getRoom("closet").addItem(coat);
        level.getRoom("closet").addItem(shoes);
        level.getRoom("closet").addItem(backpack);
        level.getRoom("closet").addItem(necklace);

        Item dog = new Item("dog", "a small animal");
        Item food = new Item("food", "something to eat");
        level.getRoom("basement").addItem(dog);
        level.getRoom("basement").addItem(food);

        Item book = new Item("book", "something to study with");
        level.getRoom("study").addItem(book);
    }

    public static void setUpGame(Level level) {
        level.addRoom("hall", "a long, dark hallway");
        level.addRoom("closet", "a tiny, dark closet");
        level.addRoom("dungeon", "a vacant dungeon covered in dust and darkness");
        level.addRoom("bathroom", "a place to finish your business");
        level.addRoom("basement", "a nice comfy basement");
        level.addRoom("bedroom", "a place to sleep");
        level.addRoom("study", "where children study for school");
        level.addRoom("kitchen", "a place to cook");
        level.addRoom("living-room", "a place to hang out with friends and family");
        level.addRoom("game-room", "where children go to play");
        level.addRoom("empty-room", "an empty room");
        level.addRoom("play-room", "where children come to play with toys");

        level.addDirectedEdge("hall", "dungeon");
        level.addUndirectedEdge("hall", "closet");
        level.addDirectedEdge("hall", "bathroom");
        level.addUndirectedEdge("bathroom", "basement");
        level.addUndirectedEdge("bathroom", "bedroom");
        level.addUndirectedEdge("bathroom", "study");
        level.addUndirectedEdge("hall", "study");
        level.addUndirectedEdge("basement", "study");
        level.addDirectedEdge("closet", "dungeon");
        level.addDirectedEdge("dungeon", "hall");
        level.addDirectedEdge("bedroom", "study");
        level.addUndirectedEdge("game-room", "bedroom");
        level.addUndirectedEdge("study", "game-room");
        level.addUndirectedEdge("closet", "kitchen");
        level.addDirectedEdge("dungeon", "living-room");
        level.addUndirectedEdge("living-room", "kitchen");
        level.addUndirectedEdge("kitchen", "dungeon");
        level.addUndirectedEdge("bedroom", "living-room");
        level.addUndirectedEdge("empty-room", "closet");
        level.addUndirectedEdge("empty-room", "play-room");

    }
}
