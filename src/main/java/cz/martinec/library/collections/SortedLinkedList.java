package cz.martinec.library.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Sorted singly linked list that maintains elements in natural order.
 *
 * @param <T> the type of elements, must be Comparable
 */
public interface SortedLinkedList<T extends Comparable<T>> {
    /**
     * Adds an element in sorted position.
     *
     * @param value element to add
     */
    void add(T value);

    /**
     * Adds all elements from the given collection.
     *
     * @param values collection of elements to add
     * @throws NullPointerException if values is null
     */
    void addAll(Collection<T> values);

    /**
     * Removes the first occurrence of an element.
     *
     * @param value element to remove
     * @return true if removed, false otherwise
     */
    boolean remove(T value);

    /**
     * Removes all elements that are present in the given collection.
     *
     * @param values collection of elements to remove
     * @return true if at least one element was removed
     * @throws NullPointerException if values is null
     */
    boolean removeAll(Collection<T> values);

    /**
     * Checks if the list contains the given element.
     *
     * @param value element to check
     * @return true if present, false otherwise
     */
    boolean contains(T value);

    /**
     * Checks if the list contains all elements from the given collection.
     *
     * @param values collection to check
     * @return true if all elements are present, false otherwise
     * @throws NullPointerException if values is null
     */
    boolean containsAll(Collection<T> values);

    /**
     * Returns the index of the first occurrence of the given value.
     *
     * @param value the value to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    int indexOf(T value);

    /**
     * Returns the index of the last occurrence of the given value.
     *
     * @param value the value to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    int lastIndexOf(T value);

    /**
     * Returns the number of elements.
     *
     * @return size of the list
     */
    int size();

    /**
     * Checks if the list is empty.
     *
     * @return true if the list has no elements
     */
    boolean isEmpty();

    /**
     * Returns the element at the given index.
     *
     * @param index index of element (0-based)
     * @return element at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    T get(int index);

    /**
     * Returns the first element in the list.
     *
     * @return first element
     * @throws IndexOutOfBoundsException if the list is empty
     */
    T getFirst();

    /**
     * Returns the last element in the list.
     *
     * @return last element
     * @throws IndexOutOfBoundsException if the list is empty
     */
    T getLast();

    /**
     * Returns a new SortedLinkedList containing a deep copy of the elements.
     *
     * @return cloned list
     */
    SortedLinkedList<T> clone();

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Returns an array containing all elements in proper order.
     *
     * @return array of list elements
     */
    Object[] toArray();

    /**
     * Returns a sequential {@link Stream} with the elements of this list.
     *
     * @return stream of elements
     */
    Stream<T> stream();

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return iterator
     */
    Iterator<T> iterator();
}