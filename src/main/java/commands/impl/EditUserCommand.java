package main.java.commands.impl;

import main.java.commands.Command;
import main.java.commands.Receiver;
import main.java.model.Network;
import main.java.model.User;

import java.util.Scanner;

public class EditUserCommand implements Command {
    private final Receiver receiver;

    public EditUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        int id;
        String login;
        User user;
        Network network = receiver.getNetwork();

        System.out.println("Editing user");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        id = scanner.nextInt();
        user = network.findUser(id);
        if (user == null) System.out.print("A user with this id does not exist");
        else {
            System.out.println("Login: " + user.getLogin());
            System.out.println("Password: " + user.getPassword());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.print("Enter the command number: ");
            int i = scanner.nextInt();
            scanner.nextLine();
            switch (i) {
                case 0:
                    System.out.print("Enter new login: ");
                    login = scanner.nextLine();
                    if (network.loginFree(login)) user.setLogin(login);
                    else System.out.print("This login is already taken");
                    break;
                case 1:
                    System.out.print("Enter new password: ");
                    user.setPassword(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new First Name: ");
                    user.setFirstName(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new Last Name: ");
                    user.setLastName(scanner.nextLine());
                    break;
                default:
                    break;
            }
        }
    }
}