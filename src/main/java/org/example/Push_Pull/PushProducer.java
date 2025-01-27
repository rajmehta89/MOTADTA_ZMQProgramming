package org.example.Push_Pull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PushProducer {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a PUSH socket
            ZMQ.Socket pushSocket = context.createSocket(ZMQ.PUSH);
            pushSocket.bind("tcp://*:5557"); // Bind to port 5557

            System.out.println("Producer started. Sending tasks...");

            for (int taskNumber = 1; taskNumber <= 10; taskNumber++) {
                String task = "Task #" + taskNumber;
                pushSocket.send(task); // Push task to workers
                System.out.println("Sent: " + task);
                Thread.sleep(500); // Simulate delay between tasks
            }

            System.out.println("All tasks sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
