package Lesson4HW;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Network implements Closeable {
    private final Socket socket;
    private final DataOutputStream out;
    private final DataInputStream in;
    private final MessageSender messageSender;
    private final ReceiveThread receiveThread;

    public Network(String hostName, int port, MessageSender messageSender) throws IOException {
        this.socket =   new Socket(hostName,port);
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.messageSender = messageSender;
        System.out.println("fields initialised");
        receiveThread = new ReceiveThread(in,messageSender);
        receiveThread.start();
    }

    public void sentMessage(String msg){
        try {
            out.writeUTF(msg);
            out.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }



    private static class ReceiveThread extends Thread {
        private DataInputStream in;
        private MessageSender messageSender;

        public ReceiveThread(DataInputStream in,MessageSender messageSender){
                this.in = in;
                this.messageSender = messageSender;
            System.out.println("ReThread created");
        }


        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    String msg = in.readUTF();
                    System.out.println("msg read");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            messageSender.submitMessage("Server",msg);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
