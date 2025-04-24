#include <stdio.h>  // scanf printf
#include <stdlib.h> // malloc free
#include <string.h>

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

/** Recupera de @code{queue}} o @code{item} na posição @code{pos}
 *  @param queue Fila que contém o @code{item}.
 *  @param pos  Posição na qual está o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorra nenhum erro
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return se @code{err} == FALSE, valor de @code{queue}} na posição @code{pos}
 *          caso contrário, TRUE indicando que houve erro. */
int obter(Queue *queue, int *err){
    if(queue->cont==0){
        *err = ERR_FILA_VAZIA;
        return ERR_FILA_VAZIA;
    }

    *err = FALSE;
    return queue->inicio->dado;
}

/** Indica se @code{queue}} possui ou não itens.
 *  @param queue Fila na qual se verificará estar vazia ou não.

 *  @return TRUE se @code{queue}} é NULL ou se tamanho é igual a 0 (zero).
 *          FALSE se tamanho > 0 (zero). */
int estah_vazia(Queue *queue){
    return queue->cont==0;
}

/** Recupera número de itens de @code{queue}}.
 *  @param queue Fila que se quer saber o tamanho.
 *  @return Número de itens mantidos na  @code{queue}}.*/
int tamanho(Queue *queue){
    return queue->cont;
}

/***********
  Implementações
*/


/****************************/

void imprimir_Fila(Queue *queue)
{
    if (estah_vazia(queue) == FALSE) {
        Celula *x = queue->inicio;
        while(x != NULL)
        {
            printf("%d\n", x->dado);
            x = x->proximo;
        }
    }
}

int main()
{
    Queue *queue = criar_fila();

    char token[3];
    int valor, erro;

    scanf("%s", token);

    while (token[0] != 'Q') {
        if (strcmp(token, "AF") == 0) {      // adicionar no fim
            scanf(" %d", &valor);
            enfileirar(queue, valor);
        }
        else if (strcmp(token, "RI") == 0) { // remover no início
            desenfileirar(queue, &erro);
            if (erro == ERR_FILA_VAZIA)
                printf("NenhumItemException\n");
        }
        else if (token[0] == 'G') {  // obter valor numa posição
            valor = obter(queue, &erro);
            if (erro == ERR_FILA_VAZIA)
                printf("NenhumItemException\n");
            else
                printf("%d\n", valor);
        }
        else if (token[0] == 'T') {  // tamanho da Fila
            printf("%d\n", tamanho(queue));
        }
        else if (token[0] == 'V') {   // Fila está vazia
            if (estah_vazia(queue))
                printf("true\n");
            else
                printf("false\n");
        }
        else if (token[0] == 'P')    // imprimir Fila
            imprimir_Fila(queue);
        else
            printf("ERRO %s\n", token);

        fflush(stdin);
        scanf("%s", token);
    }

    return 0;
}