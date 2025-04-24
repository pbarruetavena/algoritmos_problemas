
package br.cefetmg.inf.listas.lista08.r1003;

import java.util.Scanner;

class No {
    private final Integer valor;
    private No proximo;
    
    public No(Integer valor) {
        this.valor = valor;
        proximo = null;
    }
    
    public Integer getItem() {
        return valor;
    }
    
    public No getProximo() {
        return proximo;
    }
    
    public void setProximo(No prox) {
        proximo = prox;
    }
}

class Fila {
    private No fim;
    private No inicio;
    private int tamanho;
    
    public Fila() {
        tamanho = 0;
        fim = null;
        inicio = null;
    }
    
    /**
     * Adiciona @code{item} no final da fila.
     */
    void enfileirar(Integer item) {
        No novoNo = new No(item);
        tamanho++;
        
        if(tamanho == 1) {
            inicio = fim = novoNo;
            return;
        }
        
        fim.setProximo(novoNo);
        fim = novoNo;
    }
    
    /**
     * Remove um @code{item} no início da fila.
     * return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
    */
    Integer desenfileirar() {
        if(tamanho == 0) {
            return null;
        }
        
        Integer val = inicio.getItem();
        tamanho--;
        
        if(tamanho == 0) {
            inicio = fim = null;
            return val;
        }
        
        inicio = inicio.getProximo();
        
        return val;
    }
    
    /**
     * Recupera @code{item} no início da fila, sem remover.
     * @return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
     */
    Integer getItem() {
        if(inicio == null) {
            return null;
        }
        
        return inicio.getItem();
        
    }
    
    /**
     * Recupera número de itens na fila.
     * @return Número de itens mantidos na fila.
     */
    int tamanho() {
        return tamanho;
    }
    
    /**
     * Retorna @code{true} se a fila não possuir qualquer @code{item}.
     * @return @code{true} se a fila não possuir qualquer @code{item}, 
     * ou @code{false} se possuir.
     */
    boolean estaVazia() {
        return tamanho == 0;
    }
    
    /**
     * Retorna um array contendo todos os elementos da fila.
     * @return um array com todos os elementos da fila @code{null} se a fila estiver vazia.
     */
    Integer[] toArray() {
        if(tamanho == 0)
            return null;
        
        Integer[] array = new Integer[tamanho];
        No no = inicio;
        
        for(int i = 0; i < tamanho; i++) {
            array[i] = no.getItem();
            no = no.getProximo();
        }
        
        return array;
    }
}


class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Fila fila = new Fila();
        String token;
        Integer valor;
        
        token = in.next();
        while (!token.equals("Q")) {
            switch(token) {
                case "AF":
                    valor = in.nextInt();
                    fila.enfileirar(valor);
                    break;
                case "RI":
                    valor = fila.desenfileirar();
                    if (valor == null)
                        System.out.println("NenhumItemException");
                    break;
                case "G":
                    valor = fila.getItem();
                    if (valor != null)
                        System.out.println(valor);
                    else
                        System.out.println("NenhumItemException");
                    break;
                case "T":
                    System.out.println(fila.tamanho());
                    break;
                case "V":
                    System.out.println(fila.estaVazia());
                    break;
                case "P":
                    Integer valores[] = fila.toArray();
                    if (valores != null) {
                        for(Integer item: valores)
                            System.out.println(item);
                    }
                    break;
            }
            token = in.next();
        }
        
    }
}
