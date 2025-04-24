
package br.cefetmg.inf.listas.lista11.r1007;

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

abstract class Lista {
    private int tamanho;
    
    public Lista() {
        tamanho = 0;
    }
    protected void incTamanho(int inc) {
        tamanho += inc;
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

    private Lista lista;
    
    public Main(Lista lista) {
        this.lista = lista;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main;
        String token;
        Integer valor;
        int posicao;
        ErroListaWrapper erro = new ErroListaWrapper();
        
        token = in.next();
        
        if (token.equals("LE"))
            main = new Main(new ListaEncadeada());
        else
            main = new Main(new ListaArray());
        
        while (!token.equals("Q")) {
            erro.setErro(ErroLista.SEM_ERRO);
            switch(token) {
                case "AF":
                    valor = in.nextInt();
                    main.lista.adicionarFim(valor);
                    break;
                case "AI":
                    valor = in.nextInt();
                    main.lista.adicionarInicio(valor);
                    break;                    
                case "AP":
                    posicao = in.nextInt();
                    valor = in.nextInt();
                    main.lista.adicionar(valor, posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;          
                case "RI":
                    main.lista.removerInicio(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "RF":
                    main.lista.removerFim(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "RP":                    
                    posicao = in.nextInt();
                    main.lista.remover(posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "G":
                    valor = (Integer) main.lista.getItem(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    else
                        System.out.println(valor);
                    break;
                case "GP":
                    posicao = in.nextInt();
                    valor = (Integer) main.lista.getItem(posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    else
                        System.out.println(valor);
                    break;
                case "T":
                    System.out.println(main.lista.tamanho());
                    break;
                case "V":
                    System.out.println(main.lista.vazia());
                    break;
                case "P":
                    Object valores[] = main.lista.toArray();
                    if (valores != null) {
                        for(Object item: valores)
                            System.out.println((Integer)item);
                    }
                    break;
            }
            token = in.next();
        }   
    }
}
