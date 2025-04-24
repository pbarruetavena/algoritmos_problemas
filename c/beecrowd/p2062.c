#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void corrigePalavras(char **palavras, int n){
    for(int i = 0; i<n; i++)
        if(strlen(palavras[i])==3){
            if(palavras[i][0]=='O' && palavras[i][1]=='B')
                palavras[i][2] = 'I';
            if(palavras[i][0]=='U' && palavras[i][1]=='R')
                palavras[i][2] = 'I';
        }
}

int main(){
    int n;
    char **palavras;
    scanf("%d", &n);

    palavras = (char**) malloc(n*sizeof(char*));
    for(int i = 0; i<n; i++)
        palavras[i] = (char*) malloc(21*sizeof(char));

    for(int i = 0; i<n; i++)
        scanf("%s", palavras[i]);

    corrigePalavras(palavras, n);
    for(int i = 0; i<n-1; i++)
        printf("%s ", palavras[i]);
    printf("%s\n", palavras[n-1]);
    
    return 0;
}