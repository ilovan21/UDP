package P2P;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
// Client A
public class Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket(12345)) {
            clientSocket.setBroadcast(true);

            Thread receiveThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    while (true) {
                        clientSocket.receive(receivePacket);
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Client B: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the server address: localhost ");
                String address = reader.readLine();
                InetAddress clientBAddress = InetAddress.getByName(address); // Adresa IP a clientului B
                System.out.println("Enter the port: 12345 ");
                String clientBPort = reader.readLine();
                System.out.println("Scrie un mesaj: ");
                String message = reader.readLine();
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientBAddress, Integer.parseInt(clientBPort));
                clientSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
