/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab1;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author iljak
 */
public class Client {
    
    private double a;
    private double b;
    private double n;
    private double result;
    Client(double a, double b, double n){
        this.a = a;
        this.b = b;
        this.n = n;
    }
    double getResult(){
        return result;
    }
    
    public void main() throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, 8080);
        try{
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
               new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(a);
            out.println(b);
            out.println(n);
            MyThread thread1 = new MyThread(a, ((b-a)/2) + a, n);
            MyThread thread2 = new MyThread(((b-a)/2) + a, b, n);
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            result = thread1.getResult() + thread2.getResult();
            out.println(result);
            result = Double.parseDouble(in.readLine());
        }
        finally{
            System.out.println("closing...");
            socket.close();
        }
    }
}
