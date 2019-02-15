package Lesson6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777)){
           while (true) {
               Socket socket = serverSocket.accept();
               System.out.println("Server connected");

               try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               PrintWriter output = new PrintWriter(socket.getOutputStream())){

                   //while (!input.ready()); //зачем нужен данный цикл? почему браузер иногода передает здесь значение фалс и долго не выбивает его в тру?

                   while (input.ready()){
                       System.out.println(input.readLine());
                   }

                   output.println("HTTP/1.1 200 OK");
                   output.println("Content-Type: text/html; charset=utf-8");
                   output.println();
                   output.println("<p>Helloc W!</p>");
                   output.flush();//почему иногода приходит ответ, а иногда браузер висит? и ответа не приходит?


               }
           }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
