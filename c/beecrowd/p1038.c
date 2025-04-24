#include <stdio.h>

int main(){
    int cod, quant;
    double preco;
    scanf("%d%d", &cod, &quant);
    if(cod==1)
        preco = quant*4;
    else if(cod==2)
        preco = quant*4.5;
    else if(cod==3)
        preco = quant*5;
    else if(cod==4)
        preco = quant*2;
    else 
        preco = quant*1.5;
    printf("Total: R$ %.2f\n", preco);

    return 0;
}