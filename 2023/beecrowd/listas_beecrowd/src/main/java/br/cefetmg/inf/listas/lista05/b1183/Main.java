package br.cefetmg.inf.listas.lista05.b1183;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        
        double soma = 0;
        double[][] matriz = new double[12][12];
        char ope;
        
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        
        ope = input.next().charAt(0);
        
        for(int i = 0; i < 12; i++)
            for(int k = 0; k < 12; k++) 
                matriz[i][k] = input.nextDouble();

        for(int i = 0; i < 12; i++)
            for(int k = i+1; k < 12; k++)
                soma += matriz[i][k];
        
        if(ope == 'M')
            soma = soma / 66;
        
        System.out.printf("%.1f\n", soma);
    }
}
