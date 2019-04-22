public abstract class Command {
    Level level;

    public Command(Level level) {
        this.level = level;
    }

    protected abstract void execute();
}
