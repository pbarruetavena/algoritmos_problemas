#include <stdio.h>
int main(){
    int a, b, c;
    char j;
    while(scanf("%d%d%d", &a, &b, &c) != EOF){
        if(a!=b)
            if(a==c)
                j = 'B';
            else
                j = 'A';
        else
            if(a==c)
                j = '*';
            else
                j = 'C';
        printf("%c\n", j);
    }

    return 0;
}