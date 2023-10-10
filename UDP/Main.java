package org.example;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {


   static public void main(String[] args) throws InterruptedException, IOException {

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


               DatagramPacket dp=new DatagramPacket(bytes,bytes.length,address,10085);
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
               ds = new DatagramSocket(10086);
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

