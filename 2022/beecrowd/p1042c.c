#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEFAULT_CAPACITY 10

#define FALSE 0
#define TRUE 1
#define ERR_INDICE_INVALIDO 2
#define ERR_LISTA_VAZIA 3
#define SUCESSO 4

/**
 * Tipo Abstrato de Dado Lista
 * Implementa uma lista de items por meio de um array/vetor
 */ 
typedef struct {
    int *items;     // vetor de itens
    int cont;    // número de elementos armazenados na lista
    int limite;     /* capacidade máxima, número máximo
                       de itens que podem ser inseridos na lista, 
                       sem necessidade de realocar memória */
} ArrayList;

/**
 * Cria uma lista vazia.
 * @return  Retona uma lista vazia.
 *          Se retorno for NULL, memória insuficiente.  */
ArrayList* criar_lista() 
{
    // aloca memória para a lista
    ArrayList *list = (ArrayList*) malloc(sizeof(ArrayList));
    
    // se há memória
    if (list != NULL)
    {
        list->cont = 0;                  // lista vazia
        list->limite = DEFAULT_CAPACITY;    // capacidade inicial
        // cria vetor para armazenar os itens de dados
        list->items = (int*) malloc(list->limite * sizeof(int));

        if (list->items == NULL) // não há memória
            return NULL;

        return list;                        // retorna a lista criada e inicializada
    }
    else // não há memória
        return NULL;
}

/** Insere @code{item} no final de @code{list}
 *  @param list Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{list}.
 *  @return TRUE se @code{item} for inserido em @code{list}.
 *          FALSE se não houver memória. */
int inserir(ArrayList *list, int item){
    if(list->cont == list->limite){
        list->items = realloc(list->items, (list->limite + DEFAULT_CAPACITY)*sizeof(int));
        if(list->items == NULL)
            return FALSE;

        list->limite+=DEFAULT_CAPACITY;
    }

    list->items[list->cont] = item;
    if(list->items != NULL){
        list->cont++;
        return TRUE;
    } else
        return FALSE;
}

/** Insere @code{item} numa posição @code{pos} de @code{list}
 *  @param list Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{list}.
 *  @param pos  Posição na qual o @code{item} deverá ser inserido.
 *  @return TRUE se @code{item} for inserido em @code{list}
 *          FALSE se não houver memória 
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho) */
int inserir_posicao(ArrayList *list, int item, int pos){
    if(pos < 0 || pos > list->cont){
        return ERR_INDICE_INVALIDO;
    }

    if(list->cont == list->limite){
        list->items = realloc(list->items, (list->limite + DEFAULT_CAPACITY)*sizeof(int));
        if(list->items == NULL)
            return FALSE;
        list->limite+=DEFAULT_CAPACITY;
    }

    for(int i = list->cont; i>pos; i--){
        list->items[i] = list->items[i-1];
    }
    list->items[pos] = item;
    list->cont++;
    return TRUE;
}

/** Remove um @code{item} no inicio de @code{list}
 *  @param list Lista na qual será removio um @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{list}.
 *  @return se @code{err} é FALSE, @code{item} de @code{list} na posição 0 (zero).
 *          ERR_LISTA_VAZIA se não existem itens em @code{list}. */
int remover(ArrayList *list, int *err){
    if(list->cont == 0){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    int valor = list->items[0];
    list->cont--;
    for(int i = 0; i < list->cont; i++){
        list->items[i] = list->items[i+1];
    }
    
    *err = FALSE;
    return valor;
}

/** Remove um @code{item} na posição @code{pos} de @code{list}
 *  @param list Lista na qual será removio um @code{item}.
 *  @param pos  Posição na qual será retirado o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{list}.
 *  @return se @code{err} é FALSE, @code{item} de @code{list} na posição @code{pos}.
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho) */
int remover_posicao(ArrayList *list, int pos, int *err){
    if(list->cont == 0){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    if(pos < 0 || pos >=list->cont){
        *err = ERR_INDICE_INVALIDO;
        return ERR_INDICE_INVALIDO;
    }

    int valor = list->items[pos];
    list->cont--;
    for(int i = pos; i < list->cont; i++){
        list->items[i] = list->items[i+1];
    }
    *err = FALSE;
    return valor;
}

/** Recupera de @code{list} o @code{item} na posição @code{pos}
 *  @param list Lista que contém o @code{item}.
 *  @param pos  Posição na qual está o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return se @code{err} == FALSE, valor de @code{list} na posição @code{pos}
 *          caso contrário, TRUE indicando que houve erro. */
int obter_item(ArrayList *list, int pos, int *err){
    if(pos < 0 || pos >= list->cont){
        *err = ERR_INDICE_INVALIDO;
        return TRUE;
    }
    *err = FALSE;
    return list->items[pos];
}

/** Atribui novo @code{item} à posição @code{pos} de @code{list}
 *  @param list Lista que contém os items.
 *  @param pos  Posição na qual será atribuído o @code{item}.
 *  @param item Novo item que sera atribuído a @code{list}
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return FALSE se não for possível atribuir @code{item} em @code{list} na posição @code{pos}.
 *          TRUE se for possível. */
int atribuir_valor(ArrayList *list, int pos, int item, int *err){
    if(pos < 0 || pos >= list->cont){
        *err = ERR_INDICE_INVALIDO;
        return FALSE;
    }
    *err = FALSE;
    list->items[pos] = item;
    return TRUE;
}

/** Indica se @code{list} possui ou não itens.
 *  @param list Lista na qual se verificará estar vazia ou não.

 *  @return TRUE se @code{list} é NULL ou se tamanho é igual a 0 (zero).
 *          FALSE se tamanho > 0 (zero). */
int estah_vazia(ArrayList *list);

/** Recupera número de itens de @code{list}.
 *  @param list Lista que se quer saber o tamanho.
 *  @return Número de itens mantidos na  @code{list}.*/
int tamanho(ArrayList *list);

/************************************************************************************/

int estah_vazia(ArrayList *list) 
{
    return (tamanho(list) == 0);
}

int tamanho(ArrayList *list) 
{
    return (list == NULL) ? 0 : list->cont;
}

/************************************************************************************/

void imprimir_lista(ArrayList *list) {

    for (int i = 0; i < list->cont; i++)
        printf("%d\n", list->items[i]);
}

int remove_menor(ArrayList *list){
    int menor = 0, valor;

    for(int i = 0; i < list->cont; i++){
        if(list->items[i]<list->items[menor])
            menor = i;
    }

    valor = list->items[menor];
    list->cont--;
    for(int i = menor; i < list->cont; i++){
        list->items[i] = list->items[i+1];
    }

    return valor;
}

int main(){
    ArrayList *lista1 = criar_lista();
    ArrayList *lista2 = criar_lista();
    int n, valor;

    for(int i = 0; i < 3; i++){
        scanf("%d", &n);
        inserir(lista1, n);
        inserir(lista2, n);
    }

    for(int i = 0; i < 3; i++){
        valor = remove_menor(lista2);
        printf("%d\n", valor);
    }
    
  