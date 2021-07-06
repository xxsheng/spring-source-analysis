package com.xxq.appconfig;

public class MiddleSearch {

    public static void main(String[] args) {
        int[] arrays = new int[] {1,3,5,7,9,10,15,17};

        int index = binarySearch(arrays, 0);
        System.out.println(index);
    }

    static int binarySearch(int[] arrays, int number) {

        int start = 0;
        int end = arrays.length-1;
        int index = 0;
        while (start <= end) {
            index++;
            int mid = (start + end) >>> 1;
            if (number > arrays[mid]) {
                start = mid+1;
            } else if (number < arrays[mid]){
                end = mid-1;
            } else {
                return mid;
            }
        }

        System.out.println(index);
        return -1;
    }
}
