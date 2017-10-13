package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;

public class ShowAnothersPageCommand implements Command {
    private final Receiver receiver;
    public ShowAnothersPageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {

    }
}
