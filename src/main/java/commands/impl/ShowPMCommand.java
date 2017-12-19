package commands.impl;

import commands.Command;
import commands.Receiver;
import model.Network;
import model.PrivateMessage;
import model.User;
import services.RatingService;

import java.util.List;

public class ShowPMCommand implements Command {
    private final Receiver receiver;
    public ShowPMCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        User user = network.getUserDao().findUser(network.getSignInId());
        int rating;
        System.out.println("=====================");
        System.out.println("My private messages");
        System.out.println("=====================");

        List<PrivateMessage> messageList = network.getPrivateMessageDao().getMessages(user);
        System.out.println("---------------------");
        for (PrivateMessage message : messageList) {
            rating = RatingService.getRating(user, message, network.getPrivateMessageDao());
            System.out.println("from: " + user.getFirstName() + " to "
                    + network.getUserDao().findUser(message.getReceiverID()).getFirstName() + ": "
                    + message.getText() + " ||| rating = " + rating);
        }
        System.out.println("---------------------");
    }
}
