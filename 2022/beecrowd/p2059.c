#include <stdio.h>

int main(){
    int p, j1, j2, r, a, s;
    scanf("%d%d%d%d%d", &p, &j1, &j2, &r, &a);

    s = j1 + j2;
    p = (p+1)%2;

    if(r==1)
        if(a==1)
            printf("Jogador 2 ganha!\n");
        else
            printf("Jogador 1 ganha!\n");
    else if(a==1)
        printf("Jogador 1 ganha!\n");
    else if(((j1+j2)%2)==p)
        printf("Jogador 1 ganha!\n");
    else
        printf("Jogador 2 ganha!\n");

    return 0;
}