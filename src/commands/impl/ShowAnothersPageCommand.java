package commands.impl;

import commands.Command;
import commands.Receiver;

public class ShowAnothersPageCommand implements Command {
    private final Receiver receiver;
    public ShowAnothersPageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {

    }
}
