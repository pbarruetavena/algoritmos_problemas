
package br.cefetmg.inf.listas.lista01.b1018;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        int[] valores = {100, 50, 20, 10, 5, 2, 1};
        int[] notas = new int[7];
        int valor;
        
        Scanner input = new Scanner(System.in);
        valor = input.nextInt();
        
        System.out.println(valor);
        
        for(int i = 0; i < 7; i++) {
            notas[i] = valor / valores[i];
            valor = valor % valores[i];
        }
        
        for(int i = 0; i < 7; i++) {
            System.out.println(notas[i] + " nota(s) de R$ " + valores[i] + ",00");
        }
    }
}