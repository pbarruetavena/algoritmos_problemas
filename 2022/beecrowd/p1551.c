#include <stdio.h>
#include <string.h>


int verChar(char caracter){
    char cIgnorar[] = {' ', ',', '\n'};
    for(int j = 0; j<3; j++){
        if(caracter==cIgnorar[j])
            return 0;
    }
    return 1;
}

int qntLetras(char *frase){
    int letras = 0, caracteres[26] = {}, c;

    for(int i = 0; frase[i]!='\0'; i++){
        if(verChar(frase[i])==0)
            continue;
        
        c = (int) frase[i] - 97;

        if(c == -218)
            c = 2;

        if(caracteres[c]==0){
            caracteres[c] = 1;
            letras++;
        }
    }

    return letras;
}

int main(){
    int n, letras;
    char frase[2001] = {};

    scanf("%d", &n);
    for(int i = 0; i<n; i++){
        getchar();
        fgets(frase, 1001, stdin);

        letras = qntLetras(frase);

        if(letras==26)
            printf("frase completa\n");
        else if(letras>=13)
            printf("frase quase completa\n");
        else 
            printf("frase mal elaborada\n");
    }
    return 0;
}