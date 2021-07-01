package other;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {10, 2, 9, 8, 5, 7, 5, 4, 2, 1, 14, 12, 13};
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j + 1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
