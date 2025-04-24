#include <stdio.h>

int main(){
    int cpf, a1, a2, a3, a4, a5, a6, a7, a8, a9, b1, b2;

    while(scanf("%d", &cpf)!=EOF){
        a1 = cpf/100000000;
        cpf = cpf%100000000;
        a2 = cpf/10000000;
        cpf = cpf%10000000;
        a3 = cpf/1000000;
        cpf = cpf%1000000;
        a4 = cpf/100000;
        cpf = cpf%100000;
        a5 = cpf/10000;
        cpf = cpf%10000;
        a6 = cpf/1000;
        cpf = cpf%1000;
        a7 = cpf/100;
        cpf = cpf%100;
        a8 = cpf/10;
        a9 = cpf%10;

        b1 = ((1*a1+2*a2+3*a3+4*a4+5*a5+6*a6+7*a7+8*a8+9*a9)%11)%10;
        b2 = ((9*a1+8*a2+7*a3+6*a4+5*a5+4*a6+3*a7+2*a8+1*a9)%11)%10;

        printf("%d%d%d.%d%d%d.%d%d%d-%d%d\n", a1, a2, a3, a4, a5, a6, a7, a8, a9, b1, b2);
    }

    return 0;
}