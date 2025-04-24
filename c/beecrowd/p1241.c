#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *verEncaixa(char *a, char *b){
    int ta = strlen(a),tb = strlen(b);
    if(ta<tb)
        return "não encaixa";
    for(int i = 0; i<tb; i++)
        if(a[i+ta-tb]!=b[i])
            return "não encaixa";
    return "encaixa";
}

int main(){
    int n;
    char a[1001] = {}, b[1001] = {};
    scanf("%d", &n);
    for(int i = 0; i<n; i++){
        scanf(" %s", a);
        scanf(" %s", b);
        //printf("*%s*\n", a);
        //printf("*%s*\n", b);
        printf("%s\n", verEncaixa(a, b));
    }
}