#include <stdio.h>

int main(){
    int q, i = 0;
    double valor, vmon[12] = {100, 50, 20, 10, 5, 2, 100, 50, 25, 10, 5, 1};
    scanf("%lf", &valor);

    printf("NOTAS:\n");
    for(; i<6; i++){
        q = (int) valor/vmon[i];
        printf("%d nota(s) de R$ %.2f\n", q, vmon[i]);
        valor = valor - q*vmon[i];
    }
    printf("MOEDAS:\n");
    valor = valor*100;
    for(; i<12; i++){
        q = (int) valor/vmon[i];
        printf("%d moeda(s) de R$ %.2f\n", q, vmon[i]/100);
        valor = valor - q*vmon[i];
    }
    return 0;
}