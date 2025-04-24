#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void minusculo(char *palavra, char *palavraMinusculo){
    strcpy(palavraMinusculo, palavra);
    for(int i = 0; palavra[i]!='\0'; i++){
        palavraMinusculo[i] = tolower(palavra[i]);
    }
}

int comparaNomes(char *ultimo, char *nome2){
    for(int i = 0, j = 0; 1; i++, j++){
        if(ultimo[i]=='\0')
            return 2;
        if(nome2[2]=='\0')
            return 1;
        
        if(ultimo[i]==32)
            i++;
        if(nome2[j]==32)
            j++;
        
        if(ultimo[i]>nome2[j])
            return 1;
        else if(ultimo[i]<nome2[i])
            return 2;
    }
}

int main(){
    char nomes[81] = {}, nomeMinusculo[81] = {};
    char ultimoNome[81] = {"        "}, uNomeMinusculo[81] = {};

    while(scanf("%[^\n]", nomes)!=EOF){
        getchar();
        minusculo(nomes, nomeMinusculo);
        minusculo(ultimoNome, uNomeMinusculo);
        if(comparaNomes(uNomeMinusculo, nomeMinusculo)==2){
            strcpy(ultimoNome, nomes);
        }
    }
    printf("%s\n", ultimoNome);
    return 0;
}