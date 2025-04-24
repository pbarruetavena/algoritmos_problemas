#include <stdio.h>
#include <string.h>

int main(){
    char palavra[101] = {};
    while(scanf("%[^\n]", palavra)!=EOF){
        getchar();
        for(int i = strlen(palavra), j = 0; i!=0; i--, j++){
            for(int k = 0; k<j; k++)
                printf(" ");
            for(int k = 0; k<i-1; k++)
                printf("%c ", palavra[k]);
            printf("%c\n", palavra[i-1]);
        }
        printf("\n");
    }
    return 0;
}