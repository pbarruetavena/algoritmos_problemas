
package br.cefetmg.inf.listas.lista02.b2786;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int l, c, lajota1, lajota2;
        
        Scanner input = new Scanner(System.in);
        l = input.nextInt();
        c = input.nextInt();
        
        lajota1 = (l * c) + ((l - 1)*(c - 1));
        lajota2 = 2*(l - 1) + 2*(c - 1);
        
        System.out.println(lajota1);
        System.out.println(lajota2);
    }
}