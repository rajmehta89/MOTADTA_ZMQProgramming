package org.example;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import com.google.gson.Gson;
import java.io.*;

public class ZMQClient {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a REQ (Request) socket
            ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
            socket.connect("tcp://10.20.41.91:5555"); // Connect to the server

            System.out.println("Client connected to server.");

            // Send JSON data
            MyObject myObject = new MyObject("John", 25);
            Gson gson = new Gson();
            String jsonData = gson.toJson(myObject);

            // Send JSON message
            socket.send(jsonData);
            System.out.println("Sent JSON: " + jsonData);

            // Receive the response
            String jsonResponse = socket.recvStr();
            System.out.println("Received response for JSON: " + jsonResponse);

            // Send serialized object data
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(myObject);
            byte[] objectData = byteOut.toByteArray();

            // Send object data
            socket.send(objectData);
            System.out.println("Sent Object: " + myObject);

            // Receive the response for object data
            String objectResponse = socket.recvStr();
            System.out.println("Received response for Object: " + objectResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Example object to send
    static class MyObject implements Serializable {
        private String name;
        private int age;

        public MyObject(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "MyObject{name='" + name + "', age=" + age + "}";
        }
    }
}
