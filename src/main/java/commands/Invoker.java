package commands;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        System.out.println("");
        if (command != null) command.execute();
    }
}
