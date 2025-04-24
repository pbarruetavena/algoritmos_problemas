
package br.cefetmg.inf.listas.lista09.r1004;

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

class Lista {
    private No inicio, fim;
    private int tamanho;
    
    private class No {
        private Integer valor;
        private No anterior;
        private No proximo;
        
        public No(Integer val, No ant, No prox) {
            valor = val;
            anterior = ant;
            proximo = prox;
        }
        
        public Integer getValor() {
            return valor;
        }
        
        public No getAnterior() {
            return anterior;
        }
        
        public No getProximo() {
            return proximo;
        }
        
        public void setAnterior(No ante) {
            anterior = ante;
        }
        
        public void setProximo(No prox) {
            proximo = prox;
        }
    }
    
    public Lista() {
        tamanho = 0;
        inicio = fim = null;
    }
    
    public void adicionarFim(Integer valor) {
        No novoNo = new No(valor, fim, null);
        
        if(tamanho == 0) {
            inicio = fim = novoNo;
            tamanho++;
            return;
        }
        
        fim.setProximo(novoNo);
        fim = novoNo;
        tamanho++;
        
    }
    
    public void adicionarInicio(Integer valor) {
        No novoNo = new No(valor, null, inicio);
        
        if(tamanho == 0) {
            inicio = fim = novoNo;
            tamanho++;
            return;
        }
        
        inicio.setAnterior(novoNo);
        inicio = novoNo;
        tamanho++;
        
    }
    
    public void adicionar(Integer valor, int posicao, ErroListaWrapper erro) {
        if(posicao < 0 || posicao > tamanho) {
            erro.setErro(ErroLista.INDICE_INVALIDO);
            return;
        }
        
        if(posicao == 0) {
            this.adicionarInicio(valor);
            return;
        }
        if(posicao == tamanho) {
            this.adicionarFim(valor);
            return;
        }
        
        No novoNo = new No(valor, null, null);
        erro.setErro(ErroLista.SEM_ERRO);
        
        if(posicao < tamanho/2) {
            
            No aux = inicio;
            for(int i = 0; i < posicao - 1; i++) {
                aux = aux.getProximo();
            }
            novoNo.setProximo(aux.getProximo());
            novoNo.setAnterior(aux);
            aux.getProximo().setAnterior(novoNo);
            aux.setProximo(novoNo);
            tamanho++;
            
        } else {
            
            No aux = fim;
            for(int i = tamanho-1; i > posicao+1; i--) {
                aux = aux.getAnterior();
            }
            novoNo.setProximo(aux);
            novoNo.setAnterior(aux.getAnterior());
            aux.getAnterior().setProximo(novoNo);
            aux.setAnterior(novoNo);
            tamanho++;
        }
    }
    
    public Integer removerInicio(ErroListaWrapper erro) {   
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Integer val = inicio.getValor();
        tamanho--;
        inicio = inicio.getProximo();
        inicio.setAnterior(null);
        
        if(tamanho == 0) {
            inicio = fim = null;
            return val;
        }
        if(tamanho == 1) {
            fim = inicio;
            return val;
        }
        
        return val;
    }
    public Integer removerFim(ErroListaWrapper erro) {
        if(this.vazia()) {
            erro.setErro(ErroLista.VAZIA);
            return null;
        }
        
        erro.setErro(ErroLista.SEM_ERRO);
        Integer val = fim.getValor();
        tamanho--;
        fim = fim.getAnterior();
        fim.setProximo(null);
        
        if(tamanho == 0) {
            inicio = fim = null;
        }
        if(tamanho == 1) {
            inicio = fim;
        }
        
        return val;
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
        
        if(posicao == 0) {
            return this.removerInicio(erro);
        }
        if(posicao == tamanho-1) {
            return this.removerInicio(erro);
        }
        
        No aux;
        if(posicao < tamanho/2) {
            aux = inicio;
            for(int i = 0; i < posicao; i++) {
                aux = aux.getProximo();
            }
        } else {
            aux = fim;
            for(int i = tamanho-1; i > posicao; i--) {
                aux = aux.getAnterior();
            }
        }
        
        aux.getProximo().setAnterior(aux.getAnterior());
        aux.getAnterior().setProximo(aux.getProximo());
        tamanho--;
        return aux.getValor();
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
        
        No aux;
        if(posicao < tamanho/2) {
            aux = inicio;
            for(int i = 0; i < posicao; i++) {
                aux = aux.getProximo();
            }
        } else {
            aux = fim;
            for(int i = tamanho-1; i > posicao; i--) {
                aux = aux.getAnterior();
            }
        }
        return aux.getValor();
    }
    
    public int tamanho() {
        return tamanho;
    }
    
    public boolean vazia() {
        return tamanho == 0;
    }
    
    public Integer[] toArray() {
        if(tamanho == 0) {
            return null;
        }
        
        No aux = inicio;
        Integer[] array = new Integer[tamanho];
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
