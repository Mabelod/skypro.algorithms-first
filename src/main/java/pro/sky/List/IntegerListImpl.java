package pro.sky.List;

import pro.sky.Exception.ArrayLengthExceeded;
import pro.sky.Exception.ValueError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IntegerListImpl implements IntegerList{

    private Integer[] storage;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (storage[storage.length - 1] != null) {
            grow();
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = item;
                break;
            }
        }
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        } else if (item == null) {
            throw new NullPointerException();
        } else if (storage[storage.length - 1] != null) {
            grow();
        }
        for (int length = storage.length - 1; length >= 0; length--) {
            if (storage[length] != null) {
                storage[length + 1] = storage[length];
                storage[length] = null;
                if (length == index) {
                    storage[index] = item;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public Integer set(int index, Integer item) {
        Integer[] array = new Integer[storage.length];
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        } else if (item == null) {
            throw new NullPointerException();
        }
        System.arraycopy(storage, 0, array, 0, storage.length);
        array[index] = item;
        storage = array;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        Integer[] array = new Integer[storage.length];
        System.arraycopy(storage, 0, array, 0, storage.length);
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                array[i] = null;
                counter++;
            }
        }
        if (counter == 0) {
            throw new ValueError("Элемент отсутсвует в массиве");
        }
        storage = Arrays.stream(array).filter(Objects::nonNull).toArray(Integer[]::new);
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        }
        Integer[] array = new Integer[storage.length];
        System.arraycopy(storage, 0, array, 0, storage.length);
        Integer value = array[index];
        array[index] = null;
        storage = Arrays.stream(array).filter(Objects::nonNull).toArray(Integer[]::new);
        return value;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Integer[] arrayForSearch = toArray();
        sortInsertion(arrayForSearch);

        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        int index = -1;
        for (int i = 0; i < storage.length; i++) {
            if (this.storage[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        int index = -1;
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        }
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList integerList) {
        if (integerList == null) {
            throw new NullPointerException();
        } else if (integerList.size() != storage.length) {
            return false;
        }
        for (int i = 0; i < storage.length; i++) {
            if (!storage[i].equals(integerList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return storage.length;
    }

    @Override
    public boolean isEmpty() {
        return storage.length == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, null);
        storage = Arrays.stream(storage).filter(Objects::nonNull).toArray(Integer[]::new);
    }

    @Override
    public Integer[] toArray() {
        int counter = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                counter++;
            }
        }
        Integer[] array = new Integer[storage.length - counter];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            array[i] = storage[i];
        }
        return array;
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void sortInsertion(Integer[] arr) { // сортировка вставками
//        for (int i = 1; i < arr.length; i++) {
//            int temp = arr[i];
//            int j = i - 1;
//            while (j >= 0 && temp < arr[j]) {
//                arr[j + 1] = arr[j];
//                j--;
//            }
//            arr[j + 1] = temp;
//        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void grow() {
        Integer[] array = new Integer[storage.length + storage.length / 2];
        System.arraycopy(storage, 0, array, 0, storage.length);
        storage = array;
    }
}
