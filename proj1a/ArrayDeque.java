public class ArrayDeque<T> {

    private int size;
    private T[] item;
    private int head;
    private int tail;
    public ArrayDeque() {
        size = 0;
        head = 1;
        tail = 0;
        item = (T[]) new Object[8];
    }
    private int prev(int x) {
        if (x - 1 < 0) {
            return item.length - 1;
        }
        return x - 1;
    }
    private int next(int x) {
        if (x + 1 == item.length) {
            return 0;
        }
        return x + 1;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p = head;
        for (int i = 0; i < size; i++) {
            a[i] = item[p];
            p = next(p);
        }
        head = 0;
        tail = size - 1;
        item = a;
    }
    public void addFirst(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        head = prev(head);
        item[head] = x;
        size += 1;
    }
    public void addLast(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        tail = next(tail);
        item[tail] = x;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int p = head;
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
            p = next(p);
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = item[head];
        item[head] = null;
        head = next(head);
        size -= 1;
        return x;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = item[tail];
        item[tail] = null;
        tail = prev(head);
        size -= 1;
        return x;
    }
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        pos = (head + index - item.length) % item.length;
        return item[pos];
    }
}
