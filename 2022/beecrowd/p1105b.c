#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int reserva, credito, divida;
} Banco;

void carregarReserva(int b, Banco *bancos){
    for(int i = 0; i<b; i++)
        scanf("%d", &bancos[i].reserva);
}

void zeraValor(int b, Banco *bancos){
    for(int i =0; i<b; i++){
        bancos[i].credito = 0;
        bancos[i].divida = 0;
    }
}

void carregarDeb(int n, Banco *bancos){
    int d, c, v;
    for(int i = 0; i<n; i++){
        scanf("%d %d %d", &d, &c, &v);
        bancos[c-1].credito+=v;
        bancos[d-1].divida+=v;
    }
}

char calculaLiq(Banco *bancos, int b){
    for(int i = 0; i<b; i++){
        bancos[i].credito-=bancos[i].divida;
        bancos[i].reserva+=bancos[i].credito;
        if(bancos[i].reserva<0)
            return 'N';
    }
    return 'S';
}

int main(){
    int b, n;
    char liq;
    Banco* bancos;
    
    while(1){
        scanf("%d %d", &b, &n);
        if(b==0 && n==0)
            break;
        bancos = (Banco*) malloc(b*sizeof(Banco));
        

        carregarReserva(b, bancos);
        zeraValor(b, bancos);
        carregarDeb(n, bancos);
        liq = calculaLiq(bancos, b);
        printf("%c\n", liq);

        free(bancos);
    }
    return 0;
}
