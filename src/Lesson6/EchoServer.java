package Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("thread created ");
                        try (DataInputStream inp = new DataInputStream(socket.getInputStream());
                             DataOutputStream out = new DataOutputStream((socket.getOutputStream()))){

                            while (true){
                                String msg = inp.readUTF();
                                System.out.println("Message: " + msg);
                                out.writeUTF(msg);
                                out.flush();
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
