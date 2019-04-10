public class Chicken extends Creature {

    public Chicken(Room currentRoom, Level level, Player player) {
        super(currentRoom, level, player);
    }

    protected void move() {
        Room room = level.getRandomNeighboringRoom(currentRoom);
        currentRoom.removeChicken(this);
        room.addChicken(this);
        currentRoom = room;
    }
}
