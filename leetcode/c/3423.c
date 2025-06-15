int absolute(int n) {
    if (n < 0) return -n;
    return n;
}

int maxAdjacentDistance(int* nums, int numsSize) {
    int max = 0, res;
    for(int i = 0; i < numsSize; i++) {
        res = absolute(nums[i] - nums[(i+1)%numsSize]);
        if(res > max) {
            max = res;
        }
    }
    return max;
}