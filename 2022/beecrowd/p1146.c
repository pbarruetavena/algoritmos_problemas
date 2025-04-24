#include <stdio.h>

void contagem(int max){
    for(int n = 1; n<max; n++)
        printf("%d ", n);
    printf("%d\n", max);
}

int main(){
    int x;
    while(1){
        scanf("%d", &x);
        if(x==0)
            break;
        contagem(x);
    }
    return 0;
}