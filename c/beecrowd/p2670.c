#include <stdio.h>

int main(){
    int a1, a2, a3, andar, t2, t, d1, d2, d3;
    scanf("%d%d%d", &a1, &a2, &a3);

    andar = 2;

    d1 = andar - 1;
    d2 = andar - 2;
    d3 = 3 - andar;

    if(d2<0)
        d2 = -d2;

    //calculo usando o 2º andar
    t = a1*2*d1 + a2*2*d2 + a3*2*d3;
    /* calculo
        -a(n) ->    quantidade de pessoas p/ andar (n - andar)
        -2 ->       minutos por pessoas (ida e volta)
        -d(n) ->    diferença de andar para o andar com a maq. de café
                    (multiplica para zerar o tempo das pessoas que estão no msm
                    andar da máquina e dobrar o tempo de quem está à 2 andares de dif.)
    */
    andar = 1;
    if(a3>a1)
        andar = 3;
    
    d1 = andar - 1;
    d2 = andar - 2;
    d3 = 3 - andar;
    if(d2<0)
        d2 = -d2;

    //calculo usando o 1º ou 3º (o que tiver mais pessoas)
    t2 = a1*2*d1 + a2*2*d2 + a3*2*d3;

    //compara qual o tempo é menor
    if(t>t2)
        t = t2;
    

    printf("%d\n", t);

    return 0;
}