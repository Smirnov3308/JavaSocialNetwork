package commands.impl;

import commands.Command;
import commands.Invoker;
import commands.Receiver;
import model.Network;

public class FindUserCommand implements Command {
    private final Receiver receiver;
    public FindUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();

        Command showAnothersPageCommand = new ShowAnothersPageCommand(receiver);
        Invoker invoker = new Invoker();

        System.out.println("=====================\nUser List \n=====================");
        network.showUserList();
        System.out.println("---------------------");

        invoker.setCommand(showAnothersPageCommand);
        invoker.run();
    }
}
