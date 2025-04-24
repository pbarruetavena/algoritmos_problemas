
package br.cefetmg.inf.listas.lista05.b2163;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] matriz;
        int n, m;
        
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        
        matriz = new int[n][m];
        
        for(int i = 0; i < n; i++)
            for(int k = 0; k < m; k++)
                matriz[i][k] = input.nextInt();
        
        for(int i = 1; i < n - 1; i++)
            for(int k = 1; k < m - 1; k++)
                if(matriz[i][k] == 42) {
                    if(matriz[i-1][k-1]==7 && matriz[i-1][k]==7 && matriz[i-1][k+1]==7 && matriz[i][k-1]==7 && matriz[i][k+1]==7 && matriz[i+1][k-1]==7 && matriz[i+1][k]==7 && matriz[i+1][k+1]==7){
                        i++; 
                        k++;
                        System.out.println(i + " " + k);
                        return;
                    }
                }
        
    }
}
