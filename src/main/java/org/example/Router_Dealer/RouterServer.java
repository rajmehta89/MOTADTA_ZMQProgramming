package org.example.Router_Dealer;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class RouterServer {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket router = context.createSocket(ZMQ.ROUTER);
            router.bind("tcp://*:5559"); // Bind the server

            System.out.println("Router started...");
            while (true) {
                // Receive the identity of the client
                byte[] identity = router.recv();
                System.out.println("Router: Received identity -> " + new String(identity));

                // Receive the actual message
                byte[] message = router.recv();
                System.out.println("Router: Received message -> " + new String(message));

                // Send a reply to the client
                router.send(identity, ZMQ.SNDMORE); // Send identity frame
                router.send("Reply from Server".getBytes(ZMQ.CHARSET)); // Send message frame
            }
        }
    }
}
