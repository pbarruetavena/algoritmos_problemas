
package br.cefetmg.inf.listas.lista08.r1001;

import java.util.Scanner;

class No {
    private final No proximo;
    private final Integer valor;
    
    public No(No proximo, Integer item) {
        this.proximo = proximo;
        valor = item;
    }
    
    public No getProximo() {
        return proximo;
    }
    
    public Integer getItem() {
        return valor;
    }
}

class Pilha {
    private No topo;
    private int tamanho;
    
    public Pilha() {
        tamanho = 0;
        topo = null;
    }
            
    /**
     * Adiciona <code>item</code> no final da pilha.
     */
    public void empilhar(Integer item) {
        No novoNo = new No(topo, item);
        topo = novoNo;
        tamanho++;
    }
    
    /**
     * Remove um <code>item</code> no topo da pilha.
     * return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
    */
    public Integer desempilhar() {
        if(topo == null)
            return null;
        
        Integer valor = topo.getItem();
        topo = topo.getProximo();
        tamanho--;
        
        return valor;
    }
    
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    public Integer getItem() {
        if(topo == null)
            return null;
        
        return topo.getItem();
    }
    
    /**
     * Recupera número de itens na pilha.
     * @return Número de itens mantidos na pilha.
     */
    public int tamanho() {
        return tamanho;
    }
    
    /**
     * Retorna <code>true</code> se a pilha não possuir qualquer <code>item</code>.
     * @return <code>true</code> se a pilha não possuir qualquer <code>item</code>, 
     * ou <code>false</code> se possuir.
     */
    public boolean vazia() {
        return (this.tamanho() == 0);
    }
    
    /**
     * Retorna um array contendo todos os itens da pilha.
     * @return um array com todos os elementos da pilha <code>null</code> se a pilha estiver vazia.
     */
    public Integer[] toArray() {
        if(topo == null)
            return null;
        
        Integer vetor[] = new Integer[tamanho];
        No ptr = topo;
        
        for(int i = 0; i < tamanho; i++) {
            vetor[i] = ptr.getItem();
            ptr = ptr.getProximo();
        }
        
        return vetor;
    }
}

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Pilha pilha = new Pilha();
        String token;
        Integer valor;
        
        token = in.next();
        while (!token.equals("Q")) {
            switch(token) {
                case "E":
                    valor = in.nextInt();
                    pilha.empilhar(valor);
                    break;
                case "D":
                    valor = pilha.desempilhar();
                    if (valor == null)
                        System.out.println("NenhumItemException");
                    break;
                case "G":
                    valor = pilha.getItem();
                    if (valor != null)
                        System.out.println(valor);
                    else
                        System.out.println("NenhumItemException");
                    break;
                case "T":
                    System.out.println(pilha.tamanho());
                    break;
                case "V":
                    System.out.println(pilha.vazia());
                    break;
                case "P":
                    Integer valores[] = pilha.toArray();
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
