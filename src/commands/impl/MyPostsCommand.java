package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.Post;
import model.User;

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
        System.out.println("---------------------");
        System.out.println("List of my posts");
        System.out.println("---------------------");
        user.showPostsList();
        System.out.println("---------------------");
        System.out.print("New post: ");
        user.addPost(new Post(scanner.nextLine()));
    }
}
