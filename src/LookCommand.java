public class LookCommand extends Command{

    public LookCommand(Level level) {
        super(level);
    }

    protected void execute() {
        System.out.println("Items in room: " + level.getPlayer().getCurrentRoom().getItemNames());
        System.out.println("Neighbors: " + level.getPlayer().getCurrentRoom().getNeighborNames());
    }
}
