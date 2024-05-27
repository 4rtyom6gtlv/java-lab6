/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.lab1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author iljak
 */
public class Server {

    
    
    
    static class ServerOneJabber extends Thread {
    
    private static double a;
    private static double b;
    private static double n;
    private static double result;
    private static Socket socket;
    private static BufferedReader in;
    
    Double getA(){
        return a;
    }
    
    Double getB(){
        return b;
    }
    
    Double getN(){
        return n;
    }
    
    Double getResult(){
        return result;
    }
    
    public ServerOneJabber(Socket s) throws IOException{
        socket = s;
        in = new BufferedReader(new 
	InputStreamReader(socket.getInputStream()));
    }
            
    @Override
    public void run(){
        try {
            a = Double.parseDouble(in.readLine());
            b = Double.parseDouble(in.readLine());
            n = Double.parseDouble(in.readLine());
            result = Double.parseDouble(in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(ServerOneJabber.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket s = new ServerSocket(8080);
        System.out.println("Server started");
        try {
            while (true) {
                double a = 0.0;
                double resultClient = 0.0;
                for (int i = 0; i < 2; i++) {
                    Socket socket = s.accept();
                    try {
                        ServerOneJabber soj = new ServerOneJabber(socket);
                        soj.start();
                        soj.join();
                        resultClient += soj.getResult();
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                        out.println(resultClient);
                        if (i == 1) {
                            System.out.println("Нижний порог: " + a + " - Верхний порог: " + soj.getB() + 
                                " - Шаг: " + soj.getN() + " - Результат: " + resultClient);
                        }
                        else {
                            a = soj.getA();
                        }
                    }
                    finally {
                        socket.close();
                    }
                }
            }
        }
        finally {
            s.close();
        }
    }
}
