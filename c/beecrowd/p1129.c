#include <stdio.h>

int main(){
    int n, alt[5], qr = 0;
    char resp;

    while(n!=0){
        scanf("%d", &n);
        for(int cont = 1; cont<=n; cont++){
            for(int i = 65; i<70; i++)
                scanf("%d", &alt[i]);
            for(int i = 65; i<70; i++)
                if(alt[i]<=127){
                    qr++;
                    resp = (char) i;
                }
            if(qr!=1)
                resp = '*';
            printf("%c\n", resp);
            qr = 0;
        }
    }
    return 0;
}