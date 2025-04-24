#include <stdio.h>

int main(){
    unsigned int linha1, coluna, linha, r;
    scanf("%d%d", &linha, &coluna);

    // - as casas da primeira linha se alternam no valor da coluna: ímpar: branco e par: preto
    // - seguindo nas diagonais as cores das casas se mantém
    // logo se conseguirmos achar o valor da coluna da casa da linha 1 que equivale à última casa do tabuleiro (a casa da linha 1 que está alinhada na diagonal da ultima casa) podemos achar sua cor, usando o resto da divisão por 2
    //uma casa alinhada à outra significa que estas casa se encontram na diagonal

    //calculo
    linha1 = coluna - (linha - 1); //posição na coluna da casa da linha 1 alinhada à última casa do tabuleiro
    r = linha1%2; //resto da divisão por 2, resultando em 0 em colunas pares e 1 em colunas ímpares
    //resultado
    printf("%d\n", r);

    return 0;
}