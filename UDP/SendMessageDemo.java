package org.example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SendMessageDemo {
    public static void main(String[] args) throws IOException {
        new Thread(()->{
            String str;
            Scanner sc=new Scanner(System.in);
            InetAddress address= null;
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            DatagramSocket ds= null;
            try {
                ds = new DatagramSocket();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            while (true){
                str=sc.nextLine();
                byte[] bytes=str.getBytes();


                DatagramPacket dp=new DatagramPacket(bytes,bytes.length,address,10086);
                try {
                    ds.send(dp);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }).start();

        new Thread(()->{
            DatagramSocket ds= null;
            try {
                ds = new DatagramSocket(10085);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            byte[] bytes=new byte[1024];

            DatagramPacket dp=new DatagramPacket(bytes,bytes.length);
            while (true){
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                byte[] data=dp.getData();

                System.out.println(new String(data,0,dp.getLength()));

            }
        }).start();

    }
}
