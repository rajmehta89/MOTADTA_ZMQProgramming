package org.example.Req_Response;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Response {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a REP (Reply) socket
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555"); // Bind to port 5555

            System.out.println("Server is ready. Waiting for messages...");

            while (!Thread.currentThread().isInterrupted()) {
                // Receive a request
                String request = socket.recvStr();
                System.out.println("Received: " + request);

                // Send a response
                String response = "Hello, " + request + "!";
                socket.send(response);
                System.out.println("Sent: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}