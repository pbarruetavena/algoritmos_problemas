package br.cefetmg.inf.listas.lista04.b1985;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int n, prod, quant;
        double tot = 0.0, preco;

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);

        n = input.nextInt();

        for(int i = 0; i < n; i++) {
            prod = input.nextInt();
            quant = input.nextInt();

            preco = prod - 1000.0 + 0.5;
            tot += preco * quant;
        }

        System.out.printf("%.2f\n", tot);
    }
}
