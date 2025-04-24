#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define FALSE 0
#define TRUE 1
#define ERR_INDICE_INVALIDO 2
#define ERR_LISTA_VAZIA 3
#define SUCESSO 4

typedef struct tipo_no{
    int item;
    struct tipo_no *proximo;
} NO;

/**
 * Tipo Abstrato de Dado Lista
 * Implementa uma lista encadeada de items
 */ 
typedef struct {
    int cont;
    NO *primeiro;
    NO *ultimo;
} Lista;

/**
 * Cria uma lista vazia.
 * @return  Retona uma lista vazia.
 *          Se retorno for NULL, memória insuficiente.  */
Lista* criar_lista() 
{
    Lista *nova_lista = (Lista*) malloc(sizeof(Lista));

    if(nova_lista==NULL)
        return NULL;
    
    nova_lista->cont = 0;
    nova_lista->primeiro = NULL;
    nova_lista->ultimo = NULL;

    return nova_lista;
}

/** Função para criar um nó (célula) solto na memória
 *  Uso interno - usado somente dentro dos métodos*/
NO* cria_no(int item){
    NO *novo_no = (NO*) malloc(sizeof(NO));
    novo_no->item = item;
    novo_no->proximo = NULL;
    
    return novo_no;
}

int estah_vazia(Lista *lista);

/** Função para percorrer a lista
 *  @return um ponteiro para o nó imediatamente anterior ao nó de posição pos
 *  Função para o uso interno do método */
NO* percorre_no(Lista *lista, int pos){
    NO* ptr_no_atual = lista->primeiro;

    for(int i = 0; i<pos-1; i++){
        ptr_no_atual = ptr_no_atual->proximo;
    }

    return ptr_no_atual;
}

/** Insere @code{item} no final de @code{lista}
 *  @param lista Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{lista}.
 *  @return TRUE se @code{item} for inserido em @code{lista}.
 *          FALSE se não houver memória. */
int inserir(Lista *lista, int item){
    NO *novo_no = cria_no(item);
    if(novo_no==NULL)
        return FALSE;

    if(lista->cont==0){
        lista->primeiro = novo_no;
        lista->ultimo = novo_no;
    } else{
        lista->ultimo->proximo = novo_no;
        lista->ultimo = novo_no;;
    }

    lista->cont++;
    return TRUE;
}

/** Insere @code{item} numa posição @code{pos} de @code{lista}
 *  @param lista Lista na qual será inserido o @code{item}.
 *  @param item Item que será inserido em @code{lista}.
 *  @param pos  Posição na qual o @code{item} deverá ser inserido.
 *  @return TRUE se @code{item} for inserido em @code{lista}
 *          FALSE se não houver memória 
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho) */
int inserir_posicao(Lista *lista, int item, int pos){
    NO *novo_no, *ptr_no_atual = lista->primeiro;

    if(pos < 0 || pos > lista->cont)
        return ERR_INDICE_INVALIDO;
    
    novo_no = cria_no(item);
    if(novo_no==NULL){
        return FALSE;
    }

    if(pos==0){
        novo_no->proximo = lista->primeiro;
        lista->primeiro = novo_no;

        if(lista->cont==0)
            lista->ultimo = novo_no;
        
        lista->cont++;
        return TRUE;
    }

    ptr_no_atual = percorre_no(lista, pos);

    novo_no->proximo = ptr_no_atual->proximo;
    ptr_no_atual->proximo = novo_no;
    lista->cont++;

    return TRUE;
}

/** Remove um @code{item} no inicio de @code{lista}
 *  @param lista Lista na qual será removio um @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{lista}.
 *  @return se @code{err} é FALSE, @code{item} de @code{lista} na posição 0 (zero).
 *          ERR_LISTA_VAZIA se não existem itens em @code{lista}. */
int remover(Lista *lista, int *err){
    if(estah_vazia(lista)){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    NO *ptr_auxiliar = lista->primeiro;
    lista->primeiro = lista->primeiro->proximo;
    free(ptr_auxiliar);
    *err = FALSE;
    lista->cont--;

    return FALSE;
}

/** Remove um @code{item} na posição @code{pos} de @code{lista}
 *  @param lista Lista na qual será removio um @code{item}.
 *  @param pos  Posição na qual será retirado o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_LISTA_VAZIA se não existem itens em @code{lista}.
 *  @return se @code{err} é FALSE, @code{item} de @code{lista} na posição @code{pos}.
 *          ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho) */
int remover_posicao(Lista *lista, int pos, int *err){
    NO *ptr_no_atual, *ptr_auxiliar;

    if(estah_vazia(lista)){
        *err = ERR_LISTA_VAZIA;
        return ERR_LISTA_VAZIA;
    }

    if(pos < 0 || pos >= lista->cont){
        *err = ERR_INDICE_INVALIDO;
        return ERR_INDICE_INVALIDO;
    }

    if(pos==0){
        ptr_auxiliar = lista->primeiro;
        lista->primeiro = lista->primeiro->proximo;
    } else{
        ptr_no_atual = percorre_no(lista, pos);
        ptr_auxiliar = ptr_no_atual->proximo;
        ptr_no_atual->proximo = ptr_auxiliar->proximo;
    }
    free(ptr_auxiliar);
    lista->cont--;

    *err = FALSE;
    return FALSE;
}

/** Recupera de @code{lista} o @code{item} na posição @code{pos}
 *  @param lista Lista que contém o @code{item}.
 *  @param pos  Posição na qual está o @code{item}.
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return se @code{err} == FALSE, valor de @code{lista} na posição @code{pos}
 *          caso contrário, TRUE indicando que houve erro. */
int obter_item(Lista *lista, int pos, int *err){
    NO* ptr_no_atual;
    if(pos < 0 || pos >= lista->cont){
        *err = ERR_INDICE_INVALIDO;
        return TRUE;
    }

    ptr_no_atual = percorre_no(lista, pos);
    ptr_no_atual = ptr_no_atual->proximo;
    *err = FALSE;
    return ptr_no_atual->item;
}

/** Atribui novo @code{item} à posição @code{pos} de @code{lista}
 *  @param lista Lista que contém os items.
 *  @param pos  Posição na qual será atribuído o @code{item}.
 *  @param item Novo item que sera atribuído a @code{lista}
 *  @param err  Código de erro.
 *              FALSE caso não ocorre nenhum erro.
 *              ERR_INDICE_INVALIDO se posição @code{pos} não é válida (@code{pos} < 0 || @code{pos} >= tamanho)
 *  @return FALSE se não for possível atribuir @code{item} em @code{lista} na posição @code{pos}.
 *          TRUE se for possível. */
int atribuir_valor(Lista *lista, int pos, int item, int *err){
    NO *ptr_no_atual;
    if(pos < 0 || pos >= lista->cont){
        *err = ERR_INDICE_INVALIDO;
        return FALSE;
    }

    ptr_no_atual = percorre_no(lista, pos);
    ptr_no_atual = ptr_no_atual->proximo;
    ptr_no_atual->item = item;
    *err = FALSE;

    return TRUE;
}

/** Indica se @code{lista} possui ou não itens.
 *  @param lista Lista na qual se verificará estar vazia ou não.

 *  @return TRUE se @code{lista} é NULL ou se tamanho é igual a 0 (zero).
 *          FALSE se tamanho > 0 (zero). */
int estah_vazia(Lista *lista){
    return lista->cont==0;
}

/** Recupera número de itens de @code{lista}.
 *  @param lista Lista que se quer saber o tamanho.
 *  @return Número de itens mantidos na  @code{lista}.*/
int tamanho(Lista *lista){
    return lista->cont;
}

/************************************************************************************/

void imprimir_lista(Lista *lista) {
    NO *ptr_no_atual = lista->primeiro;

    if(estah_vazia(lista))
        return;
    
    printf("%d\n", ptr_no_atual->item);
    while(ptr_no_atual->proximo!=NULL){
        ptr_no_atual = ptr_no_atual->proximo;
        printf("%d\n", ptr_no_atual->item);
    } 
}

int main()
{
    Lista *lista = criar_lista();

    char token[3];
    int valor, posicao, result, erro;

    scanf("%s", token);

    while (token[0] != 'Q') {
        if (strcmp(token, "AF") == 0) {      // adicionar no fim
            scanf(" %d", &valor);
            inserir(lista, valor);
        }
        else if (strcmp(token, "AP") == 0) { // adicionar numa posição
            scanf(" %d", &posicao);
            scanf(" %d", &valor);
            result = inserir_posicao(lista, valor, posicao);

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
            valor = remover(lista, &erro);
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
            remover_posicao(lista, posicao, &erro);
            if (erro == ERR_INDICE_INVALIDO)
                printf("PosicaoInvalidaException\n");                
        }
        else if (token[0] == 'S') {  // setar valor numa posição
            scanf(" %d", &posicao);
            scanf(" %d", &valor);
            atribuir_valor(lista, posicao, valor, &erro);
            if (erro == ERR_INDICE_INVALIDO)
                printf("PosicaoInvalidaException\n");    
        }        
        else if (token[0] == 'G') {  // obter valor numa posição
            scanf(" %d", &posicao);
            obter_item(lista, posicao, &erro);
            if (erro == ERR_INDICE_INVALIDO)
            printf("PosicaoInvalidaException\n");
        }
        else if (token[0] == 'T') {  // tamanho da lista
            printf("%d\n", tamanho(lista));
        }
        else if (token[0] == 'V') {   // lista está vazia
            if (estah_vazia(lista))
                printf("true\n");
            else
                printf("false\n");
        }
        else if (token[0] == 'P')    // imprimir lista
            imprimir_lista(lista);
        else
            printf("ERRO %s\n", token);

        fflush(stdin);
        scanf("%s", token);
    }

    return 0;
}