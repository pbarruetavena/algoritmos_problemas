#include <stdio.h>
int main(){
    int a, b, c, ma, med, me, n;
    scanf("%d%d%d", &a, &b, &c);
    ma = a;
    med = b;
    me = c;

    if(c>b){
        med = c;
        me = b;
    }
    if(med>ma){
        ma = med;
        med = a;
    }
    if(me>med){
        n = med;
        med = me;
        me = n;
    }

    printf("%d\n%d\n%d\n\n%d\n%d\n%d\n", me, med, ma, a, b, c);
    return 0;
}