#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int percorreArray(int n, char **listaPomekon){
    char pomekon[1010] = {};
    int cont = 0, ver;
    for(int i = 0; i<n; i++){
        scanf("%[^\n]s", pomekon);
        getchar();
        ver = 0;
        for(int j = 0; j<cont; j++)
            if(strcmp(pomekon, listaPomekon[j])==0){
                ver = 1;
                break;
            }
        if(ver==0){
            strcpy(listaPomekon[cont], pomekon);
            cont++;
        }
    }
    return 151 - cont;
}

int main(){
    int n;
    char **listaPomekon;

    scanf("%d", &n);
    getchar();
    listaPomekon = (char**) malloc(n*sizeof(char*));
    for(int i = 0; i<n; i++)
        listaPomekon[i] = (char*) malloc(1010*sizeof(char));

    printf("Falta(m) %d pomekon(s).\n", percorreArray(n, listaPomekon));

    return 0;
}