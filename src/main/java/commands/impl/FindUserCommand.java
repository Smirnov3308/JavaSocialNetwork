package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;

import java.util.Scanner;

public class FindUserCommand implements Command {
    private final Receiver receiver;
    public FindUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================\nUser List \n=====================");
        network.showUserList();
        System.out.println("---------------------\nEnter the friend number: ");
        int i = scanner.nextInt();
        User user = network.findUser(i);
        if (i == network.getSignInId() || user == null) throw new IllegalArgumentException("");
        else user.showPage();
    }
}
