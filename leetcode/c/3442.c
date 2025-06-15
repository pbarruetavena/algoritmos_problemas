int count_apaga(char *s, char c) {
    int tot = 0;
    while(*s != '\0') {
        if(*s == c) {
            tot++;
            *s = '$';
        }
        s++;
    }
    return tot;
}

int maxDifference(char* s) {
    int max_odd = -1;
    int min_even = -1;
    int q;
    while(*s != '\0') {
        if(*s != '$') {
            q = count_apaga(s, *s);
            if(q%2 == 1) {
                if(max_odd < q) max_odd = q;
            } else {
                if(min_even == -1) min_even = q;
                else if(min_even > q) min_even = q;
            }
        }
        s++;
    }
    return max_odd - min_even;
}
