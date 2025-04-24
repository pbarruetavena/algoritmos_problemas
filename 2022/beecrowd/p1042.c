#include <stdio.h>

int main(){
    int a, b, c, max, min, med;
    scanf("%d%d%d", &a, &b, &c);
    max = a;
    med = b;
    min = c;
    
    if(c>a){
        min = a;
        max = c;
    }
    if(b>max){
        med = max;
        max = b;
    }
    else if(b<min){
        med = min;
        min = b;
    }

    printf("%d\n%d\n%d\n\n%d\n%d\n%d\n", min, med, max, a, b, c);
    return 0;
}