package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.Scanner;

public class CreateNewUserCommand implements Command {
    private final Receiver receiver;
    public CreateNewUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        int id;
        String login;
        String password;
        String firstName;
        String lastName;
        Network network = receiver.getNetwork();

        System.out.println("Creating new user");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter your login: ");
        login = scanner.nextLine();
        if (network.loginFree(login)) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();

            id = network.getUserDao().getCount() + 1;
            User user = new User(id, login, password, firstName, lastName);
            network.getUserDao().addUser(user);
            System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " is added to the network, number of users in the network: " + network.getUserDao().getCount());
        } else {
            System.out.print("This login is already taken");
        }
    }
}
