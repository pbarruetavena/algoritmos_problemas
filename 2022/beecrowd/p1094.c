#include <stdio.h>

int main(){
    int n;
    double pc, pr, ps, q, qc, qr, qs;
    char tipo;
    qc = qr = qs = 0;
    scanf("%d", &n);

    for(int i=1; i<=n; i++){
        scanf("%lf %c", &q, &tipo);
        switch (tipo){
            case 'C': qc+=q; break;
            case 'R': qr+=q; break;
            default: qs+=q; break;
        }
    }
    q = qc+qr+qs;
    pc = (qc*100/q);
    pr = (qr/q)*100;
    ps = (qs/q)*100;

    printf("Total: %d cobaias\nTotal de coelhos: %d\n", (int) q, (int) qc);
    printf("Total de ratos: %d\nTotal de sapos: %d\n", (int) qr, (int) qs);
    printf("Percentual de coelhos: %.2f %%\n", pc);
    printf("Percentual de ratos: %.2f %%\n", pr);
    printf("Percentual de sapos: %.2f %%\n", ps);

    return 0;
}