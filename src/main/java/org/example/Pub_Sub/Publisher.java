package org.example.Pub_Sub;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a PUB socket
            ZMQ.Socket publisher = context.createSocket(ZMQ.PUB);
            publisher.bind("tcp://*:5556"); // Bind to port 5556

            System.out.println("Publisher started. Broadcasting messages...");

            int messageCount = 0;
            while (!Thread.currentThread().isInterrupted()) {
                String topic = "news";
                String message = topic + " Message #" + messageCount++;
                publisher.send(message); // Publish message
                System.out.println("Published: " + message);

                Thread.sleep(1000); // Sleep for 1 second before the next message
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

