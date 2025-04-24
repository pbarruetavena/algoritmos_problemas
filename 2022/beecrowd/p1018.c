#include <stdio.h>
int main()
{
    //quantidade de notas de determinado valor, valor restante e total
    int q_notas, valor_restante;

    //leitura do valor
    scanf("%d", &valor_restante);
    printf("%d\n", valor_restante);

    //notas de 100
    q_notas = valor_restante/100;
    printf("%d nota(s) de R$ 100,00\n", q_notas);
    valor_restante = valor_restante%100;

    //notas de 50
    q_notas = valor_restante/50;
    printf("%d nota(s) de R$ 50,00\n", q_notas);
    valor_restante = valor_restante%50;

    //notas de 20
    q_notas = valor_restante/20;
    printf("%d nota(s) de R$ 20,00\n", q_notas);
    valor_restante = valor_restante%20;

    //notas de 10
    q_notas = valor_restante/10;
    printf("%d nota(s) de R$ 10,00\n", q_notas);
    valor_restante = valor_restante%10;

    //notas de 5
    q_notas = valor_restante/5;
    printf("%d nota(s) de R$ 5,00\n", q_notas);
    valor_restante = valor_restante%5;

    //notas de 2
    q_notas = valor_restante/2;
    printf("%d nota(s) de R$ 2,00\n", q_notas);
    valor_restante = valor_restante%2;

    //notas de 1
    q_notas = valor_restante;
    printf("%d nota(s) de R$ 1,00\n", q_notas);
    
    return 0;
}