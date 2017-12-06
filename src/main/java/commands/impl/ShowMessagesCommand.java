package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

public class ShowMessagesCommand implements Command {
    private final Receiver receiver;
    public ShowMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getUserDao().findUser(network.getSignInId());

        System.out.println("=====================");
        System.out.println("My private messages");
        System.out.println("=====================");
        network.showMessageList(user);
    }
}
