#include <stdio.h>
#include <stdlib.h>

void preencheInfo(int *m, char *l, int q){
    for(int i = 0; i<q; i++)
        scanf("%d %c", &m[i], &l[i]);
}

int verificaPares(int *m, char *l, int **mtr, int n){
    int pares = 0;

    for(int i = 0; i < n; i++){
        if(l[i]=='D'){
            if(mtr[1][m[i]]>=1){
                pares++;
                mtr[1][m[i]]--;
                continue;
            }
            mtr[0][m[i]]++;
        }else{
            if(mtr[0][m[i]]>=1){
                pares++;
                mtr[0][m[i]]--;
                continue;
            }
            mtr[1][m[i]]++;
        }
    }

    return pares;
}

int main(){
    int n, **peDirEsq, *m;
    char *l;
    while(scanf("%d", &n)!=EOF){
        m = (int *) malloc(n*sizeof(int));
        l = (char *) malloc(n*sizeof(char));

        peDirEsq = (int **) calloc(2, sizeof(int*));
        for(int i = 0; i<2; i++)
            peDirEsq[i] = (int *) calloc(61, sizeof(int));

        preencheInfo(m, l, n);
        printf("%d\n", verificaPares(m, l, peDirEsq, n));

        free(m);
        free(l);
        for(int i = 0; i<2; i++)
            free(peDirEsq[i]);
        free(peDirEsq);
    }
    return 0;
}