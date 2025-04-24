#include <stdio.h>
#include <stdlib.h>

void carregaMatriz(int** m, int n){
    for(int i = 0; i<n; i++)
        for(int j = 0; j<n; j++)
            *m = (int *) malloc(n*sizeof(int));
}
void printaMatriz(int** m, int n){
    for(int l = 0; l<n; l++){
            for(int c = 0; c<n; c++)
                if(c<n-1)
                    printf("%3d ", m[l][c]);
                else
                    printf("%3d\n", m[l][n-1]);
        }
        printf("\n");
}

void preencheMatriz(int** m, int n){
    int ctr = (n+1)/2;
    for(int cmda = 1, ini = 0, fim = 0; cmda<=ctr; cmda++){
        ini = cmda-1;
        fim = n-cmda;
        for(int i = ini; i<=fim; i++){
            m[ini][i] = cmda;
        }
        for(int i = ini; i<=fim; i++){
            m[fim][i] = cmda;
        }
        for(int i = ini; i<=fim; i++){
            m[i][ini] = cmda;
        }
        for(int i = ini; i<=fim; i++){
            m[i][fim] = cmda;
        }
    }
}

void freeMatriz(int** m, int n){
    for(int i = 0; i<n; i++)
        free(*(m+i));
    free(m);
}

int main(){
    int tam, **matriz;
    scanf("%d", &tam);
    while(tam!=0){
        carregaMatriz(matriz, tam);
        for(int i = 0; i<tam; i++)
            for(int j = 0; j<tam; j++)
                matriz[i][j] = 2;
        //preencheMatriz(matriz, tam);
        //printaMatriz(matriz, tam);
        for(int i = 0; i<tam; i++)
            for(int j = 0; j<tam; j++)
                printf("%d \n", matriz[i][j]);
        freeMatriz(matriz, tam);
    }
    return 0;
}