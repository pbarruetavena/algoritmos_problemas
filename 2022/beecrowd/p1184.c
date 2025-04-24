#include <stdio.h>

int main(){
    char ope;
    double m[12][12], valor = 0;

    scanf("%c", &ope);
    for(int l = 0; l<12; l++)
        for(int c = 0; c<12; c++)
            scanf("%lf", &m[l][c]);

    for(int l = 0; l<12; l++)
        for(int c = 0; c<l; c++)
            valor+=m[l][c];
    
    if(ope=='M')
        valor/=66;
    printf("%.1f\n", valor);
    
    return 0;
}