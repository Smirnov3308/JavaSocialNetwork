package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;

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