public class AddCommand extends Command {
    private String response;

    public AddCommand(String response, Level level) {
        super(level);
        this.response = response;
    }

    protected void execute() {
        String[] words = response.split(" ");
        if (words[1].equals("room")) {
            String roomName = words[2];
            if (level.containsRoom(roomName)) {
                level.addDirectedEdge(level.getPlayer().getCurrentRoom().getName(), roomName);
            } else {
                level.addRoom(roomName, "");
                    level.addDirectedEdge(level.getPlayer().getCurrentRoom().getName(), roomName);
            }
        }
    }
}
