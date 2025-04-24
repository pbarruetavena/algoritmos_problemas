#include <stdio.h>
#include <stdlib.h>

int main(){
    int x, y, s = 0;
    scanf("%d%d", &x, &y);
    if(x==y)
        s = 0;
    else{
        x=x+(y-x)/abs(y-x);
        for(; x!=y; x=x+(y-x)/abs(y-x)){
            if(x%2!=0)
                s+=x;
        }
    }
    
    printf("%d\n", s);

    return 0;
}