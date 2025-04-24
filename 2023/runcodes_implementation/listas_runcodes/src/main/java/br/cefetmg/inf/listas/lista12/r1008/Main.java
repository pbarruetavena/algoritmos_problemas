
package br.cefetmg.inf.listas.lista12.r1008;

import java.util.Scanner;

enum ErroLista {
    INDICE_INVALIDO("PosicaoInvalidaException"),
    VAZIA("NenhumItemException"),
    SEM_ERRO("NenhumErro");
    
    String msgErro;
    
    ErroLista(String msg) {
        msgErro = msg;
    }
    
    public String getMessage() {
        return msgErro;
    }
}

class ErroListaWrapper {
    private ErroLista erro;
    
    public ErroListaWrapper() {
        this(ErroLista.SEM_ERRO);
    }
    
    public ErroListaWrapper(ErroLista erro) {
        this.erro = erro;
    }

    public ErroLista getErro() {
        return erro;
    }

    public void setErro(ErroLista erro) {
        this.erro = erro;
    }
}

interface ILista {
    void adicionar(Object item);
    Object remover(ErroListaWrapper erro);
    Object getItem(ErroListaWrapper erro);
    int tamanho();
    boolean vazia();
    Object[] toArray();
}

abstract class Pilha implements ILista{
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
    
    public void adicionar(Object item) {
        empilhar(item);
    }
    
    public Object remover(ErroListaWrapper erro) {
        return desempilhar(erro);
    }
    
    /**
     * Adiciona <code>item</code> no final da pilha.
     */
    public abstract void empilhar(Object item);
    
    /**
     * Remove um <code>item</code> no topo da pilha.
     * return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
    */
    public abstract Object desempilhar(ErroListaWrapper erro);
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    public abstract Object getItem(ErroListaWrapper erro);
    
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
    public Object desempilhar(ErroListaWrapper erro) {
        if(topo == null){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Object valor = topo.getItem();
        topo = topo.getProximo();
        this.incTamanho(-1);
        
        return valor;
    }
    
    /**
     * Recupera <code>item</code> no topo da pilha, sem remover.
     * @return <code>item</code> no topo da pilha ou <code>null</code> se a pilha estiver vazia.
     */
    public Object getItem(ErroListaWrapper erro) {
        if(topo == null){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        erro.setErro(ErroLista.SEM_ERRO);
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
    
    public Object desempilhar(ErroListaWrapper erro) {
        if(tamanho() == 0){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Object aux = array[tamanho()-1];
        array[tamanho()-1] = null;
        incTamanho(-1);
        return aux;
    }
    
    public Object getItem(ErroListaWrapper erro) {
        if(tamanho() == 0){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        erro.setErro(ErroLista.SEM_ERRO);
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

abstract class Fila implements ILista {
    private int tamanho;
    
    Fila() {
        tamanho = 0;
    }
    
    protected void incTamanho(int inc) {
        tamanho += inc;
    }
    
    public void adicionar(Object item) {
        enfileirar(item);
    }
    
    public Object remover(ErroListaWrapper erro) {
        return desenfileirar(erro);
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
    public boolean vazia() {
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
    public abstract Object desenfileirar(ErroListaWrapper erro);
    
    /**
     * Recupera @code{item} no início da fila, sem remover.
     * @return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
     */
    public abstract Object getItem(ErroListaWrapper erro);
    
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
    public Object desenfileirar(ErroListaWrapper erro) {
        if(tamanho() == 0) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        Object val = inicio.getItem();
        incTamanho(-1);
        
        if(tamanho() == 0) {
            inicio = fim = null;
            return val;
        }
        
        inicio = inicio.getProximo();
        erro.setErro(ErroLista.SEM_ERRO);
        return val;
    }
    
    /**
     * Recupera @code{item} no início da fila, sem remover.
     * @return @code{item} no início da fila ou @code{null} se a fila estiver vazia.
     */
    public Object getItem(ErroListaWrapper erro) {
        if(inicio == null) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        erro.setErro(ErroLista.SEM_ERRO);
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
    
    public Object desenfileirar(ErroListaWrapper erro) {
        if(vazia()){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
            
        Object aux = array[0];
        for(int i = 0; i < tamanho()-1; i++)
            array[i] = array[i+1];
        
        incTamanho(-1);
        erro.setErro(ErroLista.SEM_ERRO);
        return aux;
    }
    
    public Object getItem(ErroListaWrapper erro) {
        if(vazia()){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        erro.setErro(ErroLista.SEM_ERRO);
        return array[0];
    }
    
    public Object[] toArray() {
        if(vazia())
            return null;
        
        Object arrReturn[] = new Object[tamanho()];
        for(int i = 0; i < tamanho(); i++)
            arrReturn[i] = array[i];
        return arrReturn;
    }
}

abstract class Lista  implements ILista {
    private int tamanho;
    
    public Lista() {
        tamanho = 0;
    }
    protected void incTamanho(int inc) {
        tamanho += inc;
    }
    
    public void adicionar(Object item) {
        adicionarFim(item);
    }
    
    public Object remover(ErroListaWrapper erro) {
        return removerInicio(erro);
    }
    
    public abstract void adicionarFim(Object item);
    public abstract void adicionarInicio(Object item);
    public abstract void adicionar(Object valor, int posicao, ErroListaWrapper erro);
    public abstract Object removerInicio(ErroListaWrapper erro);
    public abstract Object removerFim(ErroListaWrapper erro);
    public abstract Object remover(int posicao, ErroListaWrapper erro);
    public abstract Object getItem(ErroListaWrapper erro);
    public abstract Object getItem(int posicao, ErroListaWrapper erro);
    public abstract Object[] toArray();
    
    public int tamanho() {
        return tamanho;
    }
    public boolean vazia() {
        return tamanho == 0;
    }
}

class ListaEncadeada extends Lista {
    private No inicio;
    
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
    
    public ListaEncadeada() {
        inicio = null;
    }
    
    public void adicionarFim(Object valor) {
        No novoNo = new No(valor);
        No aux = inicio;
        incTamanho(1);
        
        if(tamanho() == 1) {
            inicio = novoNo;
            return;
        }
        
        while(aux.temProximo()) {
            aux = aux.getProximo();
        }
        aux.setProximo(novoNo);
    }
    
    public void adicionarInicio(Object valor) {
        No novoNo = new No(valor);
        
        incTamanho(1);
        novoNo.setProximo(inicio);
        inicio = novoNo;
    }
    
    public void adicionar(Object valor, int posicao, ErroListaWrapper erro) {
        if(posicao < 0 || posicao > tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return;
        }
        
        No novoNo = new No(valor);
        No aux = inicio;
        incTamanho(1);        
        erro.setErro(ErroLista.SEM_ERRO);
        
        if(tamanho() == 1) {
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
    
    public Object removerInicio(ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Object valor = inicio.getValor();
        inicio = inicio.getProximo();
        incTamanho(-1);
        return valor;
    }
    
    public Object removerFim(ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        incTamanho(-1);
        Object valor = inicio.getValor();
        No aux = inicio;
        
        if(tamanho() == 0) {
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
    
    public Object remover(int posicao, ErroListaWrapper erro) {
        if(this.vazia()){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        
        Object valor = inicio.getValor();
        No aux = inicio;
        incTamanho(-1);
        erro.setErro(ErroLista.SEM_ERRO);
        
        if(tamanho() == 0) {
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
    
    public Object getItem(ErroListaWrapper erro) {
        return inicio.getValor();
    }
    
    public Object getItem(int posicao, ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        
        Object valor;
        No aux = inicio;
        
        for(int i = 0; i < posicao; i++)
            aux = aux.getProximo();
        
        valor = aux.getValor();
        return valor;
    }
    
    public Object[] toArray() {
        Object[] array = new Object[this.tamanho()];
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

class ListaArray extends Lista {
    private Object[] array;
    private int tamMax;
    
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
    
    public void adicionarFim(Object item) {
        if(tamanho() >= tamMax)
            realocar(tamanho()*2);
        array[tamanho()] = item;
        incTamanho(1);
    }
    
    public void adicionarInicio(Object item) {
        if(tamanho() >= tamMax)
            realocar(tamanho()*2);
        for(int i = tamanho(); i > 0; i--)
            array[i] = array[i-1];
        array[0] = item;
        incTamanho(1);
    }
    
    public void adicionar(Object valor, int posicao, ErroListaWrapper erro) {
        if(posicao < 0 || posicao > tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return;
        }
        if(tamanho() >= tamMax)
            realocar(tamanho()*2);
        for(int i = tamanho(); i > posicao; i--) 
            array[i] = array[i-1];
        array[posicao] = valor;
        incTamanho(1);
        erro.setErro(ErroLista.SEM_ERRO);
    }
    
    public Object removerInicio(ErroListaWrapper erro) {
        if(vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        Object aux = array[0];
        for(int i = 0; i < tamanho()-1; i++)
            array[i] = array[i+1];
        array[tamanho()-1] = null;
        incTamanho(-1);
        erro.setErro(ErroLista.SEM_ERRO);
        return aux;
    }
    
    public Object removerFim(ErroListaWrapper erro) {
        if(vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        incTamanho(-1);
        Object aux = array[tamanho()-1];
        array[tamanho()-1] = null;
        return aux;
    }
    
    public Object remover(int posicao, ErroListaWrapper erro) {
        if(vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        Object aux = array[posicao];
        for(int i = posicao; i < tamanho()-1; i++)
            array[i] = array[i+1];
        array[tamanho()-1] = null;
        incTamanho(-1);
        return aux;
    }
    
    public Object getItem(ErroListaWrapper erro) {
        return getItem(0, erro);
    }
    
    public Object getItem(int posicao, ErroListaWrapper erro) {
        if(vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho()) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        return array[posicao];
    }
    
    public Object[] toArray() {
        if(vazia())
            return null;
        Object[] novoArr = new Object[tamanho()];
        for(int i = 0; i < tamanho(); i++)
            novoArr[i] = array[i];
        return novoArr;
    }
}

class Main {

    private ILista lista;
    
    public Main(ILista lista) {
        this.lista = lista;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main;
        String token;
        Integer valor;
        ErroListaWrapper erro = new ErroListaWrapper();
        
        token = in.next();
        
        switch (token) {
            case "PE": main = new Main(new PilhaEncadeada()); break;
            case "PA": main = new Main(new PilhaArray()); break;
            case "FE": main = new Main(new FilaEncadeada()); break;
            case "FA": main = new Main(new FilaArray()); break;
            case "LE": main = new Main(new ListaEncadeada()); break;
            default: main = new Main(new ListaArray()); // LA
        }
        
        while (!token.equals("Q")) {
            erro.setErro(ErroLista.SEM_ERRO);
            switch(token) {
                case "A":   // adiciona um item
                    valor = in.nextInt();
                    main.lista.adicionar(valor);
                    break;      
                case "R":   // remove um item
                    main.lista.remover(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "G":   // retorna um item, sem remover
                    valor = (Integer) main.lista.getItem(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    else
                        System.out.println(valor);
                    break;
                case "T":   // numero de itens na estrutura
                    System.out.println(main.lista.tamanho());
                    break;
                case "V":   // indica se a estrutura esta vazia
                    System.out.println(main.lista.vazia());
                    break;
                case "P":   // imprime os itens da estrutura, sem remover
                    Object valores[] = main.lista.toArray();
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
