#include <stdio.h>
#include <stdlib.h>

int preencheDuplicaArcos(int *l, int n){
    int tot = 0;
    for(int i = 0; i<n; i++){
        scanf("%d", &l[i]);
        tot+=l[i];
        l[i+n] = l[i];
    }
    return tot;
}

char calculaRetangulo(int *l, int n, int tot){
    int med = tot/2, aux;
    for(int i = 0; i<n; i++){
        aux = l[i];
        for(int j = 0, sup; j<n; j++){
            aux+=l[i+j];
            sup = med - aux;
            if(sup < 0)
                break;
            for(int k = 0, somaver = 0, vertice = 2; k<n; k++){
                somaver+=l[k];
                if(somaver==aux+sup)
                    vertice++;
                if(somaver==aux+med)
                    vertice++;
                if(vertice==4)
                    return 'S';
            }
        }
    }
    return 'N';
}

int main(){
    int n, *l, t;
    scanf("%d", &n);
    l = (int*) malloc(2*n*sizeof(int));

    t = preencheDuplicaArcos(l, n);
    printf("%c\n", calculaRetangulo(l, n, t));

    free(l);

    return 0;
}