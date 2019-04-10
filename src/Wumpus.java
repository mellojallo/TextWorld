public class Wumpus extends Creature {

    public Wumpus(Room currentRoom, Level level, Player player) {
        super(currentRoom, level, player);
    }

    public void move(){
        boolean again;
        Room room;
        do {
            again = true;
            room = level.getRandomNeighboringRoom(currentRoom);
            if (!room.equals(player.getCurrentRoom())) {
                again = false;
            }
        } while (again);
        currentRoom = room;
    }
}
