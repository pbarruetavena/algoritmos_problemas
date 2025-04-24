#include <stdio.h>
#include <stdlib.h>

void calculaDiv(int a, int b, int *quo, int *rst){
    *quo = a / b;
    *rst = a - b*(a/b);
}

int main(){
    int a, b, q, r;
    scanf("%d %d", &a, &b);
    calculaDiv(a, b, &q, &r);
    printf("%d %d", q, r);

    return 0;
}
