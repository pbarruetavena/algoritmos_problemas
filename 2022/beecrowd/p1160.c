#include <stdio.h>

int main(){
    int pa = 0, pb, t;
    double ga, gb;
    scanf("%d", &t);
    for(int cont = 1; cont<=t; cont++){
        scanf("%d%d%lf%lf", &pa, &pb, &ga, &gb);
        for(int i = 1; 1; i++){
            pa = pa*(1+ga/100);
            pb = pb*(1+gb/100);
            if(pa>pb){
                printf("%d anos.\n", i);
                break; }
            if(i>99){
                printf("Mais de 1 seculo.\n");
                break; }
        }
    }

    return 0;
}