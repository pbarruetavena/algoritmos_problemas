#include <stdio.h>

int main(){
    int n, m, l, cm, cl, a;
    int bmar[100][100], bleo[100][100];

    while(scanf("%d", &n)!=EOF){
        scanf("%d%d",&m, &l);
        for(int i = 1; i<=m; i++)
            for(int j = 1; j<=n; j++)
                scanf("%d", &bmar[i][j]);
        for(int i = 1; i<=l; i++)
            for(int j = 1; j<=n; j++)
                scanf("%d", &bleo[i][j]);
        scanf("%d%d%d", &cm, &cl, &a);

        if(bmar[cm][a]>bleo[cl][a])
            printf("Marcos\n");
        else if(bleo[cl][a]>bmar[cm][a])
            printf("Leonardo\n");
        else 
            printf("Empate\n");
    }
    return 0;
}