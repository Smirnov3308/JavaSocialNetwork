package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.Post;
import main.java.model.User;

import java.util.Scanner;

public class MyPostsCommand implements Command {
    private final Receiver receiver;
    public MyPostsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        User user = network.findUser(network.getSignInId());
        user.showUserName();
        user.showPostsList();
        System.out.print("New post: ");
        user.addPost(new Post(scanner.nextLine()));
    }
}
