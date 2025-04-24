#include <stdio.h>
#include <string.h>

int verSenha(char *senha){
    int req[3], t, ver;
    char letrasValidas[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    t = strlen(senha);
    if(t>32 || t<6)
        return 0;
    
    for(int i = 0; senha[i]!='\0'; i++){
        ver = 0;
        for(int j = 0; letrasValidas[j]!='\0'; j++){
            if(senha[i]==letrasValidas[j]){
                ver = 1;
                req[j/26] = 1;
                break;
            }
        }
        if(ver==0)
            return 0;
    }
    if(req[0]==1 && req[1]==1 && req[2]==1)
        return 1;
    else
        return 0;

}

int main(){
    char senha[101];

    while(fgets(senha, 101, stdin)!=NULL){
        senha[strcspn(senha, "\n")] = '\0';
        if(verSenha(senha)==1)
            printf("Senha valida.\n");
        else
            printf("Senha invalida.\n");
    }

    return 0;
}