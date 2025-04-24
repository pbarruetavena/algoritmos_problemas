#include <stdio.h>
#include <string.h>



void consertaFrase(char *frase){
    char *erro;
    while(1){
        erro = strstr(frase, "oulupukk");
        if(erro==NULL)
            return;
        *--erro = 'J';
        erro+=9;
        *erro = 'i';
        frase = erro;
    }
}

int main(){
    int n;
    char frase[101];
    scanf("%d", &n);
    getchar();

    for(int qfrases = 0; qfrases<n; qfrases++){
        fgets(frase, 101, stdin);
        consertaFrase(frase);
        printf("%s", frase);
    }

    return 0;
}