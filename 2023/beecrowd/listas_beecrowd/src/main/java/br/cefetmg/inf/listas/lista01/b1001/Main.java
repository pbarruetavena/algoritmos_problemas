package br.cefetmg.inf.listas.lista01.b1001;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int a, b, x;
        
        Scanner input = new Scanner(System.in);
        
        a = input.nextInt();
        b = input.nextInt();
        
        x = a + b;
        
        System.out.println("X = " + x);
    }
}