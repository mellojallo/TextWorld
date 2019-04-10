public abstract class Creature {
    protected Room currentRoom;
    protected Level level;
    protected Player player;

    protected Creature(Room currentRoom, Level level, Player player) {
        this.currentRoom = currentRoom;
        this.level = level;
        this.player = player;
    }

    protected abstract void move();

    protected Room getCurrentRoom() {
        return currentRoom;
    }

    protected void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
