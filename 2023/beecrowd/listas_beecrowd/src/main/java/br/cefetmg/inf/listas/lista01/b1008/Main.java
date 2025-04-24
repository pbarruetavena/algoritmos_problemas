
package br.cefetmg.inf.listas.lista01.b1008;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        
        int num, hrs;
        double valPHora, salario;
        
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        hrs = input.nextInt();
        valPHora = input.nextDouble();
        
        salario = hrs * valPHora;
        
        System.out.printf("NUMBER = %d\nSALARY = U$ %.2f\n", num, salario);
        
    }
}