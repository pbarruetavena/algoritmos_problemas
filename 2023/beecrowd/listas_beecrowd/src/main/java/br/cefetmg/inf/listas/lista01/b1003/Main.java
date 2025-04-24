
package br.cefetmg.inf.listas.lista01.b1003;
 
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
 
        int A, B, soma;
        
        Scanner input = new Scanner(System.in);
        
        A = input.nextInt();
        B = input.nextInt();
        
        soma = A + B;
        
        System.out.println("SOMA = " + soma);
 
    }
 
}