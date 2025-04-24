#include <stdio.h>

int main(){
    int p, j1, j2, r, a, g;
    scanf("%d%d%d%d%d", &p, &j1, &j2, &r, &a);

    p = (p+1)%2;

    if(r==1)
        if(a==0)
            g = 1;
        else
            g = 2;
    else
        if(a==1)
            g = 1;
        else if((j1+j2)%2==p)
            g = 1;
        else
            g = 2;

    printf("Jogador %d ganha!\n", g);
    return 0;
}