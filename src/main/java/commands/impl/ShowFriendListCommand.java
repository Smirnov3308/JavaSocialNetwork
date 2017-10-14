package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Invoker;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;

import java.util.Scanner;

public class ShowFriendListCommand implements Command {
    private final Receiver receiver;
    public ShowFriendListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        User user = network.findUser(network.getSignInId());

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