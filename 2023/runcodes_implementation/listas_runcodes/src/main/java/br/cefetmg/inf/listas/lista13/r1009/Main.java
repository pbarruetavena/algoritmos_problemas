
package br.cefetmg.inf.listas.lista13.r1009;

import java.util.Scanner;


class NenhumItemException extends RuntimeException {
    public NenhumItemException() {
        super("NenhumItemException");
    }
}

class PosicaoInvalidaException extends RuntimeException {
    public PosicaoInvalidaException() {
        super("PosicaoInvalidaException");
    }
}

interface ListaCommon {
    Object getItem( );  // throws NenhumItemException
    int tamanho();
    boolean vazia();
    Object[] toArray(); // throws NenhumItemException
}

interface ListaBasica extends ListaCommon {
    void adicionar(Object item);
    Object remover();   // throws NenhumItemException
}

interface Pilha extends ListaCommon {
    void empilhar(Object item);
    Object desempilhar();       // throws NenhumItemException   
}

class PilhaEncadeada implements Pilha {
    private No topo;
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
    
    public PilhaEncadeada() {
        topo = null;
        tamanho = 0;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    @Override
    public boolean vazia() {
        return tamanho == 0;
    }
            
    /**
     * Adiciona <code>item</code> no final da pilha.
     */
    @Override
    public void empilhar(Object item) {
        No novoNo = new No(topo, item);
        topo = novoNo;
        tamanho++;
    }
    
    /**
     * Remove um <code>item</code> no topo da pilha.
     * return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
    */
    @Override
    public Object desempilhar() {
        if(topo == null){
            throw new NenhumItemException();
        }
        
        Object valor = topo.getItem();
        topo = topo.getProximo();
        tamanho--;
        
        return valor;
    }
    
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    @Override
    public Object getItem() {
        if(topo == null){
            throw new NenhumItemException();
        }
        return topo.getItem();
    }
    
    /**
     * Retorna um array contendo todos os itens da pilha.
     * @return um array com todos os elementos da pilha <code>null</code> se a pilha estiver vazia.
     */
    @Override
    public Object[] toArray() {
        if(topo == null)
            return null;
        
        Object vetor[] = new Integer[tamanho];
        No ptr = topo;
        
        for(int i = 0; i < tamanho; i++) {
            vetor[i] = ptr.getItem();
            ptr = ptr.getProximo();
        }
        
        return vetor;
    }
}

class PilhaArray implements Pilha {
    private Object[] array;
    private int tamanhoMax;
    private int tamanho;
    
    public PilhaArray() {
        tamanhoMax = 15;
        array = new Object[tamanhoMax];
        tamanho = 0;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    @Override
    public boolean vazia() {
        return tamanho == 0;
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
    
    @Override
    public void empilhar(Object item) {
        if(tamanho >= tamanhoMax)
            realocar(2*tamanhoMax);
        
        array[tamanho] = item;
        tamanho++;
        return;
    }
    
    @Override
    public Object desempilhar() {
        if(tamanho == 0){
            throw new NenhumItemException();
        }
        
        Object aux = array[tamanho-1];
        array[tamanho-1] = null;
        tamanho--;
        return aux;
    }
    
    @Override
    public Object getItem() {
        if(tamanho == 0){
            throw new NenhumItemException();
        }
        return array[tamanho-1];
    }
    
    @Override
    public Object[] toArray() {
        Object[] arrayRetorno = new Object[tamanho];
        
        for(int i = 0; i < tamanho; i++) {
            arrayRetorno[i] = array[tamanho-i-1];
        }
        return arrayRetorno;
    }
}

interface Fila extends ListaCommon {
    void enfileirar(Object item);
    Object desenfileirar();     // throws NenhumItemException
}

class FilaEncadeada implements Fila {
    private No fim;
    private No inicio;
    private int tamanho;
    
    public FilaEncadeada() {
        fim = null;
        inicio = null;
        tamanho = 0;
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
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    @Override
    public boolean vazia() {
        return tamanho == 0;
    }
    
    /**
     * Adiciona @code{item} no final da fila.
     */
    @Override
    public void enfileirar(Object item) {
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
    @Override
    public Object desenfileirar() {
        if(tamanho == 0) {
            throw new NenhumItemException();
        }
        
        Object val = inicio.getItem();
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
    @Override
    public Object getItem() {
        if(inicio == null) {
            throw new NenhumItemException();
        }
        return inicio.getItem();
    }
    
    /**
     * Retorna um array contendo todos os elementos da fila.
     * @return um array com todos os elementos da fila @code{null} se a fila estiver vazia.
     */
    @Override
    public Object[] toArray() {
        if(tamanho == 0)
            return null;
        
        Object[] array = new Integer[tamanho];
        No no = inicio;
        
        for(int i = 0; i < tamanho; i++) {
            array[i] = no.getItem();
            no = no.getProximo();
        }
        
        return array;
    }
}

class FilaArray implements Fila {
    private Object[] array;
    private int tamanhoMax;
    private int tamanho;
    
    public FilaArray() {
        tamanhoMax = 15;
        array = new Object[tamanhoMax];
        tamanho = 0;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    @Override
    public boolean vazia() {
        return tamanho == 0;
    }
    
    private void realocar(int novoMax) {
        if(tamanhoMax > novoMax)
            return;
        
        Object[] novoArray = new Object[novoMax];
        System.arraycopy(array, 0, novoArray, 0, tamanhoMax);
        
        tamanhoMax = novoMax;
        array = novoArray;
    }
    
    @Override
    public void enfileirar(Object item) {
        if(tamanho >= tamanhoMax) {
            realocar(tamanho*2);
        }
        
        array[tamanho] = item;
        tamanho++;
    }
    
    @Override
    public Object desenfileirar() {
        if(tamanho==0){
            throw new NenhumItemException();
        }
            
        Object aux = array[0];
        for(int i = 0; i < tamanho-1; i++)
            array[i] = array[i+1];
        
        tamanho--;
        return aux;
    }
    
    @Override
    public Object getItem() {
        if(tamanho == 0){
            throw new NenhumItemException();
        }
        return array[0];
    }
    
    @Override
    public Object[] toArray() {
        if(tamanho == 0)
            return null;
        
        Object arrReturn[] = new Object[tamanho];
        for(int i = 0; i < tamanho; i++)
            arrReturn[i] = array[i];
        return arrReturn;
    }
}
// FAZER O MESMO QUE FOI FEITO PARA Pilha

interface Lista extends ListaBasica {
    void adicionarInicio(Object item);
    void adicionarFim(Object item);
    void adicionar(Object item, int posicao);   // throws PosicaoInvalidaException
    Object removerInicio();         // throws NenhumItemException
    Object removerFim();            // throws NenhumItemException
    Object remover(int posicao);    // throws NenhumItemException, PosicaoInvalidaExeption
    Object getItem(int posicao);    // throws PosicaoInvalidaExeption
}

class ListaEncadeada implements Lista {
    private No inicio;
    private int tamanho;
    
    class No {
        private final Object valor;
        private No proximo;

        public No(Object valor) {
            this.valor = valor;
            proximo = null;
        }

        public Object getValor() {
            return valor;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No prox) {
            this.proximo = prox;
        }

        public boolean temProximo() {
            return proximo != null;
        }
    }
    
    @Override
    public boolean vazia() {
        return tamanho == 0;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    
    public ListaEncadeada() {
        inicio = null;
    }
    
    @Override
    public void adicionar(Object valor) {
        adicionarFim(valor);
    }
    
    @Override
    public void adicionarFim(Object valor) {
        No novoNo = new No(valor);
        No aux = inicio;
        tamanho++;
        
        if(tamanho == 1) {
            inicio = novoNo;
            return;
        }
        
        while(aux.temProximo()) {
            aux = aux.getProximo();
        }
        aux.setProximo(novoNo);
    }
    
    @Override
    public void adicionarInicio(Object valor) {
        No novoNo = new No(valor);
        
        tamanho++;
        novoNo.setProximo(inicio);
        inicio = novoNo;
    }
    
    @Override
    public void adicionar(Object valor, int posicao) {
        if(posicao < 0 || posicao > tamanho) {
            throw new PosicaoInvalidaException();
        }
        
        No novoNo = new No(valor);
        No aux = inicio;
        tamanho++;        
        
        if(tamanho == 1) {
            inicio = novoNo;
            return;
        }
        
        if(posicao == 0) {
            novoNo.setProximo(inicio);
            inicio = novoNo;
            return;
        }
        
        for(int i = 0; i < posicao-1; i++) {
            aux = aux.getProximo();
        }
        novoNo.setProximo(aux.getProximo());
        aux.setProximo(novoNo);
    }
    
    @Override
    public Object remover() {
        return removerInicio();
    }
    
    @Override
    public Object removerInicio() {
        if(tamanho == 0) {
            throw new NenhumItemException();
        }
        
        Object valor = inicio.getValor();
        inicio = inicio.getProximo();
        tamanho--;
        return valor;
    }
    
    @Override
    public Object removerFim() {
        if(tamanho == 0) {
            throw new NenhumItemException();
        }
        
        tamanho--;
        Object valor = inicio.getValor();
        No aux = inicio;
        
        if(tamanho == 0) {
            inicio = null;
            return valor;
        }
        
        while(aux.getProximo().temProximo()) {
            aux = aux.getProximo();
        }
        valor = aux.getProximo().getValor();
        aux.setProximo(null);
        return valor;
    }
    
    @Override
    public Object remover(int posicao) {
        if(tamanho == 0){
            throw new NenhumItemException();
        }
        if(posicao < 0 || posicao >= tamanho) {
            return null;
        }
        
        Object valor = inicio.getValor();
        No aux = inicio;
        tamanho--;
        
        if(tamanho == 0) {
            inicio = null;
            return valor;
        }
        
        if(posicao == 0) {
            inicio = inicio.getProximo();
        }
        
        for(int i = 0; i < posicao-1; i++) {
            aux = aux.getProximo();
        }
        valor = aux.getProximo().getValor();
        aux.setProximo(aux.getProximo().getProximo());
        
        return valor;
    }
    
    @Override
    public Object getItem() {
        return getItem(0);
    }
    
    @Override
    public Object getItem(int posicao) {
        if(tamanho == 0) {
            throw new NenhumItemException();
        }
        if(posicao < 0 || posicao >= tamanho) {
            throw new PosicaoInvalidaException();
        }
        
        Object valor;
        No aux = inicio;
        
        for(int i = 0; i < posicao; i++)
            aux = aux.getProximo();
        
        valor = aux.getValor();
        return valor;
    }
    
    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.tamanho];
        No aux = inicio;
        int i = 0;
        while(aux != null) {
            array[i] = aux.getValor();
            aux = aux.getProximo();
            i++;
        }
        
        return array;
    }
}

class ListaArray implements Lista {
    private Object[] array;
    private int tamMax;
    private int tamanho;
    
    public ListaArray() {
        tamMax = 20;
        array = new Object[tamMax];
    }
    
    private void realocar(int novoMax) {
        if(tamMax > novoMax)
            return;
        
        Object[] novoArray = new Object[novoMax];
        for(int i = 0; i < tamMax; i++) {
            novoArray[i] = array[i];
        }
        tamMax = novoMax;
        array = novoArray;
    }
    
    @Override
    public boolean vazia() {
        return tamanho == 0;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    
    @Override
    public void adicionar(Object valor) {
        adicionarFim(valor);
    }
    
    @Override
    public void adicionarFim(Object item) {
        if(tamanho >= tamMax)
            realocar(tamanho*2);
        array[tamanho] = item;
        tamanho++;
    }
    
    @Override
    public void adicionarInicio(Object item) {
        if(tamanho >= tamMax)
            realocar(tamanho*2);
        for(int i = tamanho; i > 0; i--)
            array[i] = array[i-1];
        array[0] = item;
        tamanho++;
    }
    
    @Override
    public void adicionar(Object valor, int posicao) {
        if(posicao < 0 || posicao > tamanho) {
            throw new PosicaoInvalidaException();
        }
        
        if(tamanho >= tamMax)
            realocar(tamanho*2);
        for(int i = tamanho; i > posicao; i--) 
            array[i] = array[i-1];
        array[posicao] = valor;
        tamanho++;
    }
    
    @Override
    public Object remover() {
        return removerInicio();
    }
    
    @Override
    public Object removerInicio() {
        if(tamanho==0) {
            throw new NenhumItemException();
        }
        Object aux = array[0];
        for(int i = 0; i < tamanho-1; i++)
            array[i] = array[i+1];
        array[tamanho-1] = null;
        tamanho--;
        return aux;
    }
    
    @Override
    public Object removerFim() {
        if(vazia()) {
            throw new NenhumItemException();
        }
        
        tamanho--;
        Object aux = array[tamanho-1];
        array[tamanho-1] = null;
        return aux;
    }
    
    @Override
    public Object remover(int posicao) {
        if(vazia()) {
            throw new NenhumItemException();
        }
        if(posicao < 0 || posicao >= tamanho) {
            throw new PosicaoInvalidaException();
        }
        Object aux = array[posicao];
        for(int i = posicao; i < tamanho-1; i++)
            array[i] = array[i+1];
        array[tamanho-1] = null;
        tamanho--;
        return aux;
    }
    
    @Override
    public Object getItem() {
        return getItem(0);
    }
    
    @Override
    public Object getItem(int posicao) {
        if(vazia()) {
            throw new NenhumItemException();
        }
        if(posicao < 0 || posicao >= tamanho) {
            throw new PosicaoInvalidaException();
        }
        return array[posicao];
    }
    
    @Override
    public Object[] toArray() {
        if(vazia())
            return null;
        Object[] novoArr = new Object[tamanho];
        for(int i = 0; i < tamanho; i++)
            novoArr[i] = array[i];
        return novoArr;
    }
}

class PilhaAdapter implements ListaBasica {
    
    private final Pilha pilha;

    public PilhaAdapter(Pilha pilha) {
        this.pilha = pilha;
    }    
    
    @Override
    public void adicionar(Object item) {
        pilha.empilhar(item);
    }

    @Override
    public Object remover() {
        return pilha.desempilhar();
    }

    @Override
    public Object getItem() {
        return pilha.getItem();
    }

    @Override
    public int tamanho() {
        return pilha.tamanho();
    }

    @Override
    public boolean vazia() {
        return pilha.vazia();
    }

    @Override
    public Object[] toArray() {
        return pilha.toArray();
    }    
}
class FilaAdapter implements ListaBasica {
    
    private final Fila fila;

    public FilaAdapter(Fila fila) {
        this.fila = fila;
    }    
    
    @Override
    public void adicionar(Object item) {
        fila.enfileirar(item);
    }

    @Override
    public Object remover() {
        return fila.desenfileirar();
    }

    @Override
    public Object getItem() {
        return fila.getItem();
    }

    @Override
    public int tamanho() {
        return fila.tamanho();
    }

    @Override
    public boolean vazia() {
        return fila.vazia();
    }

    @Override
    public Object[] toArray() {
        return fila.toArray();
    }    
}

class Main {

    private final ListaBasica lista;
    
    public Main(ListaBasica lista) {
        this.lista = lista;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main;
        String token;
        Integer valor;
        
        token = in.next();
        
        switch (token) {
            case "PE": main = new Main(new PilhaAdapter(new PilhaEncadeada())); break;
            case "PA": main = new Main(new PilhaAdapter(new PilhaArray())); break;
            case "FE": main = new Main(new FilaAdapter(new FilaEncadeada())); break;
            case "FA": main = new Main(new FilaAdapter(new FilaArray())); break;
            case "LE": main = new Main(new ListaEncadeada()); break;
            default: main = new Main(new ListaArray());
        }
        
        while (!token.equals("Q")) {
            switch(token) {
                case "A":   // adiciona um item
                    valor = in.nextInt();
                    main.lista.adicionar(valor);
                    break;      
                case "R":   // remove um item
                    try {
                        main.lista.remover();
                    } catch(NenhumItemException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "G":   // retorna um item, sem remover
                    try {
                        valor = (Integer) main.lista.getItem();
                        System.out.println(valor);
                    } catch (NenhumItemException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "T":   // numero de itens na estrutura
                    System.out.println(main.lista.tamanho());
                    break;
                case "V":   // indica se a estrutura esta vazia
                    System.out.println(main.lista.vazia());
                    break;
                case "P":   // imprime os itens da estrutura, sem remover
                    try {
                        Object valores[] = main.lista.toArray();
                        if (valores != null) 
                            for(Object item: valores)
                                System.out.println(item);
                    } catch (NenhumItemException e) {
                        System.out.println(e.getMessage());
                    }    
                    break;
            }
            token = in.next();
        }
        in.close();
    }
}
