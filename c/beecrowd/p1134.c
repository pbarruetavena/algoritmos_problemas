#include <stdio.h>

int main(){
    int n, alc, gas, die;
    alc = gas = die = 0;
    do{
        scanf("%d", &n);
        switch(n){
            case 1: alc++; break;
            case 2: gas++; break;
            case 3: die++;
        }
    } while(n!=4);

    printf("MUITO OBRIGADO\nAlcool: %d\n", alc);
    printf("Gasolina: %d\nDiesel: %d\n", gas, die);
    return 0;
}