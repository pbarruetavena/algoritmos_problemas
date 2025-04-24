#include <stdio.h>

    // case 'd': return 1;
    // case 'p': return 2;
    // case 's': return 3;
    // case 'g': return 4;
    // case 'o': return 5; 

void verificacao(char c1, char c2){
    if(c1==c2){
        printf("Caso #3: De novo!\n");
        return;
    }
    switch (c1){
    case 'd':
        switch(c2){
            case 's': case 'g':
                printf("Caso #1: Bazinga!\n");
                return;
            case 'p': case 'o':
                printf("Caso #2: Raj trapaceou!\n");
        } break;
    case 'p':
        switch(c2){
            case 'd': case 'o':
                printf("Caso #1: Bazinga!\n");
                return;
            case 's': case 'g':
                printf("Caso #2: Raj trapaceou!\n");
        } break;
    case 's':
        switch(c2){
            case 'g': case 'p':
                printf("Caso #1: Bazinga!\n");
                return;
            case 'd': case 'o':
                printf("Caso #2: Raj trapaceou!\n");
        } break;
    case 'g':
        switch(c2){
            case 'p': case 'o':
                printf("Caso #1: Bazinga!\n");
                return;
            case 'd': case 's':
                printf("Caso #2: Raj trapaceou!\n");
        } break;
    case 'o':
        switch(c2){
            case 'd': case 's':
                printf("Caso #1: Bazinga!\n");
                return;
            case 'p': case 'g':
                printf("Caso #2: Raj trapaceou!\n");
        } break;
    }
}

int main(){
    int n;
    char sheldon[10], raj[10];
    scanf("%d", &n);
    for(int i = 0; i<n; i++){
        scanf(" %s %s", sheldon, raj);
        verificacao(sheldon[2], raj[2]);
    }

    return 0;
}