package commands.impl;

import commands.Command;
import commands.Invoker;
import commands.Receiver;
import model.Network;
import model.User;

import java.util.List;

public class FindUserCommand implements Command {
    private final Receiver receiver;
    public FindUserCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();

        Command showAnothersPageCommand = new ShowAnothersPageCommand(receiver);
        Invoker invoker = new Invoker();

        List<User> userList = network.getUserDao().getUserList();

        System.out.println("=====================\nUser List \n=====================");
        for (User user : userList) {
            if(user.getId() != network.getSignInId())
                System.out.println(user.getId() + ") " + user.getFirstName() + " " + user.getLastName());
        }
        if (userList.isEmpty()) System.out.println("Users not found");
        System.out.println("---------------------");

        invoker.setCommand(showAnothersPageCommand);
        invoker.run();
    }
}
