package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;

public class LogOutCommand implements Command {
    private final Receiver receiver;
    public LogOutCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        String login;
        String password;
        Network network = receiver.getNetwork();
        network.logOut();
    }
}