#include <stdio.h>

int main()
{
    int distancia;
    float combustivel, consumo;

    //leitura dos dados
    scanf("%d %f", &distancia, &combustivel);

    //calculo
    consumo = distancia/combustivel;

    //resultado
    printf("%.3f km/l\n", consumo);

    return 0;
}