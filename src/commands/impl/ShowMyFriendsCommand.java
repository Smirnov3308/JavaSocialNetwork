package commands.impl;

import commands.Command;
import commands.Invoker;
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

        Command showMyFriendsCommand = new ShowMyFriendsCommand(receiver);
        Command myPostsCommand = new MyPostsCommand(receiver);
        Invoker invoker = new Invoker();

        System.out.println("---------------------");
        System.out.println("Friend List");
        System.out.println("---------------------");
        user.showFriendList();
        System.out.println("---------------------");

        System.out.print("Enter the friend number: ");
        int i = scanner.nextInt();
        // Добавить проверку
        User friend = network.findUser(i);
        System.out.println("---------------------");
        System.out.println(friend.getFirstName() + " " + friend.getLastName());
        System.out.println("---------------------");
        System.out.println("1) To my page");
        System.out.println("2) Posts");
        //System.out.println("3) Messages");
        System.out.println("---------------------");
        System.out.print("Enter the command number: ");
        i = scanner.nextInt();
        switch (i) {
            case 1: break;
            case 2:
                System.out.println("---------------------");
                System.out.println("User's posts");
                System.out.println("---------------------");
                friend.showPostsList();
                System.out.println("---------------------");
                break;
            default: throw new IllegalArgumentException("");
        }
    }
}