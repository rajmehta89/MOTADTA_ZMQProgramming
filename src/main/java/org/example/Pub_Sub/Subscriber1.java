package org.example.Pub_Sub;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Subscriber1 {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a SUB socket
            ZMQ.Socket subscriber = context.createSocket(ZMQ.SUB);
            subscriber.connect("tcp://localhost:5556"); // Connect to the publisher

            // Subscribe to the "news" topic
            subscriber.subscribe("news".getBytes());

            System.out.println("Subscriber connected and waiting for messages...");

            while (!Thread.currentThread().isInterrupted()) {
                // Receive the message
                String message = subscriber.recvStr();
                System.out.println("Received: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

