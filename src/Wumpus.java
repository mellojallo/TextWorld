public class Wumpus extends Creature {

    public Wumpus(Room currentRoom, Level level, Player player) {
        super(currentRoom, level, player);
    }

    public void move(){
        for (Room room : currentRoom.getNeighbors()) {
            if (!room.equals(player.getCurrentRoom()) && !player.getCurrentRoom().getNeighbors().contains(room)) {
                currentRoom = room;
                return;
            }
        }
        currentRoom = level.getRandomNeighboringRoom(currentRoom);
    }
}
