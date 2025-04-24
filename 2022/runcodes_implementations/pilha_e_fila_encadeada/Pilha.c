#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define FALSE 0
#define TRUE 1
#define ERR_PILHA_VAZIA 2
#define SEM_MEMORIA 3



typedef struct TCELULA{
    int dado;
    struct TCELULA* proximo;
} Celula;

typedef struct TPILHA{
    int cont;
    Celula* topo;
} Pilha;


Celula* criar_celula(int item, Celula* proximo){
    Celula *nova_celula = (Celula *) malloc(sizeof(Celula));

    if(nova_celula==NULL)
        return NULL;

    nova_celula->dado = item;
    nova_celula->proximo = proximo;
    return nova_celula;
}

Pilha* criar_pilha(){
    Pilha* nova_pilha = (Pilha*) malloc(sizeof(Pilha));
    
    if(nova_pilha==NULL)
        return NULL;
    
    nova_pilha->cont = 0;
    nova_pilha->topo = NULL;
    return nova_pilha;
}

int empilhar(Pilha *pilha, int item){
    Celula *nova_celula = criar_celula(item, NULL);

    if(nova_celula==NULL)
        return FALSE;
    
    nova_celula->proximo = pilha->topo;
    pilha->topo = nova_celula;
    pilha->cont++;
    return TRUE;
}

int desempilhar(Pilha *pilha, int *err){
    Celula *celula;
    if(pilha->cont==0){
        *err = ERR_PILHA_VAZIA;
        return TRUE;
    }

    int item;
    celula = pilha->topo;
    item = pilha->topo->dado;
    pilha->topo = pilha->topo->proximo;
    pilha->cont--;
    *err = FALSE;
    free(celula);
    return item;
}

int tamanho(Pilha *pilha){
    return pilha->cont;
}

int esta_vazia(Pilha *pilha){
    return pilha->cont == 0;
}

int main(){
    char key;
    int operacoes, item, err;
    Pilha* pilha = criar_pilha();

    scanf("%d", &operacoes);



    for(int contador = 0; contador < operacoes; contador++){
        scanf(" %c", &key);
        if(key=='E'){
            scanf("%d", &item);
            empilhar(pilha, item);
        }
        else if(key=='D'){
            item = desempilhar(pilha, &err);
            if(err==ERR_PILHA_VAZIA)
                printf("Erro: Pilha Vazia\n");
            else
                printf("%d\n", item);
        }
        else if(key == 'T'){
            item = tamanho(pilha);
            printf("%d\n", item);
        }
    }
}