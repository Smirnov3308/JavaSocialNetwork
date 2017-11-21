package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;

import java.util.Scanner;

public class LoginCommand implements Command {
    private final Receiver receiver;
    public LoginCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        String login;
        String password;
        Network network = receiver.getNetwork();

        System.out.println("Sign in");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter your login: ");
        login = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        network.signIn(login, password);
    }
}
