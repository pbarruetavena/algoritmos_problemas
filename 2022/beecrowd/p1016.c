#include <stdio.h>

int main()
{
    /*
        30 km/h - velocidade relativa entre Y e X
        30 km/h = 30km/ 1h = 30km/ 60min = 1km/ 2 min = 1/2 km/min

        velocidade = distancia/tempo - velocidade*tempo=distancia
        tempo = distancia/velocidade - tempo = dist/(1/2)
        tempo = dist*2
    */

    int km, min;
    scanf("%d", &km);
    min = km*2;

    printf("%d minutos\n", min);
    
    return 0;
}