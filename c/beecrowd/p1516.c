#include <stdio.h>
#include <string.h>

int main(){
    int n, m, a, b, clin, ccol;
    char dOriginal[102][102] = {};
    char dDimensao[102][102] = {};

    while(1){

        for(int i =0; i<102; i++)
            for(int j = 0; j<102; j++){
                dOriginal[i][j] = '\0';
                dDimensao[i][j] = '\0';
            }

        scanf("%d %d", &n, &m);
        getchar();
        if(n==0 || m==0)
            return 0;
        
        for(int i = 0; i<n; i++){
            scanf("%[^\n]", dOriginal[i]);
            getchar();
        }
        scanf("%d %d", &a, &b);
        getchar();
        clin = a / n;
        ccol = b / m;

        for(int i = 0; i<a; i++){
            for(int j = 0; j<b; j++){
                dDimensao[i][j] = dOriginal[i/clin][j/ccol];
            }
        }
        
        for(int i = 0; i<a; i++){
            printf("%s\n", dDimensao[i]);
        }
        printf("\n");
    }

    return 0;
}