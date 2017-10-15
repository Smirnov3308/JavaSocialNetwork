package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Invoker;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;

import java.util.Scanner;

public class ShowMessagesCommand implements Command {
    private final Receiver receiver;
    public ShowMessagesCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        User user = network.findUser(network.getSignInId());

        System.out.println("=====================");
        System.out.println("My private messages");
        System.out.println("=====================");
        network.showMessageList(user);
    }
}