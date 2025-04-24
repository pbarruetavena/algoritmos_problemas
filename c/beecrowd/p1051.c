#include <stdio.h>

int main(){
    double sal, imp;
    scanf("%lf", &sal);

    if(sal<=2000)
        printf("Isento\n");
    else if(sal<=3000){
        imp = (sal-2000)*(0.08);
        printf("R$ %.2f\n", imp);
    } else if(sal<=4500){
        imp = 80 + (sal-3000)*0.18;
        printf("R$ %.2f\n", imp);
    } else{
        imp = 80 + 270 + (sal-4500)*0.28;
        printf("R$ %.2f\n", imp);
    }

    return 0;
}