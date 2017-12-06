package commands.impl;

import commands.Command;
import commands.Invoker;
import commands.Receiver;
import model.Network;
import model.User;

public class ShowFriendListCommand implements Command {
    private final Receiver receiver;
    public ShowFriendListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getUserDao().findUser(network.getSignInId());

        Command showAnothersPageCommand = new ShowAnothersPageCommand(receiver);
        Invoker invoker = new Invoker();

        System.out.println("=====================");
        System.out.println("Friend List");
        System.out.println("=====================");
        user.showFriendList();
        System.out.println("---------------------");

        invoker.setCommand(showAnothersPageCommand);
        invoker.run();
    }
}
