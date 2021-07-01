package other;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {10, 2, 9, 8, 5, 7, 5, 4, 2, 1, 14, 12, 13};

        quickSort(array, 0, array.length-1);

        for (int i : array) {
            System.out.println(i);
        }
    }

    static void quickSort(int [] array , int start, int end) {
        int temp = array[start];
        int i = start;
        int j = end;

        while (i < j) {


            while (i < j && array[j] > temp) {
                j--;
            }
            while (i < j && array[i] < temp) {
                i++;
            }

            if (array[i] == array[j] && i < j) {
                i++;
            } else {
                int data = array[i];
                array[i] = array[j];
                array[j] = data;

            }

            if (i-1>start) quickSort(array,start,i-1);
            if (j+1<end) quickSort(array,j+1,end);
        }
    }
}
