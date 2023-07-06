public class LinkedListDeque<T> {
    private  class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T x, TNode p, TNode n) {
            item = x;
            prev = p;
            next = n;
        }
    }
    private TNode sentinel;
    private int size;
    public LinkedListDeque() {
        size = 0;
        T x = (T)new Object();
        sentinel = new TNode(x, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addFirst(T item) {
        TNode a = new TNode(item, null, null);
        sentinel.next.prev = a;
        a.next = sentinel.next;
        sentinel.next = a;
        a.prev = sentinel;
        size += 1;
    }
    public void addLast(T item) {
        TNode a = new TNode(item, null, null);
        a.prev = sentinel.prev;
        a.next = sentinel.prev.next;
        sentinel.prev.next = a;
        sentinel.prev = a;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        TNode p = sentinel.next;
        while(p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return x;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return x;
    }
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        int i = 0;
        TNode p = sentinel.next;
        while(i < index) {
            i += 1;
            p = p.next;
        }
        return p.item;
    }
    private T helper(TNode p, int index) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return helper(p.next, index - 1);
    }
    public T getRecursive(int index) {
        return helper(sentinel.next, index);
    }
}