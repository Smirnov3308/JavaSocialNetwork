package main.java;

import main.java.commands.Command;
import main.java.commands.Invoker;
import main.java.commands.Receiver;
import main.java.commands.impl.*;
import main.java.model.Network;
import main.java.model.Post;
import main.java.model.User;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {

        Receiver receiver = new Receiver(new Network());
        Command createNewUserCommand = new CreateNewUserCommand(receiver);
        Command editUserCommand = new EditUserCommand(receiver);
        Command removeUserCommand = new RemoveUserCommand(receiver);
        Command loginCommand = new LoginCommand(receiver);
        Command logOutCommand = new LogOutCommand(receiver);
        Command showFriendListCommand = new ShowFriendListCommand(receiver);
        Command myPostsCommand = new MyPostsCommand(receiver);
        Command showMessagesCommand = new ShowMessagesCommand(receiver);
        FindUserCommand findUserCommand = new FindUserCommand(receiver);
        Invoker invoker = new Invoker();

        //// Тестовые данные
        Network network = receiver.getNetwork();
        network.addUser(new User(1, "VSmirnov", "1","Vadim","Smirnov"));
        network.addUser(new User(2, "IBorovkov", "2","Ivan","Borovkov"));
        network.addUser(new User(3, "ASoboleva", "3","Anna","Soboleva"));
        network.addUser(new User(4, "MPolyakov", "4","Mihail","Polyakov"));
        network.addUser(new User(5, "LEfremova", "5","Lina","Efremova"));

        network.findUser(1).addFriend(network.findUser(2));
        network.findUser(1).addFriend(network.findUser(3));
        network.findUser(1).addFriend(network.findUser(4));

        network.findUser(1).addPost(new Post("My first post"));
        network.findUser(1).addPost(new Post("My second post"));
        network.findUser(2).addPost(new Post("My first post"));
        network.findUser(2).addPost(new Post("My second post"));


        network.addPM(network.findUser(2), network.findUser(1), "Hello 1");
        network.addPM(network.findUser(2), network.findUser(1), "Hello 2");
        network.addPM(network.findUser(2), network.findUser(1), "Hello 3");

        network.addPM(network.findUser(1), network.findUser(3), "Hello, my name is Vadim.");
        network.addPM(network.findUser(1), network.findUser(3), "What is your name?");
        network.addPM(network.findUser(1), network.findUser(3), "Nice to meet you");

        network.addPM(network.findUser(1), network.findUser(3), "my my");
        network.addPM(network.findUser(1), network.findUser(3), "Nice421412 Nice nice nice hello");
        network.addPM(network.findUser(1), network.findUser(3), "hello hello");
        network.addPM(network.findUser(1), network.findUser(3), "is");
        ////

        int i;

        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (network.getSignInId() == 0) {
                    System.out.println(" 1) Sign in \n 2) Create a New Account \n Enter the command number:");
                    i = scanner.nextInt();

                    switch (i) {
                        case 1: invoker.setCommand(loginCommand); invoker.run(); break;
                        case 2: invoker.setCommand(createNewUserCommand); invoker.run(); break;
                        default: throw new IllegalArgumentException("");
                    }
                } else {
                    User user = network.findUser(network.getSignInId());
                    user.showUserName();
                    System.out.println(" 1) Posts\n 2) Messages\n 3) Friends\n 4) Find user\n 5) Log out\n" +
                            " 6) Show top10 words\n---------------------\nEnter the command number: ");
                    i = scanner.nextInt();

                    switch (i) {
                        case 1: invoker.setCommand(myPostsCommand); invoker.run(); break;
                        case 2: invoker.setCommand(showMessagesCommand); invoker.run(); break;
                        case 3: invoker.setCommand(showFriendListCommand); invoker.run(); break;
                        case 4: invoker.setCommand(findUserCommand); invoker.run(); break;
                        case 5: invoker.setCommand(logOutCommand); invoker.run(); break;
                        case 6:
                            network.findUser(1).showTop10Word();
                            break;
                        default: throw new IllegalArgumentException("");
                    }
                }
                System.out.println("");
            }
        }
    }
}