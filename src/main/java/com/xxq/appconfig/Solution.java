package com.xxq.appconfig;

import java.util.HashMap;
import java.util.Map;

public  class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int num2 = target - num;
            Integer integer = temp.get(num2);
            if (integer != null) {
                result[0] = i;
                result[1] = integer;
            } else {
                temp.put(num, i);
            }
        }
        return result;
    }
}
