#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char* pilhaArr;
    int cont, lim;
} Pilha;

Pilha *cria_pilha(){
    Pilha *nova_piha = (Pilha*) malloc(sizeof(Pilha));
    nova_piha->cont = 0;
    nova_piha->lim = 1001;
    nova_piha->pilhaArr = (char*) calloc(1001, sizeof(char));

    return nova_piha;
}

void empilhar(Pilha *pilha, char caracter){
    if(pilha->lim == pilha->cont){
        pilha->lim+=1001;
        pilha->pilhaArr = (char*) realloc(pilha->pilhaArr, pilha->lim);
    }

    pilha->pilhaArr[pilha->cont] = caracter;
    pilha->cont++;
}

int desempilhar(Pilha *pilha){
    if(pilha->cont<1)
        return 0;
    
    char item = pilha->pilhaArr[pilha->cont-1];
    pilha->pilhaArr[pilha->cont-1] = '\0';
    pilha->cont--;

    return (int) item;
}

int verifica_pilha(Pilha *pilha, char *exp){
    int tam_exp = strlen(exp);
    for(int i = 0; i < tam_exp; i++){
        if(exp[i]=='(')
            empilhar(pilha, '(');
        else if(exp[i]==')'){
            if(!desempilhar(pilha))
                return 0;
        }
    }
    
    return pilha->cont==0;
}

int main(){
    int ver;
    char expressao[1001];
    Pilha *pilha;

    while(scanf(" %s", expressao)!=EOF){
        pilha = cria_pilha();
        ver = verifica_pilha(pilha, expressao);
        
        if(ver)
            printf("correct\n");
        else
            printf("incorrect\n");

        free(pilha);
    }
    

    return 0;
}