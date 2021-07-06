package com.xxq.appconfig;

public class Solution4_2 {

    public static void main(String[] args) {
//        int[] array1 = new int[] {1,2,4,6,8,9,10,14,18};
//        int[] array2 = new int[] {1,3,4,5,7,11,13,16,17};
        int[] array1 = new int[] {1,2,4,6};
        int[] array2 = new int[] {1,3,4,5,7};
        Solution4_2 solution4_2 = new Solution4_2();
        solution4_2.findMedianSortedArrays(array1, array2);
    }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2; // 奇数直接取中间值，只有一个中位数
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2; // -1表示左边，不建议表示右边，俩个中位数
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            int kthElement = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    // 等于边界得情况下，nums1得元素要不全部被排除，这时候直接从num2中获取
                    // -1 是因为上面加1了
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    // -1是因为上面加1了
                    // 等于边界得情况下，num2得元素应该就已经全部排除，这时候直接从num1中获取
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                // -1表示要使用个数往前退1，才能使用索引值
                int newIndex1 = Math.min(index1 + half, length1) - 1; //half为k的一半，所以在俩个数组中分别比较对应得half/2+index得值
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1); // 去掉的元素个数
                    index1 = newIndex1 + 1; // 往后位移
                } else {
                    k -= (newIndex2 - index2 + 1); // 去掉的元素个数
                    index2 = newIndex2 + 1; // 往后位移
                }
            }
        }

}
