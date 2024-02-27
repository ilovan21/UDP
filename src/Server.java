import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Crearea unui socket UDP pe portul 8000
            socket = new DatagramSocket(1234);
            byte[] buf = new byte[1000];
            DatagramPacket pack = new DatagramPacket(buf, buf.length);

            while (true) {
                // Așteptarea primirii unui pachet de la client
                socket.receive(pack);
                byte[] data = pack.getData();
                String str = new String(data, 0, pack.getLength());
                String newStr = str.toUpperCase();
                byte[] newData = newStr.getBytes();

                // Crearea unui pachet pentru a trimite datele înapoi către client
                DatagramPacket pack1 = new DatagramPacket(newData, newData.length, pack.getAddress(), pack.getPort());

                // Trimiterea pachetului către client
                socket.send(pack1);

            }
        } catch (IOException e) {
            // Tratarea excepțiilor care ar putea apărea în timpul execuției
            e.printStackTrace();
        } finally {
            // Închiderea socket-ului după terminarea comunicării
            if (socket != null) {
                socket.close();
            }
        }
    }
}