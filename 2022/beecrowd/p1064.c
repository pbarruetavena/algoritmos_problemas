#include <stdio.h> 

int main(){
    double num, med = 0;
    int qn = 0;
    for(int i=1; i<=6; i++){
        scanf("%lf", &num);
        if(num>0){
            med+=num;
            qn++;
        }
    }

    med = med/qn;
    printf("%d valores positivos\n", qn);
    printf("%.1f\n", med);

    return 0;
}