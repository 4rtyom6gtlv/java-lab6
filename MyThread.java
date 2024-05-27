/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab1;

/**
 *
 * @author iljak
 */
class MyThread extends Thread{
    private double a;
    private double b;
    private double n;
    private double result = 0.0;

    MyThread(double a, double b, double n){
        this.a = a;
        this.b = b;
        this.n = n;
    } 

    public double getResult () {
        return result;
    }                

    @Override
    public void run(){
        double dx = (b-a)/n;
        for (int i = 1; i < dx; i++){
            if(!((a+n)>b)) {
                result+= (Math.sin(a+n)+Math.sin(a))*n/2;
                a += n;
            }
            else result+= (Math.sin(a)+Math.sin(b))*(b-a)/2;
        }
    }
}

