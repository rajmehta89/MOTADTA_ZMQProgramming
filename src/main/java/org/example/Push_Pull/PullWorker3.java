package org.example.Push_Pull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PullWorker3 {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a PULL socket
            ZMQ.Socket pullSocket = context.createSocket(ZMQ.PULL);
            pullSocket.connect("tcp://localhost:5557"); // Connect to the producer

            System.out.println("Worker started. Waiting for tasks...");

            while (!Thread.currentThread().isInterrupted()) {
                // Receive a task
                String task = pullSocket.recvStr();
                System.out.println("Received: " + task);

                // Simulate task processing
                Thread.sleep(1000);
                System.out.println("Processed: " + task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

