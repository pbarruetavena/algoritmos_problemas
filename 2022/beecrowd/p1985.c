#include <stdio.h>

int main(){
    int p, q, cod;
    double preco = 0;
    scanf("%d", &p);
    for(int i=1; i<=p;i++){
        scanf("%d %d", &cod, &q);
        switch(cod){
            case 1001: preco+=1.5*q; break;
            case 1002: preco+=2.5*q; break;
            case 1003: preco+=3.5*q; break;
            case 1004: preco+=4.5*q; break;
            case 1005: preco+=5.5*q; break;
        }
    }
    printf("%.2f\n", preco);
    return 0;
}