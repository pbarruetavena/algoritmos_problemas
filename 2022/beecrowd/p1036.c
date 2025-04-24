#include <stdio.h> 
#include <math.h>

int main(){
    double a, b, c, r1, r2, del;
    scanf("%lf%lf%lf", &a, &b, &c);

    del = b*b - 4*a*c;
    if(del>=0 && a!=0){
        r1 = (-b+sqrt(del))/(2*a);
        r2 = (-b-sqrt(del))/(2*a);
        printf("R1 = %.5f\nR2 = %.5f\n", r1, r2);
    } else 
        printf("Impossivel calcular\n");

    return 0;
}