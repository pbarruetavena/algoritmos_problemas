#include <stdio.h>
#include <stdlib.h>

int* leSequencia(int *ptrN){
    int *seq;
    scanf("%d", ptrN);
    seq = (int *) malloc((*ptrN)*sizeof(int));
    for(int i = 0; i<(*ptrN); i++)
        scanf("%d", &seq[i]);
    return seq;
}

int verificaSequencia(int *seq, int *n){
    int cec = 0;
    for(int i = 0; i<(*n); i++)
        for(int j = i, soma = 0; j<(*n); j++){
            soma+=seq[j];
            if(soma==0)
                cec++;
        }
    return cec;
}

int main(){
    int n;
    int* x = leSequencia(&n);
    int nulas = verificaSequencia(x, &n);
    printf("%d\n", nulas);
    free(x);
    return 0;
}