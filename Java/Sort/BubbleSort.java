package Sorts;

import static Sorts.SortUtils.*;

class BubbleSort implements SortAlgorithm {

public  <T extends Comparable<T>> T[] sort(T array[]) {
        int last = array.length;
        //Sorting
        boolean swap;
        do {
            swap = false;
            for (int count = 0; count < last-1; count++) {
                if (less(array[count], array[count + 1])) {
                    swap = swap(array, count, count + 1);
                }
            }
            last--;
        } while (swap);
        return array;
    }

    // Driver Program
    public static void main(String[] args) {

        // Integer Input
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(integers);

        // Output => 231, 78, 54, 23, 12, 9, 6, 4, 1
        print(integers);

        // String Input
        String[] strings = {"c", "a", "e", "b","d"};
        //Output => e, d, c, b, a
        print(bubbleSort.sort(strings));

    }
}
