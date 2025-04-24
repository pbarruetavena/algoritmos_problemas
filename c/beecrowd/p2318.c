#include <stdio.h>

int main(){
    int quad[3][3], soma, somp = 0, ver = 0, pozero;

    for(int i = 0; i < 3; i++)
        for(int j = 0; j < 3; j++)
            scanf("%d", &quad[i][j]);

    if(quad[1][1] == 0){
        if(quad[0][0] != 0 && quad[2][2] != 0)
            quad[1][1] = (quad[0][0] + quad[2][2]) / 2;
        else if(quad[0][2] != 0 && quad[2][0] != 0)
            quad[1][1] = (quad[0][2] + quad[2][0]) / 2;
        else if(quad[1][0] != 0 && quad[1][2] != 0)
            quad[1][1] = (quad[1][0] + quad[1][2]) / 2;
        else // if(quad[0][1]!=0 && quad[2][1]!=0)
            quad[1][1] = (quad[0][1] + quad[2][1]) / 2;
    }

    
    soma = 3 * quad[1][1];
    for(int cont = 0; cont < 3; cont++){
        for(int i = 0; i < 3; i++, ver = 0, somp = 0){
            for(int j = 0; j < 3; j++){
                if(quad[i][j]==0){
                    pozero = j;
                    ver++;
                }
                somp += quad[i][j];
            }
            if(ver == 1)
                quad[i][pozero] = soma - somp;
        }
        for(int j = 0; j < 3; j++, ver = 0, somp = 0){
            for(int i = 0; i < 3; i++){
                if(quad[i][j]==0){
                    pozero = i;
                    ver++;
                }
                somp += quad[i][j];
            }
            if(ver == 1)
                quad[pozero][j] = soma - somp;
        }
    }
    
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 2; j++)
            printf("%d ", quad[i][j]);
        printf("%d\n", quad[i][2]);
    }

    return 0;
}