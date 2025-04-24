package br.cefetmg.inf.listas.lista04.b1094;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int exp, qr, qs, qc, quant = 0, tot;
        qr = qs = qc = tot = 0;
        char tipo;
        double pc, ps, pr;

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        exp = input.nextInt();

        for(int i = 0; i < exp; i++){
            quant = input.nextInt();
            tipo = input.next().charAt(0);
            
            if(tipo == 'C') {
                qc+=quant;
            } else if(tipo == 'S') {
                qs+=quant;
            } else if(tipo == 'R') {
                qr+=quant;
            }
            tot+=quant;
        }
        System.out.println("Total: " + tot + " cobaias");
        System.out.println("Total de coelhos: " + qc);
        System.out.println("Total de ratos: " + qr);
        System.out.println("Total de sapos: " + qs);

        pc = 100.0 * qc / tot;
        pr = 100.0 * qr / tot;
        ps = 100.0 * qs / tot;

        System.out.printf("Percentual de coelhos: %.2f %%\n", pc);
        System.out.printf("Percentual de ratos: %.2f %%\n", pr);
        System.out.printf("Percentual de sapos: %.2f %%\n", ps);
    }
    
}
