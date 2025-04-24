
package br.cefetmg.inf.listas.lista07.b1245;

import java.util.Scanner;

class Bota {
    private int tamanho;
    private char lado;
    
    public Bota(int t, char l) {
        tamanho = t;
        lado = l;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public char getLado() {
        return lado;
    }
}

class Inventario {
    private Bota[] botas;
    private int qBotas, k;
    
    public Inventario(int quantBotas) {
        botas = new Bota[quantBotas];
        qBotas = quantBotas;
        k = 0;
    }
    
    public void criaBota(int t, char l) {
        Bota novaBota = new Bota(t, l);
        botas[k] = novaBota;
        k++;
    }
    
    public int calculaPares() {
        int botasE[] = new int[61];
        int botasD[] = new int[61];
        int q = 0, tam;
        
        for(int i = 0; i < qBotas; i++) {
            tam = botas[i].getTamanho();
            if(botas[i].getLado() == 'E'){
                botasE[tam]++;
            } else {
                botasD[tam]++;
            }
        }
        
        for(int i = 30; i < 61; i++) {
            if(botasE[i] < botasD[i])
                q += botasE[i];
            else
                q += botasD[i];
        }
        
        return q;
    }
}

public class Main {
    public static void main(String[] args){
        int tam, q, pares;
        char lado;
        Inventario inventario;
        
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()) {
            q = in.nextInt();
            inventario = new Inventario(q);
            
            for(int k = 0; k < q; k++){
                tam = in.nextInt();
                lado = in.next().charAt(0);
                inventario.criaBota(tam, lado);
            }
            
            pares = inventario.calculaPares();
            System.out.println(pares);
            
        }
    }
}
