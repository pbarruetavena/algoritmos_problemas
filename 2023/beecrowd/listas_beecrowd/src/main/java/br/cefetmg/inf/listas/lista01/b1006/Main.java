
package br.cefetmg.inf.listas.lista01.b1006;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        double a, b, c, media;
        
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
        
        media = (2*a + 3*b + 5*c) / 10;
        
        System.out.printf("MEDIA = %.1f\n", media);
    }
}