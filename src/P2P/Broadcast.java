package P2P;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Broadcast {
    public static void main(String[] args) {
        try {
            String ipAddress = "192.168.100.50";
            String subnetMask = "255.255.255.0";

            InetAddress ip = InetAddress.getByName(ipAddress);
            InetAddress mask = InetAddress.getByName(subnetMask);

            byte[] ipBytes = ip.getAddress();
            byte[] maskBytes = mask.getAddress();

            // Calcularea adresei de broadcast
            byte[] broadcastBytes = new byte[ipBytes.length];
            for (int i = 0; i < ipBytes.length; i++) {
                broadcastBytes[i] = (byte) (ipBytes[i] | ~maskBytes[i]);
            }

            InetAddress broadcastAddress = InetAddress.getByAddress(broadcastBytes);
            System.out.println("Broadcast Address: " + broadcastAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}