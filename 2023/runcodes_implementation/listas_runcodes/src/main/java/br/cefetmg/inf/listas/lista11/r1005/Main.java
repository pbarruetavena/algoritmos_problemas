
package br.cefetmg.inf.listas.lista11.r1005;

import java.util.Scanner;

abstract class Pilha {
    private int tamanho;    
    
    class No {
        private final No proximo;
        private final Object valor;

        public No(No proximo, Object item) {
            this.proximo = proximo;
            valor = item;
        }

        public No getProximo() {
            return proximo;
        }

        public Object getItem() {
            return valor;
        }
    }
    
    protected void setTamanho(int n) {
       tamanho = n; 
    }
    
    protected void incTamanho(int incremento) {
        tamanho += incremento;
    }
    
    /**
     * Adiciona <code>item</code> no final da pilha.
     */
    public abstract void empilhar(Object item);
    
    /**
     * Remove um <code>item</code> no topo da pilha.
     * return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
    */
    public abstract Object desempilhar();
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    public abstract Object getItem();
    
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
        return tamanho == 0;
    }
    
    /**
     * Retorna um array contendo todos os itens da pilha.
     * @return um array com todos os elementos da pilha <code>null</code> se a pilha estiver vazia.
     */
    public abstract Object[] toArray();
}



class PilhaEncadeada extends Pilha{
    private No topo;
    
    public PilhaEncadeada() {
        topo = null;
        this.setTamanho(0);
    }
            
    /**
     * Adiciona <code>item</code> no final da pilha.
     */
    public void empilhar(Object item) {
        No novoNo = new No(topo, item);
        topo = novoNo;
        this.incTamanho(1);
    }
    
    /**
     * Remove um <code>item</code> no topo da pilha.
     * return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
    */
    public Object desempilhar() {
        if(topo == null)
            return null;
        
        Object valor = topo.getItem();
        topo = topo.getProximo();
        this.incTamanho(-1);
        
        return valor;
    }
    
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    public Object getItem() {
        if(topo == null)
            return null;
        
        return topo.getItem();
    }
    
    /**
     * Retorna um array contendo todos os itens da pilha.
     * @return um array com todos os elementos da pilha <code>null</code> se a pilha estiver vazia.
     */
    public Object[] toArray() {
        if(topo == null)
            return null;
        
        Object vetor[] = new Integer[this.tamanho()];
        No ptr = topo;
        
        for(int i = 0; i < this.tamanho(); i++) {
            vetor[i] = ptr.getItem();
            ptr = ptr.getProximo();
        }
        
        return vetor;
    }
}



class PilhaArray extends Pilha {
    private Object[] array;
    private int tamanhoMax;
    
    public PilhaArray() {
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
    
    public void empilhar(Object item) {
        if(tamanho() >= tamanhoMax)
            realocar(2*tamanhoMax);
        
        array[tamanho()] = item;
        incTamanho(1);
        return;
    }
    
    public Object desempilhar() {
        if(tamanho() == 0)
            return null;
        
        Object aux = array[tamanho()-1];
        array[tamanho()-1] = null;
        incTamanho(-1);
        return aux;
    }
    
    public Object getItem() {
        if(tamanho() == 0)
            return null;
        
        return array[tamanho()-1];
    }
    
    public Object[] toArray() {
        Object[] arrayRetorno = new Object[tamanho()];
        
        for(int i = 0; i < tamanho(); i++) {
            arrayRetorno[i] = array[tamanho()-i-1];
        }
        return arrayRetorno;
    }
    
}

class Main {

    private Pilha pilha;

    public Main(Pilha pilha) {
        this.pilha = pilha;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main;
        String token;
        Integer valor;
        
        token = in.next();

        if (token.equals("pe"))
            main = new Main(new PilhaEncadeada());
        else
            main = new Main(new PilhaArray());

        while (!token.equals("Q")) {
            switch(token) {
                case "E":
                    valor = in.nextInt();
                    main.pilha.empilhar(valor);
                    break;
                case "D":
                    valor = (Integer) main.pilha.desempilhar();
                    if (valor == null)
                        System.out.println("NenhumItemException");
                    break;
                case "G":
                    valor = (Integer) main.pilha.getItem();
                    if (valor != null)
                        System.out.println(valor);
                    else
                        System.out.println("NenhumItemException");
                    break;
                case "T":
                    System.out.println(main.pilha.tamanho());
                    break;
                case "V":
                    System.out.println(main.pilha.vazia());
                    break;
                case "P":
                    Object valores[] = main.pilha.toArray();
                    if (valores != null) {
                        for(Object item: valores)
                            System.out.println(item);
                    }
                    break;
            }
            token = in.next();
        }
    }
}
