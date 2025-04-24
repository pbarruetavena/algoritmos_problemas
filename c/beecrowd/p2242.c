#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *tiraConsoante(char *palavra){
    int i = 0, qVogais = 0;
    char vogaisAlfabeto[] = "aeiou";
    char *vogaisPalavra;
    vogaisPalavra = (char *) malloc(51*sizeof(char));

    while(palavra[i]!='\0'){
        for(int j = 0; j<5; j++)
            if(palavra[i]==vogaisAlfabeto[j]){
                vogaisPalavra[qVogais] = palavra[i];
                    qVogais++;
            }
        i++;
    }

    vogaisPalavra[qVogais] = '\0';
    return vogaisPalavra;
}

char verificaRisada(char *vogais){
    int tam = strlen(vogais);
    int meio = (tam+1)/2;

    for(int i = 0; i<meio; i++){
        if(vogais[i]!=vogais[tam-1-i]){
            return 'N';
        }
    }

    return 'S';
}

int main(){
    char risada[50] = {}, *vogaisPalavra;
    scanf("%s", risada);
    vogaisPalavra = tiraConsoante(risada);
    printf("%c\n", verificaRisada(vogaisPalavra));
    free(vogaisPalavra);
    return 0;
}