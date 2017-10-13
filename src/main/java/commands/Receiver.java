package main.java.commands;

import main.java.model.Network;

public class Receiver {
    private final Network network;

    public Receiver(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }
}