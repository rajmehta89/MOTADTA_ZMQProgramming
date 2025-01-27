package org.example.Req_Response;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Request {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a REQ (Request) socket
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
            socket.connect("tcp://10.20.41.91:5558"); // Connect to the server

            System.out.println("Client connected to server.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String request = reader.readLine();

                // Exit if the user types "exit"
                if ("exit".equalsIgnoreCase(request)) {
                    System.out.println("Exiting...");
                    break;
                }

                // Send the request
                socket.send(request);
                System.out.println("Sent: " + request);

                // Receive a response
                String response = socket.recvStr();
                System.out.println("Received: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
