#include <stdio.h>

int main(){
    int cod;
    char a = 32, b = 32, c = 32, d = 32, e = 32;
    while(scanf(" %c%c", &a, &b)!=EOF){
        a = (int) a-64;
        b = (int) b-64;
        if(a>0 && a<27){
            if(b<=0 || b>=27)
                cod = a;
            else{
                c = getchar();
                c = (int) c-64;
                if(c<=0 || c>=27){
                    cod = a*26 + b;
                } else{
                    if((d = getchar())!=10)
                        d = (int) d-64;
                    cod = a*26*26 + b*26 + c;
                    if(d == -54)
                        while(e!=10)
                            e = getchar();
                }
            }
            if(cod> 24*26*26 + 6*26 + 4 || (d>0 && d<27))
                printf("Essa coluna nao existe Tobias!\n");
            else
                printf("%d\n", cod);
        }
        fflush(stdin);
        a = b = c = d = e = 32;
    }

    return 0;
}