public class PopStar extends Creature{

    public PopStar(Room currentRoom, Level level, Player player) {
        super(currentRoom, level, player);
    }

    protected void move() {
        if (currentRoom.getNeighbors().contains(player.getCurrentRoom()) || currentRoom.equals(player.getCurrentRoom())) {
            currentRoom = player.getCurrentRoom();
            return;
        }

        for (Room room : currentRoom.getNeighbors()) {
            if (room.getNeighbors().contains(player.getCurrentRoom())) {
                currentRoom = room;
                return;
            }
        }

        Room room = level.getRandomNeighboringRoom(currentRoom);
        currentRoom = room;
    }
}
