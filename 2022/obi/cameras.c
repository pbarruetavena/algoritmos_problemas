#include <stdio.h>
#include <stdlib.h>

int main(){
    int n, m, k, col, lin, ver = 1;
    char dir;
    int mapa[32][32] = {};
    scanf("%d %d %d", &n, &m, &k);
    
    for(int i = 0; i<=n+1; i++){
        mapa[0][i] = 1;
        mapa[m+1][i] = 1;
        mapa[i][0] = 1;
        mapa[i][n+1] = 1;
    }


    for(int i = 0; i<k; i++){
        scanf("%d %d %c", &col, &lin, &dir);

        if(dir=='N'){
            for(int i = lin; i >= 1; i--)
                mapa[i][col] = 1;
        } else if(dir=='S'){
            for(int i = lin; i <= m; i++)
                mapa[i][col] = 1;
        } else if(dir=='O'){
            for(int i = col; i >= 1; i--)
                mapa[lin][i] = 1;
        } else if(dir=='L'){
            for(int i = col; i <= n; i++)
                mapa[lin][i] = 1;
        }
    }

    if(mapa[1][1]==1 || mapa[m][n]==1){
        printf("N\n");
        return 0;
    }
    mapa[1][1] = 3;

    while(ver==1){
        ver = 0;
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++)
                if(mapa[i][j]==3){
                    if(mapa[i-1][j]==0){
                        mapa[i-1][j] = 3;
                        ver = 1;
                    } if(mapa[i+1][j]==0){
                        mapa[i+1][j] = 3;
                        ver = 1;
                    } if(mapa[i][j-1]==0){
                        mapa[i][j-1] = 3;
                        ver = 1;
                    } if(mapa[i][j+1]==0){
                        mapa[i][j+1] = 3;
                        ver = 1;
                    }
                }
            if(mapa[m][n]==3){
                printf("S\n");
                return 0;
            }
        }
    }
    
    printf("N\n");

    return 0;
}