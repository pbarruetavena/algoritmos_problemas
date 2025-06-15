#include <stdio.h>
double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    int total = nums1Size + nums2Size, cont = 0;
    int med1 = 0, med2 = 0, i = 0, j = 0;

    while(1) {
        med2 = med1;
        if(i == nums1Size) {
            med1 = nums2[j];
            j++;
        } else if(j == nums2Size) {
            med1 = nums1[i];
            i++;
        } else if(nums1[i] < nums2[j]) {
            med1 = nums1[i];
            i++;
        } else {
            med1 = nums2[j];
            j++;
        }
        cont++;
        printf("med=%d i=%d j=%d cont=%d\n", med1, i, j, cont);
        if(total%2!=0 && cont*2==total+1) {
            return med1;
        } else if(total%2==0 && cont>total/2) {
            return ((double) med1 + med2)/2;
        }
    }

}