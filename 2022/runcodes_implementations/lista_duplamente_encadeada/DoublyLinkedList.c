#include <stdio.h>  // scanf printf
#include <stdlib.h> // malloc free
#include <string.h>

#define FALSE 0
#define TRUE 1
#define ERR_INDICE_INVALIDO 2
#define ERR_LISTA_VAZIA 3
#define SEM_MEMORIA 4

/*****************************
  Protótipo de Célula
*/

typedef struct TCelula {
    int dado;                   // item de informação
    struct TCelula *anterior;   // referência para a célula anterior
    struct TCelula *proximo;    // referência para a próxima célula
} Celula;

Celula* criar_celula(int v, Celula *a, Celula *p) 
{
    Celula *novo = (Celula*) malloc(sizeof(Celula));
    if (novo == NULL) // se não houve memória
        return NULL;

    novo->dado = v;
    novo->anterior = a;
    novo->proximo = p;

    return novo;
}

/*****************************
  Protótipo da Lista Duplamente Encadeada
*/

typedef struct {
    Celula *primeiro;
    Celula *ultimo;
    int cont;

} LinkedList;

LinkedList* criar_lista(){
    LinkedList* list;
    list = (LinkedList*) malloc(sizeof(LinkedList));

    if(list==NULL)
        return NULL;

    list->cont = 0;
    list->primeiro = NULL;
    list->ultimo = NULL;
    return list;
}

/** Insere @code{item} no final de @code{list}
 *  @param list Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{list}.
 *  @return TRUE se @code{item} for inserido em @code{list}.
 *          FALSE se não houver memória. */
int inserir(LinkedList *list, int item){
    Celula* nova_celula = criar_celula(item, NULL, NULL);
    if(nova_celula==NULL)
        return FALSE;

    list->cont++;
    if(list->cont == 1){
        list->primeiro = nova_celula;
        list->ultimo = nova_celula;
        return TRUE;
    }

    list->ultimo->proximo = nova_celula;
    nova_celula->anterior = list->ultimo;
    list->ultimo = nova_celula;
    return TRUE;
}

/** Insere @code{item} numa posição @code{pos} de @code{list}
 *  @param list Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{list}.
 *  @param pos  Posição na qual o @code{item} deverá ser inserido.
 *  @return TRUE se @code{item} for inserido em @code{list}
 *          FALSE se não houver memória 
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} > tamanho) */
int inserir_posicao(LinkedList *list, int item, int pos){
    if(pos < 0 || pos > list->cont)
        return ERR_INDICE_INVALIDO;

    Celula *celula_atual;
    Celula* nova_celula = criar_celula(item, NULL, NULL);
    if(nova_celula==NULL)
        return FALSE;

    list->cont++;
    if(list->cont == 1){
        list->primeiro = nova_celula;
        list->ultimo = nova_celula;
        return TRUE;
    }

    if(pos == 0){
        nova_celula->proximo = list->primeiro;
        list->primeiro->anterior = nova_celula;
        list->primeiro = nova_celula;
        return TRUE;
    } else if(pos==list->cont-1){
        list->ultimo->proximo = nova_celula;
        nova_celula->anterior = list->ultimo;
        list->ultimo = nova_celula;
        return TRUE;
    }

    if(pos <= (list->cont-1)/2){
        celula_atual = list->primeiro;
        for(int i = 0; i < pos; i++)
            celula_atual = celula_atual->proximo;
    } else{
        celula_atual = list->ultimo;
        for(int i = list->cont-1; i > pos+1; i--)
            celula_atual = celula_atual->anterior;
    }

    celula_atual->anterior->proximo = nova_celula;
    nova_celula->anterior = celula_atual->anterior;
    celula_atual->anterior = nova_celula;
    nova_celula->proximo = celula_atual;
    return TRUE;
}

/** Remove um @code{item} no inicio de @code{list}
 *  @param list Lista na qual será removio um @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{list}.
 *  @return se @code{err} é FALSE, @code{item} de @code{list} na posição 0 (zero).
 *          ERR_LISTA_VAZIA se não existem itens em @code{list}. */
int remover(LinkedList *list, int *err){
    if(list->cont==0){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    if(list->cont==1){
        Celula* aux = list->primeiro;
        int item = aux->dado;
        list->cont--;
        list->primeiro = NULL;
        list->ultimo = NULL;
        free(aux);
        *err = FALSE;
        return item;
    }

    Celula* aux = list->primeiro;
    int item = aux->dado;
    list->cont--;
    list->primeiro = aux->proximo;
    list->primeiro->anterior = NULL;
    aux->proximo = NULL;
    free(aux);
    *err = FALSE;
    return item;
}

/** Remove um @code{item} na posição @code{pos} de @code{list}
 *  @param list Lista na qual será removio um @code{item}.
 *  @param pos  Posição na qual será retirado o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{list}.
 *  @return se @code{err} é FALSE, @code{item} de @code{list} na posição @code{pos}.
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho) */
int remover_posicao(LinkedList *list, int pos, int *err){
    if(list->cont == 0){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    if(pos < 0 || pos >= list->cont){
        *err = ERR_INDICE_INVALIDO;
        return ERR_INDICE_INVALIDO;
    }

    if(list->cont==1){
        Celula* auxiliar = list->primeiro;
        int item = auxiliar->dado;
        list->cont--;
        list->primeiro = NULL;
        list->ultimo = NULL;
        free(auxiliar);
        *err = FALSE;
        return item;
    }

    Celula* auxiliar;
    int item;

    if(pos == 0){
        auxiliar = list->primeiro;
        list->primeiro = auxiliar->proximo;
        list->primeiro->anterior = NULL;
        auxiliar->proximo = NULL;
        *err = FALSE;
        item = auxiliar->dado;
        free(auxiliar);
        list->cont--;
        return item;
    }
    else if(pos==list->cont-1){
        auxiliar = list->ultimo;
        list->ultimo = auxiliar->anterior;
        list->ultimo->proximo = NULL;
        auxiliar->anterior = NULL;
        *err = FALSE;
        item = auxiliar->dado;
        free(auxiliar);
        list->cont--;
        return item;
    }


    if(pos <= list->cont/2){
        auxiliar = list->primeiro;
        for(int i = 0; i < pos; i++)
            auxiliar = auxiliar->proximo;
    } else{
        auxiliar = list->ultimo;
        for(int i = list->cont-1; i > pos+1; i--)
            auxiliar = auxiliar->anterior;
    }

    auxiliar->anterior->proximo = auxiliar->proximo;
    auxiliar->proximo->anterior = auxiliar->anterior;
    auxiliar->anterior = auxiliar->proximo = NULL;
    *err = FALSE;
    item = auxiliar->dado;
    free(auxiliar);
    list->cont--;
    return item;
}

/** Recupera de @code{list} o @code{item} na posição @code{pos}
 *  @param list Lista que contém o @code{item}.
 *  @param pos  Posição na qual está o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return se @code{err} == FALSE, valor de @code{list} na posição @code{pos}
 *          caso contrário, TRUE indicando que houve erro. */
int obter_item(LinkedList *list, int pos, int *err){
    if(pos < 0 || pos >= list->cont){
        *err = ERR_INDICE_INVALIDO;
        return TRUE;
    }

    Celula* auxiliar;

    if(pos <= list->cont/2){
        auxiliar = list->primeiro;
        for(int i = 0; i < pos; i++)
            auxiliar = auxiliar->proximo;
    } else{
        auxiliar = list->ultimo;
        for(int i = list->cont-1; i > pos+1; i--)
            auxiliar = auxiliar->anterior;
    }

    return auxiliar->dado;
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
int atribuir_valor(LinkedList *list, int pos, int item, int *err){
    if(pos < 0 || pos >= list->cont){
        *err = ERR_INDICE_INVALIDO;
        return FALSE;
    }

    Celula* auxiliar;

    if(pos <= list->cont/2){
        auxiliar = list->primeiro;
        for(int i = 0; i < pos; i++)
            auxiliar = auxiliar->proximo;
    } else{
        auxiliar = list->ultimo;
        for(int i = list->cont-1; i > pos+1; i--)
            auxiliar = auxiliar->anterior;
    }

    auxiliar->dado = item;
    return TRUE;
}

/** Indica se @code{list} possui ou não itens.
 *  @param list Lista na qual se verificará estar vazia ou não.

 *  @return TRUE se @code{list} é NULL ou se tamanho é igual a 0 (zero).
 *          FALSE se tamanho > 0 (zero). */
int estah_vazia(LinkedList *list){
    return list->cont == 0;
}

/** Recupera número de itens de @code{list}.
 *  @param list Lista que se quer saber o tamanho.
 *  @return Número de itens mantidos na  @code{list}.*/
int tamanho(LinkedList *list){
    return list->cont;
}

/*****************************
  Implementações
*/


/*****************************
  Principal
*/

void imprimir_lista(LinkedList *list) {
    if (estah_vazia(list) == FALSE) {
        Celula *x = list->primeiro;
        while(x != NULL)
        {
            printf("%d\n", x->dado);
            x = x->proximo;
        }
    }
}

int main()
{
    LinkedList *list = criar_lista();

    char token[3];
    int valor, posicao, result, erro;

    scanf("%s", token);

    while (token[0] != 'Q') {
        if (strcmp(token, "AF") == 0) {      // adicionar no fim
            scanf(" %d", &valor);
            inserir(list, valor);
        }
        else if (strcmp(token, "AP") == 0) { // adicionar numa posição
            scanf(" %d", &posicao);
            scanf(" %d", &valor);
            result = inserir_posicao(list, valor, posicao);

            switch(result) {
                case FALSE:
                    printf("NenhumItemException\n");
                    break;
                case ERR_INDICE_INVALIDO:
                    printf("PosicaoInvalidaException\n");
                    break;
            }                
        }
        else if (strcmp(token, "RI") == 0) { // remover no início
            valor = remover(list, &erro);
            switch(erro) {
                case ERR_LISTA_VAZIA:
                    printf("NenhumItemException\n");
                    break;
                case ERR_INDICE_INVALIDO:
                    printf("PosicaoInvalidaException\n");
                    break;
            }
        }
        else if (strcmp(token, "RP") == 0) { // remover numa posição
            scanf(" %d", &posicao);
            remover_posicao(list, posicao, &erro);
            if (erro == ERR_INDICE_INVALIDO)
                printf("PosicaoInvalidaException\n");                
        }
        else if (token[0] == 'S') {  // setar valor numa posição
            scanf(" %d", &posicao);
            scanf(" %d", &valor);
            atribuir_valor(list, posicao, valor, &erro);
            if (erro == ERR_INDICE_INVALIDO)
                printf("PosicaoInvalidaException\n");    
        }        
        else if (token[0] == 'G') {  // obter valor numa posição
            scanf(" %d", &posicao);
            obter_item(list, posicao, &erro);
            if (erro == ERR_INDICE_INVALIDO)
            printf("PosicaoInvalidaException\n");
        }
        else if (token[0] == 'T') {  // tamanho da lista
            printf("%d\n", tamanho(list));
        }
        else if (token[0] == 'V') {   // lista está vazia
            if (estah_vazia(list))
                printf("true\n");
            else
                printf("false\n");
        }
        else if (token[0] == 'P')    // imprimir lista
            imprimir_lista(list);
        else
            printf("ERRO %s\n", token);

        fflush(stdin);
        scanf("%s", token);
    }

    return 0;
}