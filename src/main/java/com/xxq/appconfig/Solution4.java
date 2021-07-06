package com.xxq.appconfig;

public class Solution4 {

    public static void main(String[] args) {
        int[] num1 = new int[] {1,2};
        int[] num2 = new int[] {3,4};

        System.out.println(findMid(num1, num2));

        System.out.println(Double.parseDouble(Integer.toString(2)));
    }

    static double findMid(int [] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return (double) 0;
        } else if(nums1.length == 0){
            if ((nums2.length & 1) == 1) {
                // 奇数
                int mid = nums2.length / 2 + 1;
                return Double.parseDouble(Integer.toString(nums2[mid-1]));
            } else {
                int mid = nums2.length / 2;
                int mid2 = mid+1;
                return Double.parseDouble(Integer.toString((nums2[mid-1] + nums2[mid2-1])))/2;
            }
        } else if(nums2.length == 0) {
            if ((nums1.length & 1) == 1) {
                // 奇数
                int mid = nums1.length / 2 + 1;
                return Double.parseDouble(Integer.toString(nums1[mid-1]));
            } else {
                int mid = nums1.length / 2;
                int mid2 = mid+1;
                return Double.parseDouble(Integer.toString((nums1[mid-1] + nums1[mid2-1])))/2;
            }
        }
        int[] ints = new int[nums1.length + nums2.length];
        int index=0;
        for (int i=0,j=0; i<nums1.length || j<nums2.length;) {
            int i1 = nums1[i];
            int i2 = nums2[j];
            if (i1<i2) {
                ints[index] = i1;
                i++;
            } else {
                ints[index] = i2;
                j++;
            }
            index++;
            if (i == nums1.length) {
                for (;  j< nums2.length; j++) {
                    ints[index] = nums2[j];
                    index++;
                }
                break;
            } else if(j == nums2.length) {
                for (;  i< nums1.length; i++) {
                    ints[index] = nums1[i];
                    index++;
                }
                break;
            }
        }

        if ((ints.length & 1) == 1) {
            // 奇数
            int mid = ints.length / 2 + 1;
            return Double.parseDouble(Integer.toString(ints[mid-1]));
        } else {
            int mid = ints.length / 2;
            int mid2 = mid+1;
            return Double.parseDouble(Integer.toString((ints[mid-1] + ints[mid2-1])))/2;
        }
    }
}
