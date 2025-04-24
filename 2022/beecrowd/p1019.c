#include <stdio.h>

int main()
{
    //declaração e leitura das variáveis
    int n, x;
    scanf("%d", &n);

    x = n/3600; //calculo das horas / 1 h = 3600 seg
    printf("%d", x);
    n = n%3600; //tira as horas já marcadas

    x = n/60;   //calculo dos minutos / 1 min = 60 seg
    printf(":%d", x);
    n = n%60;   //desconta os minutos já marcados

    printf(":%d\n", n); //os segundos são o tempo restante

    return 0;
}