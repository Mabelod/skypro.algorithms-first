package pro.sky.List;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl();
        StringList stringList1 = new StringListImpl();

        stringList1.add("Привет");
        stringList1.add("Привет");
        stringList1.add("Привет");
        stringList1.add("Привет");
        stringList.add("Привет");
        stringList.add("Привет");
        stringList.add("Привет");
        stringList.add("Привет");
//        stringList.add(0, null);
//        stringList.set(0, "Перезаписал");
//        System.out.println(stringList.remove("Привет"));
//        stringList.remove(3);
//        System.out.println(stringList.lastIndexOf("Пока2"));
//        System.out.println(stringList.get(0));
//        System.out.println(stringList.size());
//        System.out.println(stringList1.isEmpty());
//        stringList1.clear();
        System.out.println(stringList.equals(stringList1));

        System.out.println(Arrays.toString(stringList.toArray()));
        System.out.println(Arrays.toString(stringList1.toArray()));


    }
}
