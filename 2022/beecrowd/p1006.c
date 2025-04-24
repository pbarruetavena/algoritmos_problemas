#include <stdio.h>

int main(){
    double A, B, C, MEDIA;

    //leitura dos valores
    scanf("%lf %lf %lf", &A, &B, &C);

    //calculo da média
    MEDIA = ((A*2)+(B*3)+(C*5))/10;

    //resultado
    printf("MEDIA = %.1f\n", MEDIA);

    return 0;
}