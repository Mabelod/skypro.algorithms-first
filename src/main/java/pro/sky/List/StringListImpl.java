package pro.sky.List;

import pro.sky.Exception.ArrayLengthExceeded;
import pro.sky.Exception.ValueError;

import java.util.*;

public class StringListImpl implements StringList{

    private String[] storage;

    public StringListImpl() {
        storage = new String[0];
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullPointerException();
        }
        String[] array = new String[storage.length + 1];
        System.arraycopy(storage, 0, array, 0, storage.length);
        array[storage.length] = item;
        storage = array;
        return item;
    }

    @Override
    public String add(int index, String item) {
        String[] array = new String[storage.length + 1];
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        } else if (item == null) {
            throw new NullPointerException();
        }
        System.arraycopy(storage, 0, array, 0, storage.length);
        array[index] = item;
        System.arraycopy(storage, index, array, index + 1, array.length - (index + 1));
        storage = array;
        return item;
    }

    @Override
    public String set(int index, String item) {
        String[] array = new String[storage.length];
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
    public String remove(String item) {
        String[] array = new String[storage.length];
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
        storage = Arrays.stream(array).filter(Objects::nonNull).toArray(String[]::new);
        return item;
    }

    @Override
    public String remove(int index) {
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        }
        String[] array = new String[storage.length];
        System.arraycopy(storage, 0, array, 0, storage.length);
        String value = array[index];
        array[index] = null;
        storage = Arrays.stream(array).filter(Objects::nonNull).toArray(String[]::new);
        return value;
    }

    @Override
    public boolean contains(String item) {
        boolean x = false;
        for (String s : storage) {
            if (s.equals(item)) {
                x = true;
            }
        }
        return x;
    }

    @Override
    public int indexOf(String item) {
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
    public int lastIndexOf(String item) {
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
    public String get(int index) {
        if (index >= storage.length) {
            throw new ArrayLengthExceeded("Привышена длинна массива");
        }
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        } else if (otherList.size() != storage.length) {
            return false;
        }
        for (int i = 0; i < storage.length; i++) {
            if (!storage[i].equals(otherList.get(i))) {
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
        storage = Arrays.stream(storage).filter(Objects::nonNull).toArray(String[]::new);
    }

    @Override
    public String[] toArray() {
        return storage;
    }

}
