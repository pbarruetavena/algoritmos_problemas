#include <stdio.h>

int main(){
    char ope;
    double m[12][12], valor;

    scanf("%c", &ope);
    for(int l = 0; l<12; l++)
        for(int c = 0; c<12; c++)
            scanf("%lf", &m[l][c]);

    for(int l = 0; l<12; l++)
        for(int c = 10-l; c>=0; c--)
            valor+=m[l][c];

    if(ope=='M')
        valor/=66;

    printf("%.1f\n", valor);

    return 0;
}