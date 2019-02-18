package Lesson6;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ConnectionThread connectionThread = new ConnectionThread(socket);
                System.out.println("Connection started");
                connectionThread.start();
                MsgMassSendThread msgMassSendThread= new MsgMassSendThread(socket);
                msgMassSendThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static class ConnectionTread extends Thread{
        @Override
        public void run() {
            ;
        }
    }

    public static class ConnectionThread extends Thread{
        private Socket socket;

        ConnectionThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("thread created ");
                MsgCaptureThread msgCaptureThread = new MsgCaptureThread(socket);
                msgCaptureThread.start();
        }
    }

    public static class MsgCaptureThread extends Thread{
        private Socket socket;

        public MsgCaptureThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataInputStream inp = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream((socket.getOutputStream()))){
                    while (true) {
                        String msg = inp.readUTF();
                        System.out.println("Message: " + msg);
                        out.writeUTF(msg);
                        out.flush();
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static class MsgMassSendThread extends Thread{
        private Socket socket;

        public MsgMassSendThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
                 BufferedReader reader = new BufferedReader( new InputStreamReader(System.in))){
                while (true) {
                    String massMsg = reader.readLine();
                    System.out.println("Message for mass sent: " + massMsg);
                    out.writeUTF(massMsg);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

