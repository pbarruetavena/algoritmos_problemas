#include <stdio.h>

int main(){
    int m, n, s;
    scanf("%d%d", &m, &n);
    while(m>0 && n>0){
        s = 0;
        if(n>m)
            for(;n>=m;m++){
                printf("%d ", m);
                s+=m;
            }
        else
            for(;m>=n;n++){
                printf("%d ", n);
                s+=n;
            }
        printf("Sum=%d\n", s);
        scanf("%d%d", &m, &n);
    }

    return 0;
}