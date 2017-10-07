package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;

import java.util.Scanner;

public class RemoveUserCommand implements Command {
    private final Receiver receiver;
    public RemoveUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        int id;
        Network network = receiver.getNetwork();

        System.out.println("Delete user");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        id = scanner.nextInt();

        if (network.findUser(id) == null) System.out.print("A user with this id does not exist");
        else {
            network.deleteUser(id);
            System.out.println("The user has been removed, number of users in the network: " + network.getNumberOfUsers());
        }
    }
}
