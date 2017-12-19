package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.Post;
import model.User;

import java.util.List;
import java.util.Scanner;

public class MyPostsCommand implements Command {
    private final Receiver receiver;
    public MyPostsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        User user = network.getUserDao().findUser(network.getSignInId());
        user.showUserName();
        List<Post> postList = network.getPostDao().getMessages(user);
        for (Post post : postList) {
            System.out.println(" - " + post.getText());
        }
        if (postList.size() == 0) System.out.println("Posts not found");
        System.out.println("---------------------");

        System.out.print("New post: ");
        network.getPostDao().addPost(user, scanner.nextLine());
    }
}
