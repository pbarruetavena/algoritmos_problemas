#include <stdio.h>

int main(){
    int n, m, x = 0, y = 0, ver = 0;
    int mat[1000][1000];
    scanf("%d%d", &n, &m);
    for(int l = 0; l<n; l++)
        for(int c = 0; c<m; c++)
            scanf("%d", &mat[l][c]);

    for(int l = 1; l<n-1; l++)
        for(int c = 1; c<m-1; c++)
            if(mat[l][c]==42){
                for(int i = l-1; i<=l+1; i++)
                    for(int j = c-1; j<=c+1; j++)
                        if(mat[i][j]==7)
                            ver++;
                if(ver==8){
                    x = l+1;
                    y = c+1;
                    break;
                } else  
                    ver = 0;
            }
        
    printf("%d %d\n", x, y);

    return 0;
}