package Lesson4HW;

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
    private final Thread receiver;

    public Network(String hostName, int port, MessageSender messageSender) throws IOException {
        this.socket =   new Socket(hostName,port);
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.messageSender = messageSender;

        this.receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        String msg = in.readUTF();
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
        });

        receiver.start();
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
}
