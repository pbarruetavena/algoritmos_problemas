int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    int *ret;
    for(int i = 0; i < numsSize; i++) {
        for(int k = i+1; k < numsSize; k++) {
            if(nums[i] + nums[k] == target) {
                ret = (int*) malloc(2 * sizeof(int));
                ret[0] = i;
                ret[1] = k;
                *returnSize = 2;
                return ret;
            }
        }
    }
    *returnSize = 0;
    ret = (int*) malloc(0);
    return ret;
}