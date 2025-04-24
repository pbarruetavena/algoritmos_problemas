
package br.cefetmg.inf.listas.lista05.b1245;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, tam, botas = 0;
        char lado;
        int vetBotas[] = new int[61];
        
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            botas = 0;
            for(int k = 0; k < 61; k++)
                vetBotas[k] = 0;
            
            n = in.nextInt();
            for(int i = 0; i < n; i++) {
                
                
                tam = in.nextInt();
                lado = in.next().charAt(0);
                
                if(lado == 'E') {
                    if(vetBotas[tam] >= 1) {
                        vetBotas[tam]--;
                        botas++;
                    }
                } else {
                    if(vetBotas[tam] <= -1) {
                        vetBotas[tam]++;
                        botas++;
                    }
                }
            }
            
            System.out.println(botas);
        }
        
    }
}
