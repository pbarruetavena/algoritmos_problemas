#include <stdio.h>
 
int main() {
    char nome[100];
    double salario, venda, comissao;
    scanf("%s", nome);
    scanf(" %lf %lf", &salario, &venda);
    comissao = 0.15 * venda;
    printf("TOTAL = R$ %.2f\n", salario + comissao);
    return 0;
}