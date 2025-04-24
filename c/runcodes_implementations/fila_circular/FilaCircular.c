#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FALSE 0
#define TRUE 1
#define ERR_INDICE_INVALIDO 2
#define ERR_FILA_VAZIA 3
#define SEM_MEMORIA 4

typedef int Item;

/*****************************
  Protótipo de Fila Circular
*/

typedef struct {

    Item* items;    // vetor de itens
    int inicio;     // indica posicao do primeiro item
    int cont;       // número de itens na fila
    int tamanho;    // tamanho do vetor
} Queue;

/** @return Referência para fila criada.
 *          ou @code{NULL} no caso de não haver memória.*/
Queue* criar_fila(int tamanho){
    Queue* novo = (Queue*) malloc(sizeof(Queue));
    if(novo==NULL)
        return NULL;

    novo->items = (Item*) malloc(tamanho*sizeof(Item));
    if(novo->items==NULL)
        return NULL;

    novo->inicio = 0;
    novo->tamanho = tamanho;
    novo->cont = 0;
    return novo;
}

/** Insere @code{item} no final de @code{queue}}
 *  @param queue Fila na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{queue}}.
 *  @return TRUE se @code{item} for inserido em @code{queue}}.
 *          FALSE se não houver memória. */
int enfileirar(Queue *queue, Item item){
    if(queue->cont >= queue->tamanho)
        return FALSE;
    
    int pos = (queue->cont + queue->inicio) % queue->tamanho;
    queue->items[pos] = item;
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
Item desenfileirar(Queue *queue, int *err){
    if(queue->cont == 0){
        *err = ERR_FILA_VAZIA;
        return TRUE;
    }

    queue->cont--;
    queue->inicio = (queue->inicio + 1) % queue->tamanho;
    *err = FALSE;
    return queue->items[queue->inicio];
}

/** Recupera de @code{queue}} o @code{item} na posição @code{pos}
 *  @param queue Fila que contém o @code{item}.
 *  @param pos  Posição na qual está o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorra nenhum erro
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return se @code{err} == FALSE, valor de @code{queue}} na posição @code{pos}
 *          caso contrário, TRUE indicando que houve erro. */
Item obter(Queue *queue, int *err){
    if(queue->cont == 0){
        *err = ERR_FILA_VAZIA;
        return TRUE;
    }

    *err = FALSE;
    return queue->items[queue->inicio];
}

/** Indica se @code{queue}} possui ou não itens.
 *  @param queue Fila na qual se verificará estar vazia ou não.

 *  @return TRUE se @code{queue}} é NULL ou se tamanho é igual a 0 (zero).
 *          FALSE se tamanho > 0 (zero). */
int estah_vazia(Queue *queue){
    return queue->cont == 0;
}

/** Recupera número de itens de @code{queue}}.
 *  @param queue Fila que se quer saber o tamanho.
 *  @return Número de itens mantidos na  @code{queue}}.*/
int tamanho(Queue *queue){
    return queue->cont;
}

/*****************************
  Implementações
*/


/************************************************************************************/

void imprimir_Fila(Queue *queue)
{
    int pos = queue->inicio;
    for(int i = 0; i < queue->cont; i++){
        printf("%d\n", queue->items[(pos+i) % queue->tamanho]);
    }
}

int main()
{
    Queue *queue = criar_fila(10);

    char token[3];
    int valor, erro;

    scanf("%s", token);

    while (token[0] != 'Q') {
        if (strcmp(token, "AF") == 0) {      // adicionar no fim
            scanf(" %d", &valor);
            enfileirar(queue, valor); /**#*/
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