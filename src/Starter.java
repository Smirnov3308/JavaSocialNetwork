import commands.Command;
import commands.Invoker;
import commands.Receiver;
import commands.impl.*;
import model.Network;
import model.Post;
import model.User;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {

        Receiver receiver = new Receiver(new Network());
        Command createNewUserCommand = new CreateNewUserCommand(receiver);
        Command editUserCommand = new EditUserCommand(receiver);
        Command removeUserCommand = new RemoveUserCommand(receiver);
        Command loginCommand = new LoginCommand(receiver);
        Command logOutCommand = new LogOutCommand(receiver);
        Command showMyFriendsCommand = new ShowMyFriendsCommand(receiver);
        Command myPostsCommand = new MyPostsCommand(receiver);
        Invoker invoker = new Invoker();

        //// Начальные данные
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
        ////

        int i;

        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (network.getSignInId() != 0) {
                    User user = network.findUser(network.getSignInId());
                    System.out.println("---------------------");
                    System.out.println(user.getFirstName() + " " + user.getLastName());
                    System.out.println("---------------------");
                    System.out.println("1) Posts");
                    System.out.println("2) Messages");
                    System.out.println("3) Friends");
                    System.out.println("4) Groups");
                    System.out.println("5) Find user");
                    System.out.println("6) Log out");
                    System.out.println("---------------------");
                    System.out.print("Enter the command number: ");
                    i = scanner.nextInt();
                    switch (i) {
                        case 1: invoker.setCommand(myPostsCommand); invoker.run(); break;
                        case 3: invoker.setCommand(showMyFriendsCommand); invoker.run(); break;
                        case 6: invoker.setCommand(logOutCommand); invoker.run(); break;
                        default: throw new IllegalArgumentException("");
                    }
                } else {
                    System.out.println("1) Sign in");
                    System.out.println("2) Create a New Account");
                    System.out.print("Enter the command number: ");
                    i = scanner.nextInt();
                    switch (i) {
                        case 1: invoker.setCommand(loginCommand); invoker.run(); break;
                        case 2: invoker.setCommand(createNewUserCommand); invoker.run(); break;
                        default: throw new IllegalArgumentException("");
                    }
                }

            }
        }
    }
}
                /*
                System.out.print("Enter the command number: ");
                i = scanner.nextInt();
                switch (i) {
                    case 0: invoker.setCommand(createNewUserCommand); invoker.run(); break;
                    case 1: invoker.setCommand(editUserCommand); invoker.run(); break;
                    case 2: invoker.setCommand(removeUserCommand); invoker.run(); break;
                    default: throw new IllegalArgumentException("");
                }
                System.out.println("");
                */
