
package br.cefetmg.inf.listas.lista06.b1038;

import java.util.Scanner;
import java.util.Locale;

class Lanche {
    double[] precos;
    int codigo;
    Lanche(int codigo) {
        precos = new double[5];
        precos[0] = 4.0;
        precos[1] = 4.5;
        precos[2] = 5.0;
        precos[3] = 2.0;
        precos[4] = 1.5;
        this.codigo = codigo - 1;
    }
    
    double preco() {
        return precos[codigo];
    }
}

class IO {
    Scanner in;
    IO() {
        Locale.setDefault(Locale.US);
        in = new Scanner(System.in);
    }
    
    int lerInteiro() {
        int valor = in.nextInt();
        return valor;
    }
    
    void printTotal(double valor) {
        System.out.printf("Total: R$ %.2f\n", valor);
    }
}

class Calculador {
    double total;
    
    Calculador(Lanche lanche, int quantidade) {
        double precoUnico = lanche.preco();
        total = precoUnico * quantidade;
    }
    
    double precoTotal() {
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        int codigo, quantidade;
        double total;
        
        IO input = new IO();
        codigo = input.lerInteiro();
        quantidade = input.lerInteiro();
        
        Lanche lanche = new Lanche(codigo);
        Calculador calculo = new Calculador(lanche, quantidade);
        total = calculo.precoTotal();
        input.printTotal(total);
    }
}