package org.example.Router_Dealer;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class DealerClient {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket dealer = context.createSocket(ZMQ.DEALER);
            dealer.setIdentity("Client1".getBytes(ZMQ.CHARSET)); // Set a custom identity
            dealer.connect("tcp://localhost:5559");

            System.out.println("Dealer connected...");

            // Send a message to the server
            String request = "Hello, Router!";
            dealer.send(request.getBytes(ZMQ.CHARSET));
            System.out.println("Dealer: Sent -> " + request);

            // Receive the reply from the server
            String reply = dealer.recvStr();
            System.out.println("Dealer: Received -> " + reply);
        }
    }
}
