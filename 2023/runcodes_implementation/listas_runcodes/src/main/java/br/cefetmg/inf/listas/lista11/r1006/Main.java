
package br.cefetmg.inf.listas.lista11.r1006;

import java.util.Scanner;

abstract class Fila {
    private int tamanho;
    
    Fila() {
        tamanho = 0;
    }
    
    protected void incTamanho(int inc) {
        tamanho += inc;
    }
    
    /**
     * Recupera número de itens na fila.
     * @return Número de itens mantidos na fila.
     */
    public int tamanho() {
        return tamanho;
    }
    
    /**
     * Retorna @code{true} se a fila não possuir qualquer @code{item}.
     * @return @code{true} se a fila não possuir qualquer @code{item}, 
     * ou @code{false} se possuir.
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }
    
    /**
     * Adiciona @code{item} no final da fila.
     */
    public abstract void enfileirar(Object item);
    
    /**
     * Remove um @code{item} no início da fila.
     * return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
    */
    public abstract Object desenfileirar();
    
    /**
     * Recupera @code{item} no início da fila, sem remover.
     * @return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
     */
    public abstract Object getItem();
    
    /**
     * Retorna um array contendo todos os elementos da fila.
     * @return um array com todos os elementos da fila @code{null} se a fila estiver vazia.
     */
    public abstract Object[] toArray();
}

class FilaEncadeada extends Fila {
    private No fim;
    private No inicio;
    
    public FilaEncadeada() {
        fim = null;
        inicio = null;
    }
    
    class No {
        private final Object valor;
        private No proximo;

        public No(Object valor) {
            this.valor = valor;
            proximo = null;
        }

        public Object getItem() {
            return valor;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No prox) {
            proximo = prox;
        }
    }
    
    /**
     * Adiciona @code{item} no final da fila.
     */
    public void enfileirar(Object item) {
        No novoNo = new No(item);
        incTamanho(1);
        
        if(tamanho() == 1) {
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
    public Object desenfileirar() {
        if(tamanho() == 0) {
            return null;
        }
        
        Object val = inicio.getItem();
        incTamanho(-1);
        
        if(tamanho() == 0) {
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
    public Object getItem() {
        if(inicio == null) {
            return null;
        }
        
        return inicio.getItem();
    }
    
    /**
     * Retorna um array contendo todos os elementos da fila.
     * @return um array com todos os elementos da fila @code{null} se a fila estiver vazia.
     */
    public Object[] toArray() {
        if(tamanho() == 0)
            return null;
        
        Object[] array = new Integer[tamanho()];
        No no = inicio;
        
        for(int i = 0; i < tamanho(); i++) {
            array[i] = no.getItem();
            no = no.getProximo();
        }
        
        return array;
    }
}
class FilaArray extends Fila {
    private Object[] array;
    private int tamanhoMax;
    
    public FilaArray() {
        tamanhoMax = 15;
        array = new Object[tamanhoMax];
    }
    
    private void realocar(int novoMax) {
        if(tamanhoMax > novoMax)
            return;
        
        Object[] novoArray = new Object[novoMax];
        for(int i = 0; i < tamanhoMax; i++) {
            novoArray[i] = array[i];
        }
        
        tamanhoMax = novoMax;
        array = novoArray;
    }
    
    public void enfileirar(Object item) {
        if(tamanho() >= tamanhoMax) {
            realocar(tamanho()*2);
        }
        
        array[tamanho()] = item;
        incTamanho(1);
    }
    
    public Object desenfileirar() {
        if(estaVazia())
            return null;
        
        Object aux = array[0];
        for(int i = 0; i < tamanho()-1; i++)
            array[i] = array[i+1];
        
        incTamanho(-1);
        return aux;
    }
    
    public Object getItem() {
        if(estaVazia())
            return null;
        
        return array[0];
    }
    
    public Object[] toArray() {
        if(estaVazia())
            return null;
        
        Object arrReturn[] = new Object[tamanho()];
        for(int i = 0; i < tamanho(); i++)
            arrReturn[i] = array[i];
        return arrReturn;
    }
}

class Main {

    private Fila fila;

    public Main(Fila fila){
        this.fila = fila;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main;
        String token;
        Integer valor;
        
        token = in.next();
        if (token.equals("FE"))
            main = new Main(new FilaEncadeada());
        else
            main = new Main(new FilaArray());

        
        while (!token.equals("Q")) {
            switch(token) {
                case "AF":
                    valor = in.nextInt();
                    main.fila.enfileirar(valor);
                    break;
                case "RI":
                    valor = (Integer) main.fila.desenfileirar();
                    if (valor == null)
                        System.out.println("NenhumItemException");
                    break;
                case "G":
                    valor = (Integer) main.fila.getItem();
                    if (valor != null)
                        System.out.println(valor);
                    else
                        System.out.println("NenhumItemException");
                    break;
                case "T":
                    System.out.println(main.fila.tamanho());
                    break;
                case "V":
                    System.out.println(main.fila.estaVazia());
                    break;
                case "P":
                    Object valores[] = main.fila.toArray();
                    if (valores != null) {
                        for(Object item: valores)
                            System.out.println((Integer) item);
                    }
                    break;
            }
            token = in.next();
        }
        
    }
}
