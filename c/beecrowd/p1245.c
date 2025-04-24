#include <stdio.h>

int main(){
    int n, m, tamd[31], tame[31], pares;
    char l;

    while(scanf("%d", &n)!=EOF){

        for(int i = 0; i<31; i++){
            tamd[i] = 0;
            tame[i] = 0;
        }
        pares = 0;

        for(int i = 0; i<n; i++){
            scanf("%d %c", &m, &l);
            if(l=='D')
                tamd[m-30]++;
            else
                tame[m-30]++;
            if(tamd[m-30]>=1 && tame[m-30]>=1){
                pares++;
                tamd[m-30]--;
                tame[m-30]--;
            }
        }

        printf("%d\n", pares);
    }
    return 0;
}