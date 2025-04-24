#include <stdio.h>
#include <string.h>

int main(){
    char d[102] = {}, s[102] = {};
    while(scanf("%[^\n]", d)!=EOF){
        getchar();
        scanf("%[^\n]", s);
        getchar();
        if(strstr(d, s)==NULL)
            printf("Nao resistente\n");
        else
            printf("Resistente\n");
    }
    return 0;
}