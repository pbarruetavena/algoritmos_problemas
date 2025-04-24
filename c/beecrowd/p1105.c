#include <stdio.h>

int main(){
        int b, n, ver = 0, rmone[21], deb[20][3];

        while(1){
        scanf("%d %d", &b, &n);
        if(b==0 || n==0)
            break;

        for(int i = 1; i<=b; i++)
            scanf("%d", &rmone[i]);
        for(int i = 0; i<n; i++)
            for(int j = 0; j<3; j++)
                scanf("%d", &deb[i][j]);
            
        for(int i = 0; i<n; i++){
            rmone[deb[i][0]]-=deb[i][2];
            rmone[deb[i][1]]+=deb[i][2];
        }

        ver = 0;
        for(int i = 1; i<=b; i++){
            if(rmone[i]>=0)
                ver++;
        }

        if(ver==b)
            printf("S\n");
        else
            printf("N\n");
    }
    return 0;
}