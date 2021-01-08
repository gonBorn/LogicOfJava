package generictype;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DynamicArray<Double> arr = new DynamicArray<>();
        System.out.println(arr.getSize());
        Random random = new Random();
        int size = 1+random.nextInt(100);
        for (int i = 0; i < size; i++) {
            arr.add(Math.random());
        }
        Double d = arr.get(random.nextInt(size));
    }
}
