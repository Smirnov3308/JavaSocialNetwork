import commands.Command;
import commands.Invoker;
import commands.Receiver;
import commands.impl.CreateNewUserCommand;
import commands.impl.EditUserCommand;
import commands.impl.RemoveUserCommand;
import model.Network;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {

        Receiver receiver = new Receiver(new Network());
        Command createNewUserCommand = new CreateNewUserCommand(receiver);
        Command editUserCommand = new EditUserCommand(receiver);
        Command removeUserCommand = new RemoveUserCommand(receiver);

        Invoker invoker = new Invoker();

        try(Scanner scanner = new Scanner(System.in)) {
                while (true) {
                System.out.print("Enter the command number: ");
                int i = scanner.nextInt();
                switch (i) {
                    case 0: invoker.setCommand(createNewUserCommand); invoker.run(); break;
                    case 1: invoker.setCommand(editUserCommand); invoker.run(); break;
                    case 2: invoker.setCommand(removeUserCommand); invoker.run(); break;
                    default: throw new IllegalArgumentException("");
                }
                System.out.println("");
            }
        }
    }
}
