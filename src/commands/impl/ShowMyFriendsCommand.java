package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.Scanner;

public class ShowMyFriendsCommand implements Command {
    private final Receiver receiver;
    public ShowMyFriendsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        User user = network.findUser(network.getSignInId());
        System.out.println("---------------------");
        System.out.println("Friend List");
        System.out.println("---------------------");
        user.showFriendList();
        System.out.println("---------------------");
        System.out.print("Enter the friend number: ");
        int i = scanner.nextInt();
        // выбор друга
    }
}