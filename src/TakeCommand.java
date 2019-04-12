public class TakeCommand extends Command{
    private String itemName;

    public TakeCommand(String itemName, Level level) {
        super(level);
        this.itemName = itemName;
    }

    @Override
    protected void execute() {
        if (level.getPlayer().getCurrentRoom().containsItem(itemName)) {
            Item item = level.getPlayer().getCurrentRoom().removeItem(itemName);
            level.getPlayer().addItem(item);
            System.out.println(level.getPlayer().getName() + " has obtained " + itemName + "!");
        } else {
            System.out.println("item " + itemName + " is not in the current room");
        }
    }
}
