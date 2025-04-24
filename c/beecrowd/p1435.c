#include <stdio.h>

int main(){
    int n, m[100][100] = {}, ctr;

    scanf("%d", &n);
    while(n!=0){
        ctr = (n+1)/2;
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

        for(int l = 0; l<n; l++){
            for(int c = 0; c<n; c++)
                if(c<n-1)
                    printf("%3d ", m[l][c]);
                else
                    printf("%3d\n", m[l][n-1]);
        }
        printf("\n");
        scanf("%d", &n);
    }
    printf("\n");
    return 0;
}