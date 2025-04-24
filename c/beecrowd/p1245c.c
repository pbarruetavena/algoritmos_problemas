#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int qDireito, qEsquerdo;
} Bota;

int verMenor(int x, int y){
    int menor = x;
    if(y<menor)
        menor = y;
    return menor;
}

void preencheInfo(Bota *botas, int n){
    int m;
    char l;
    for(int i = 0; i<n; i++){
        scanf("%d %c", &m, &l);
        if(l=='D')
            botas[m].qDireito++;
        else
            botas[m].qEsquerdo++;
    }
}

int verificaPar(Bota *botas, int n){
    int pares = 0;
    for(int i = 30; i<61; i++){
        pares+=verMenor(botas[i].qDireito, botas[i].qEsquerdo);
    }

    return pares;
}

int main(){
    int n;
    Bota *botas;
    while(scanf("%d", &n)!=EOF){
        botas = (Bota*) calloc(61, sizeof(Bota));

        preencheInfo(botas, n);
        printf("%d\n", verificaPar(botas, n));

        free(botas);
    }
    return 0;
}
