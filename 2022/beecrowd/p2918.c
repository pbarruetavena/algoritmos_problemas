#include <stdio.h>

int main(){
    int dig[11] = {0}, l, r, n, soma, unid;
    while(scanf("%d%d", &l, &r)!=EOF){
        for(int i = 0; i<11; i++)
            dig[i] = 0;
        soma = 0;
        n = l;
        do{
            unid = l%10;
            for(int i = 0; i<11; i++){
                dig[i] = n%10;
                n = n/10;
                soma += dig[i];
            }
            l++;
        }while(unid!=0 && l<r);
        for(; l<=r; l++){
            dig[0]+=1;
            soma+=dig[0];
        }
        printf("%d\n", soma);
    }
    return 0;
}