package cz.martinec.library.collections;

import java.util.Comparator;

public class SortedLinkedListFactory {

    private SortedLinkedListFactory() {
        // Utility class
    }

    public static SortedLinkedList<Integer> createIntegerList() {
        return new SortedLinkedListImpl<Integer>(Comparator.naturalOrder());
    }

    public static SortedLinkedList<String> createStringList() {
        return new SortedLinkedListImpl<String>(Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> SortedLinkedList<T> createList() {
        return new SortedLinkedListImpl<T>(Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> SortedLinkedList<T> createList(Comparator<T> comparator) {
        return new SortedLinkedListImpl<>(comparator);
    }
}
