package pro.sky.List;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(12654685);
        integerList.add(1);
        integerList.add(1);
        integerList.add(14);
        integerList.add(14);
        integerList.add(44);
        integerList.add(1744);
        integerList.add(0,124);
        integerList.add(0,124);
        integerList.add(0,124);
        integerList.add(0,124);
        integerList.add(0,124);
        integerList.add(1744);
        integerList.add(12654685);
        integerList.add(0,12654685);
//        integerList.add(328);
//        integerList.add(2536);
//        integerList.add(2536);
//        integerList.add(2536);
        System.out.println(Arrays.toString(integerList.toArray()));
//        System.out.println(integerList.contains(328));
//        System.out.println(Arrays.toString(integerList.toArray()));


    }
}
