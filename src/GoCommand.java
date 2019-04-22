public class GoCommand extends Command{
    private String roomName;

    public GoCommand(String roomName, Level level) {
        super(level);
        this.roomName = roomName;
    }

    protected void execute() {
        if (level.getPlayer().getCurrentRoom().isNeighborsWith(roomName)) {
            level.getPlayer().move(level.getPlayer().getCurrentRoom().getNeighborRoom(roomName));
            for (Creature creature : level.getCreatures()) {
                creature.move();
            }
        }
        else {
            System.out.println("Sorry, you can't go to that room");
        }
    }
}
