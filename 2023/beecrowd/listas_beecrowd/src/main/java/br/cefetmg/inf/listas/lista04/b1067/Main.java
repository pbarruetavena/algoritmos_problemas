package br.cefetmg.inf.listas.lista04.b1067;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num;
        
        Scanner input = new Scanner(System.in);
        
        num = input.nextInt();
        
        for(int i = 1; i <= num; i+=2) {
            System.out.println(i);
        }
        
    }
}
