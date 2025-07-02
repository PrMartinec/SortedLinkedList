package cz.martinec.library.collections;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class SortedLinkedListImpl<T extends Comparable<T>> implements SortedLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private final Comparator<T> comparator;
    private Node<T> head = null;
    private int size = 0;

    SortedLinkedListImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(T value) {
        final Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else if (comparator.compare(value, head.value) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && comparator.compare(current.next.value, value) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void addAll(Collection<T> values) {
        Objects.requireNonNull(values, "Collection of values cannot be null");

        values.forEach(this::add);
    }

    @Override
    public boolean remove(T value) {
        if (head == null) return false;

        if (Objects.equals(head.value, value)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !Objects.equals(current.next.value, value)) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        size--;
        return true;
    }

    @Override
    public boolean removeAll(Collection<T> values) {
        Objects.requireNonNull(values, "Collection of values cannot be null");

        boolean modified = false;
        for (T value : values) {
            while (contains(value)) {
                remove(value);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (comparator.compare(current.value, value) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<T> values) {
        Objects.requireNonNull(values, "Collection of values cannot be null");

        for (T value : values) {
            if (!contains(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOf(T value) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> current = head;
        int index = 0;
        int lastFound = -1;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                lastFound = index;
            }
            current = current.next;
            index++;
        }

        return lastFound;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return get(size - 1);
    }

    @Override
    public SortedLinkedList<T> clone() {
        final SortedLinkedListImpl<T> copy = new SortedLinkedListImpl<>(this.comparator);
        Node<T> current = this.head;
        while (current != null) {
            copy.add(current.value);
            current = current.next;
        }
        return copy;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[size];
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.value;
            current = current.next;
        }
        return array;
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(this.iterator(), Spliterator.ORDERED),
                false
        );
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            current = current.next;
            if (current != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortedLinkedListImpl<?> other)) return false;

        if (this.size != other.size) return false;

        Node<T> current = this.head;
        Node<?> otherCurrent = other.head;

        while (current != null && otherCurrent != null) {
            if (!Objects.equals(current.value, otherCurrent.value)) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }

        return current == null && otherCurrent == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Node<T> current = head;
        while (current != null) {
            result = 31 * result + (current.value == null ? 0 : current.value.hashCode());
            current = current.next;
        }
        return result;
    }
}