#include <stdio.h> 

int main(){
    int t, n[1000];
    scanf("%d", &t);

    for(int i = 0; i<1000; i++)
        n[i] = i%t;
    for(int i = 0; i<1000; i++)
        printf("N[%d] = %d\n", i, n[i]);

    return 0;
}