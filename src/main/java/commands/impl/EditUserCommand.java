package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

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
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.print("Enter user id: ");
        id = scanner.nextInt();
        user = network.getUserDao().findUser(id);
        if (user == null) System.out.print("A user with this id does not exist");
        else {
            System.out.println("1) Edit login: " + user.getLogin());
            System.out.println("2) Edit password: " + user.getPassword());
            System.out.println("3) Edit First Name: " + user.getFirstName());
            System.out.println("4) Edit Last Name: " + user.getLastName());
            System.out.print("Enter the command number: ");
            int i = scanner.nextInt();
            scanner.nextLine();
            switch (i) {
                case 1:
                    System.out.print("Enter new login: ");
                    login = scanner.nextLine();
                    if (network.loginFree(login)) user.setLogin(login);
                    else System.out.print("This login is already taken");
                    break;
                case 2:
                    System.out.print("Enter new password: ");
                    user.setPassword(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new First Name: ");
                    user.setFirstName(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new Last Name: ");
                    user.setLastName(scanner.nextLine());
                    break;
                default:
                    break;
            }
            network.getUserDao().editUser(user);
        }
    }
}
