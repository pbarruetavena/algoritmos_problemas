#include <stdlib.h>

// Definition for singly-linked list.
struct ListNode {
    int val;
    struct ListNode *next;
};
 
struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
    int n1 = 0, n2 = 0, sum = 0, b = 1, isN1Null = 0, isN2Null = 0;
    struct ListNode *ret, *aux, *old;
    while(!isN1Null || !isN2Null || sum) {
        aux = (struct ListNode*) malloc(sizeof(struct ListNode));
        if(b) {
            ret = aux;
            b = 0;
        } else {
            old->next = aux;
        }
        if(l1 != NULL) {
            n1 = l1->val;
            l1 = l1->next;
        } else {
            n1 = 0;
        }
        if(l2 != NULL) {
            n2 = l2->val;
            l2 = l2->next;
        } else {
            n2 = 0;
            isN2Null = 1;
        }

        sum = sum + n1 + n2;
        aux->val = sum % 10;
        sum = sum / 10;
        old = aux;

        if(l1 == NULL) isN1Null = 1;
        if(l2 == NULL) isN2Null = 1;
    }
    old->next = NULL;
    return ret;
}