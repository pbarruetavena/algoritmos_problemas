#include <stdio.h>

int main(){
    //variaveis e entrada de dados
    int comp, larg, tipo1, tipo1_1, tipo1_2, tipo2;
    scanf("%d %d", &comp, &larg);

    /*calculo
        tipo 1 - lajotas inteiras
            tipo 1.1 - lajotas alinhadas aos vértices formados entre as lajotas e a parede
                - são calculados por largura*comprimento
            tipo 1.2 - lajotas alocadas entre as lajotas de tipo 1.1
                - são calculadas por (largura - 1)*(comprimento - 1)
        tipo 2 - metade do tipo 1
            - só estão presentes nas bordas da sala, portanto podem ser calculadas por 2*(largura-1)+2*(comprimento-1)
    */

    tipo1_1 = comp*larg;
    tipo1_2 = (comp-1)*(larg-1);
    tipo1 = tipo1_1 + tipo1_2;
    tipo2 = 2*(comp-1) + 2*(larg-1);

    //resultado
    printf("%d\n", tipo1);
    printf("%d\n", tipo2);

}