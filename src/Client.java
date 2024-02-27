import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket chat = null;
        try {
            // Crearea unui socket UDP
            chat = new DatagramSocket();

            // Datele de trimis către server
            byte[] data = "Hello Java".getBytes();

            // Trimiterea pachetului către server
            DatagramPacket packet1 = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 1234);
            chat.send(packet1);

            // Datele primite vor fi stocate într-un tablou de octeți de lungime maximă 1024
            byte[] receiveData = new byte[1024];

            // Crearea unui pachet pentru a primi datele de la server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Așteptarea primirii unui pachet de la server
            chat.receive(receivePacket);

            // Convertirea datelor primite într-un șir de caractere
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Afișarea mesajului primit de la server
            System.out.println("Received message from server: " + receivedMessage);
        } catch (IOException e) {
            // Tratarea excepțiilor care ar putea apărea în timpul execuției
            e.printStackTrace();
        } finally {
            // Închiderea socket-ului după terminarea comunicării
            if (chat != null) {
                chat.close();
            }
        }
    }
}

