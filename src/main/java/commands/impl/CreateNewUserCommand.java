package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your login: ");
        login = scanner.nextLine();
        if (network.loginFree(login)) {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();

            id = network.getNumberOfUsers() + 1;
            User user = new User(id, login, password, firstName, lastName);
            network.addUser(user);
            System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " is added to the network, number of users in the network: " + network.getNumberOfUsers());
        } else {
            System.out.print("This login is already taken");
        }
    }
}