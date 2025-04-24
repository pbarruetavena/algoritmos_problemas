
package br.cefetmg.inf.listas.lista02.b1016;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int tempo, dist;
        
        Scanner input = new Scanner(System.in);
        dist = input.nextInt();
        
        tempo = dist * 2;
        
        System.out.println(tempo + " minutos");
        
    }
}