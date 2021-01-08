package generictype;

import java.util.Arrays;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    //init = 0, 实际的元素个数
    private int size;
    private Object[] elements;

    public DynamicArray() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    public E get(int index) {
        return (E)elements[index];
    }

    public E set(int index, E element) {
        E e = get(index);
        elements[index] = element;
        return e;
    }

    public int getSize() {
        return size;
    }
}
