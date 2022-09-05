package pro.sky.List;

import java.util.Random;

public class Sorting {
    private static Random random = new Random();

    public static void main(String[] args) {
        double timeForSortBubble = timeForBubbleSort(5);
        System.out.println("Среднее время сортировки пузырьком: " + timeForSortBubble + " мс.");
        double timeForSortSelection = timeForSelectionSort(5);
        System.out.println("Среднее время сортировки выбором: " + timeForSortSelection + " мс.");
        double timeForSortInsertion = timeForInsertionSort(5);
        System.out.println("Среднее время сортировки вставками: " + timeForSortInsertion + " мс.");
    }

    public static void bubbleSorter(int[] array) {
        // i - номер прохода
        for (int i = 0; i < array.length - 1; i++) {
            // внутренний цикл прохода
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] > array[j]) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    private static void sortSelection(int[] array) { // выбором
        for (int i = 0; i < array.length; i++) {    // i - номер текущего шага
            int pos = i;
            int min = array[i];
            // цикл выбора наименьшего элемента
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    pos = j;    // pos - индекс наименьшего элемента
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;    // меняем местами наименьший с array[i]
        }
    }

    private static void sortInsertion(int[] arr) { // сортировка вставками
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(-100, 100);
        }
        return array;
    }

    private static double timeForInsertionSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sortInsertion(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

    private static double timeForSelectionSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sortSelection(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

    private static double timeForBubbleSort(int iteration) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            bubbleSorter(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

}
