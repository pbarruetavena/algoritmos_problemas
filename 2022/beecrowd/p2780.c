#include <stdio.h>
int main(){
    int d, r;
    scanf("%d", &d);
    r = ((d-201)/600) + 1;
    printf("%d\n", r);
    return 0;
}