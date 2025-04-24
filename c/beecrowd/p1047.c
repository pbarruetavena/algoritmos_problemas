#include <stdio.h>

int main()
{
    //declaração de variáveis e entrada de dados
    int hI, hF, minI, minF, tm;
    scanf("%d %d %d %d", &hI, &minI, &hF, &minF);

    tm = (hF*60+minF) - (hI*60+minI);
    tm = (tm + 1440 - 1)%1440;
    tm = tm + 1;

    printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", tm/60, tm%60);
    // tm/60 = horas | tm%60 = minutos
    
    return 0;
}