
package br.cefetmg.inf.listas.lista08.r1002;

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

class No {
    private final Integer valor;
    private No proximo;
    
    public No(Integer valor) {
        this.valor = valor;
        proximo = null;
    }
    
    public Integer getValor() {
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

class Lista {
    private int tamanho;
    private No inicio;
    
    public Lista() {
        tamanho = 0;
        inicio = null;
    }
    
    public void adicionarFim(Integer valor) {
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
    
    public void adicionarInicio(Integer valor) {
        No novoNo = new No(valor);
        
        tamanho++;
        novoNo.setProximo(inicio);
        inicio = novoNo;
    }
    
    public void adicionar(Integer valor, int posicao, ErroListaWrapper erro) {
        if(posicao < 0 || posicao > tamanho) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return;
        }
        
        No novoNo = new No(valor);
        No aux = inicio;
        tamanho++;        
        erro.setErro(ErroLista.SEM_ERRO);
        
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
    
    public Integer removerInicio(ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Integer valor = inicio.getValor();
        inicio = inicio.getProximo();
        tamanho--;
        return valor;
    }
    
    public Integer removerFim(ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        tamanho--;
        Integer valor = inicio.getValor();
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
    
    public Integer remover(int posicao, ErroListaWrapper erro) {
        if(this.vazia()){
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        
        Integer valor = inicio.getValor();
        No aux = inicio;
        tamanho--;
        erro.setErro(ErroLista.SEM_ERRO);
        
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
    
    public Integer getItem(ErroListaWrapper erro) {
        return inicio.getValor();
    }
    
    public Integer getItem(int posicao, ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        if(posicao < 0 || posicao >= tamanho) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return null;
        }
        
        Integer valor;
        No aux = inicio;
        
        for(int i = 0; i < posicao; i++)
            aux = aux.getProximo();
        
        valor = aux.getValor();
        return valor;
    }
    
    public int tamanho() {
        return tamanho;
    }
    
    public boolean vazia() {
        return tamanho == 0;
    }
    
    public Integer[] toArray() {
        Integer[] array = new Integer[this.tamanho];
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

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Lista lista = new Lista();
        String token;
        Integer valor;
        int posicao;
        ErroListaWrapper erro = new ErroListaWrapper();
        
        token = in.next();
        while (!token.equals("Q")) {
            erro.setErro(ErroLista.SEM_ERRO);
            switch(token) {
                case "AF":
                    valor = in.nextInt();
                    lista.adicionarFim(valor);
                    break;
                case "AI":
                    valor = in.nextInt();
                    lista.adicionarInicio(valor);
                    break;                    
                case "AP":
                    posicao = in.nextInt();
                    valor = in.nextInt();
                    lista.adicionar(valor, posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;          
                case "RI":
                    lista.removerInicio(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "RF":
                    lista.removerFim(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "RP":                    
                    posicao = in.nextInt();
                    lista.remover(posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    break;
                case "G":
                    valor = lista.getItem(erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    else
                        System.out.println(valor);
                    break;
                case "GP":
                    posicao = in.nextInt();
                    valor = lista.getItem(posicao, erro);
                    if (erro.getErro() != ErroLista.SEM_ERRO)
                        System.out.println(erro.getErro().getMessage());
                    else
                        System.out.println(valor);
                    break;
                case "T":
                    System.out.println(lista.tamanho());
                    break;
                case "V":
                    System.out.println(lista.vazia());
                    break;
                case "P":
                    Integer valores[] = lista.toArray();
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
