package br.cefetmg.inf.listas.lista04.b1134;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean ver = true;
        int n, k, q1, q2, q3, q4;
        q1 = q2 = q3 = q4 = 0;
        
        Scanner input = new Scanner(System.in);
        
        while(ver) {
            k = input.nextInt();
            
            switch (k) {
                case 1: q1++; break;
                case 2: q2++; break;
                case 3: q3++; break;
                case 4: ver = false; break;
            }
        }
        
        System.out.println("MUITO OBRIGADO");
        System.out.println("Alcool: " + q1);
        System.out.println("Gasolina: " + q2);
        System.out.println("Diesel: " + q3);
    }
}
