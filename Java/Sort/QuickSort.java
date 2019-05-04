package Sorts;

import static Sorts.SortUtils.*;

class QuickSort implements SortAlgorithm {

public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }
    
    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot , right);
        }
    }
    
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) / 2;
        T pivot = array[mid];

        while(left <= right) {
            while(less(array[left], pivot)){
                ++left;
            }
            while(less(pivot, array[right])) {
                --right;
            }
            if(left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    // Driver Program
    public static void main(String[] args) {

        // For integer input
        Integer[] array =  {3, 4, 1, 32, 0, 1, 5, 12 ,2, 5 ,7 ,8 ,9, 2, 44, 111, 5};

        QuickSort quickSort = new QuickSort();
       // quickSort.sort(array);

        //Output => 0 1 1 2 2 3 4 5 5 5 7 8 9 12 32 44 111
        print(array);

        String[] stringArray =  {"c", "a", "e", "b", "d"};
        quickSort.sort(stringArray);

        //Output => a	b	c	d	e
        print(stringArray);
    }
}
