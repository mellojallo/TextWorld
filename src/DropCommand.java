public class DropCommand extends Command {
    private String itemName;

    public DropCommand(String itemName, Level level) {
        super(level);
        this.itemName = itemName;
    }

    public void execute() {
        if (level.getPlayer().containsItem(itemName)) {
            Item item = level.getPlayer().removeItem(itemName);
                level.getPlayer().getCurrentRoom().addItem(item);
                    System.out.println(itemName + " is now in " + level.getPlayer().getCurrentRoom().getName().toUpperCase());
        } else {
            System.out.println("item " + itemName + " is not in the player's possession");
        }
    }
}
