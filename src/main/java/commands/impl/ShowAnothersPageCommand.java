package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.PrivateMessage;
import model.User;

import java.util.List;
import java.util.Scanner;

public class ShowAnothersPageCommand implements Command {
    private final Receiver receiver;
    public ShowAnothersPageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        User user = network.getUserDao().findUser(network.getSignInId());

        System.out.print("Enter the user number: ");
        int i = scanner.nextInt();
        if (i == network.getSignInId() || network.getUserDao().findUser(i) == null)
            throw new IllegalArgumentException("");
        System.out.println("\n\n");

        User friend = network.getUserDao().findUser(i);
        friend.showUserName();
        System.out.println(" 1) To my page \n 2) Posts \n 3) Messages \n 4) New message \n" +
                " 5) Add or remove friend \n---------------------\nEnter the command number: ");

        i = scanner.nextInt();
        switch (i) {
            case 1: break;
            case 2:
                friend.showUserName();
                friend.showPostsList();
                break;
            case 3:
                List<PrivateMessage> messageList = network.getPrivateMessageDao().getMessages(friend, user);
                messageList.addAll(network.getPrivateMessageDao().getMessages(user, friend));
                System.out.println("---------------------");
                if (messageList.isEmpty()) System.out.println("No messages");
                else
                    for (PrivateMessage message : messageList) {
                        System.out.println("from: " + user.getFirstName() + " to " + friend.getFirstName() + ": "
                                + message.getText());
                    }
                System.out.println("---------------------");
                break;
            case 4:
                scanner.nextLine();
                System.out.print("New message: ");
                network.getPrivateMessageDao().addPrivateMessage(user, friend, scanner.nextLine());
                break;
            case 5:
                if(user.addFriend(friend)) System.out.println("User successfully added");
                else {
                    user.removeFriend(friend);
                    System.out.println("User successfully removed from friend list");
                }
                break;
            default: throw new IllegalArgumentException("");
        }

    }
}
