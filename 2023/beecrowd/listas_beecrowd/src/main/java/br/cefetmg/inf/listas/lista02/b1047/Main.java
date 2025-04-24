
package br.cefetmg.inf.listas.lista02.b1047;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int hi, hf, mi, mf, tempo, h, m;
        
        Scanner input = new Scanner(System.in);
        hi = input.nextInt();
        mi = input.nextInt();
        hf = input.nextInt();
        mf = input.nextInt();
        
        tempo = (hf*60 + mf) - (hi*60 + mi);
        tempo = (tempo + 24*60 - 1) % (24*60);
        tempo++;
        
        h = tempo / 60;
        m = tempo % 60;
        
        System.out.println("O JOGO DUROU " + h + " HORA(S) E " + m + " MINUTO(S)");
    }
}