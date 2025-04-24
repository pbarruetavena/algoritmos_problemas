#include <stdio.h>
#include <stdlib.h>

/* FILA */
#define FALSE 0
#define TRUE 1
#define ERR_INDICE_INVALIDO 2
#define ERR_FILA_VAZIA 3 
#define SEM_MEMORIA 4



typedef struct CELULA {
    int dado;                   // item de informação
    struct CELULA *proximo;      // referência para a próxima célula
} Celula;


Celula* criar_celula(int v, Celula *p) 
{
    Celula *novo = (Celula*) malloc(sizeof(Celula));
    if (novo == NULL)
        return NULL;

    novo->dado = v;
    novo->proximo = p;

    return novo;
}

/***********
  Protótipo de Fila
*/

typedef struct {

    Celula *inicio, *fim;
    int cont;

} Queue;

/** @return Referência para fila criada.
 *          ou @code{NULL} no caso de não haver memória.*/
Queue* criar_fila(){
    Queue* nova_fila = (Queue*) malloc(sizeof(Queue));
    if(nova_fila==NULL)
        return NULL;

    nova_fila->cont = 0;
    nova_fila->inicio = NULL;
    nova_fila->fim = NULL;

    return nova_fila;
}

/** Insere @code{item} no final de @code{queue}}
 *  @param queue Fila na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{queue}}.
 *  @return TRUE se @code{item} for inserido em @code{queue}}.
 *          FALSE se não houver memória. */
int enfileirar(Queue *queue, int item){
    Celula *nova_celula = criar_celula(item, NULL);

    if(nova_celula==NULL)
        return FALSE;

    if(queue->cont == 0){
        queue->inicio = nova_celula;
        queue->fim = nova_celula;
        queue->cont++;
        return TRUE;
    }

    queue->fim->proximo = nova_celula;
    queue->fim = nova_celula;
    queue->cont++;
    return TRUE;
}

/** Remove um @code{item} no inicio de @code{queue}}
 *  @param queue Fila na qual será removio um @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorra nenhum erro.
 *              ERR_FILA_VAZIA se não existem itens em @code{queue}}.
 *  @return se @code{err} é FALSE, retorna @code{item} de @code{queue}} na posição 0 (zero).
 *          se @code{err} é ERR_FILA_VAZIA, então se não existem itens em @code{queue}}. */
int desenfileirar(Queue *queue, int *err){
    if(queue->cont==0){
        *err = ERR_FILA_VAZIA;
        return ERR_FILA_VAZIA;
    }

    Celula *celula = queue->inicio;
    int item = celula->dado;
    queue->inicio = celula->proximo;
    free(celula);
    queue->cont--;

    *err = FALSE;
    return item;
}

int estah_vazia(Queue *queue){
    return queue->cont==0;
}

/* TABELA HASH */
typedef struct {
    Queue **itens;
    int capacidade
} Tabela;

int hash_code(int item, int capacidade){
    return item % capacidade;
}

Tabela* cria_tabela(int capacidade){
    
    Tabela* tabela = (Tabela*) malloc(sizeof(Tabela));
    tabela->capacidade = capacidade;
    tabela->itens = (Queue**) malloc(capacidade * sizeof(Queue*));
    for(int i = 0; i < capacidade; i++)
        tabela->itens[i] = criar_fila();

    return tabela;
}

void adicionar(Tabela* tabela, int item){
    int pos = hash_code(item, tabela->capacidade);
    enfileirar(tabela->itens[pos], item); 
}

void imprime(Tabela *tabela){
    int t = tabela->capacidade, err = 0, aux;
    for(int i = 0; i < t; i++){
        printf("%d -> ", i);

        while(!estah_vazia(tabela->itens[i])){
            aux = desenfileirar(tabela->itens[i], &err);
            printf("%d -> ", aux);
        }
        printf("\\\n");
    }
}

void libera(Tabela* tabela){
    for(int i = 0; i < tabela->capacidade; i++){
        free(tabela->itens[i]);
    }
    free(tabela->itens);
    free(tabela);
}

int main(){
    int n, m, c, aux;
    Tabela *tabela;
    scanf("%d", &n);
    
    while(n--){
        scanf("%d %d", &m, &c);
        tabela = cria_tabela(m);

        for(int i = 0; i < c; i++){
            scanf("%d", &aux);
            adicionar(tabela, aux);
        }

        imprime(tabela);

        if(n!=0)
            printf("\n");
    }

    return 0;
}