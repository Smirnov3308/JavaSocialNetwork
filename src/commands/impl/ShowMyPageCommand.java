package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.Scanner;

public class ShowMyPageCommand implements Command{
    private final Receiver receiver;
    public ShowMyPageCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        User user = network.findUser(network.getSignInId());
        System.out.println("---------------------");
        System.out.println(user.getFirstName() + " " + user.getLastName());
        System.out.println("---------------------");
        System.out.println("1) Posts");
        System.out.println("2) Messages");
        System.out.println("3) Friends");
        System.out.println("4) Groups");
        System.out.println("5) Find user");
        System.out.println("6) Setting");
        System.out.println("---------------------");
        System.out.print("Enter the command number: ");
        int i = scanner.nextInt();
    }
}
