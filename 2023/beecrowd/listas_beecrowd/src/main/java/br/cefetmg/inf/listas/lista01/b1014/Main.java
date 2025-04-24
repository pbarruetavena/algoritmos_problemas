
package br.cefetmg.inf.listas.lista01.b1014;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        int distancia;
        double combustivel, consumo;
        
        Scanner input = new Scanner(System.in);
        distancia = input.nextInt();
        combustivel = input.nextDouble();
        
        consumo = distancia / combustivel;
        
        System.out.printf("%.3f km/l\n", consumo);
        
    }
}