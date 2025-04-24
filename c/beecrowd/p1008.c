#include <stdio.h>
int main()
{
    int num_func, horas; // número do funcionario e horas trabalhadas
    float sal_hora, salario; // salário p/ hora e salário total

    //leitura dos dados
    scanf("%d %d", &num_func, &horas);
    scanf("%f", &sal_hora);

    //calculo
    salario = sal_hora*horas;

    //resultados
    printf("NUMBER = %d\nSALARY = U$ %.2f\n", num_func, salario);
    return 0;
}