#include <stdio.h>

int main(){
    double sal, ajuste, nsal;
    scanf("%lf", &sal);

    if(sal<=400)
        ajuste = 0.15;
    else if(sal<=800)
        ajuste = 0.12;
    else if(sal<=1200)
        ajuste = 0.10;
    else if(sal<=2000)
        ajuste = 0.07;
    else
        ajuste = 0.04;

    nsal = sal*(1+ajuste);
    printf("Novo salario: %.2f\nReajuste ganho: %.2f\nEm percentual: %.0f %%\n", nsal, ajuste*sal, ajuste*100);

    return 0;
}