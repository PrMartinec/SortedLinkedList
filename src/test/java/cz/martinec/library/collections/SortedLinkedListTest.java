package cz.martinec.library.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortedLinkedListTest {

    @Test
    void shouldAddIntegersInSortedOrder() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();
        intList.add(10);
        intList.add(5);
        intList.add(15);

        assertThat(intList.toString()).isEqualTo("[5, 10, 15]");
        assertThat(intList.size()).isEqualTo(3);
    }

    @Test
    void shouldRemoveElement() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(2, 4, 6));
        final boolean removed = intList.remove(4);

        assertThat(removed).isTrue();
        assertThat(intList.toString()).isEqualTo("[2, 6]");
        assertThat(intList.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnFalseIfRemovingNonExistentElement() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.add(1);
        final boolean removed = intList.remove(99);

        assertThat(removed).isFalse();
        assertThat(intList.toString()).isEqualTo("[1]");
        assertThat(intList.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnFalseIfListAreDifferent() {
        final SortedLinkedList<Integer> list1 = SortedLinkedListFactory.createIntegerList();
        final SortedLinkedList<Integer> list2 = SortedLinkedListFactory.createIntegerList();

        list1.addAll(List.of(1, 2, 3, 4, 5));
        list2.addAll(List.of(1, 2, 9, 4, 5));

        assertThat(list1.equals(list2)).isFalse();
    }

    @Test
    void shouldReturnTrueIfListAreEquals() {
        final SortedLinkedList<Integer> list1 = SortedLinkedListFactory.createIntegerList();
        final SortedLinkedList<Integer> list2 = SortedLinkedListFactory.createIntegerList();

        list1.addAll(List.of(1, 2, 3, 4, 5));
        list2.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(list1.equals(list2)).isTrue();
    }

    @Test
    void shouldReturnFalseIfListDoesNotContainsValue() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.contains(10)).isFalse();
    }

    @Test
    void shouldReturnTrueIfListContainsValue() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.contains(1)).isTrue();
    }

    @Test
    void shouldRemoveAllValues() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.size()).isEqualTo(5);

        intList.removeAll(List.of(1, 2, 3));

        assertThat(intList.size()).isEqualTo(2);
    }

    @Test
    void shouldClearList() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.isEmpty()).isFalse();

        intList.clear();

        assertThat(intList.isEmpty()).isTrue();
    }

    @Test
    void shouldContainsAllValues() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        final List<Integer> values = List.of(1, 2, 3, 4, 5);

        intList.addAll(values);

        final boolean result = intList.containsAll(values);
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFirstIndex() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 2, 2, 3));

        assertThat(intList.indexOf(2)).isEqualTo(1);
    }

    @Test
    void shouldReturnLastIndex() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 2, 2, 3));

        assertThat(intList.lastIndexOf(2)).isEqualTo(3);
    }

    @Test
    void shouldGetValueByIndex() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.get(2)).isEqualTo(3);
    }

    @Test
    void shouldGetFirstValue() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.getFirst()).isEqualTo(1);
    }

    @Test
    void shouldGetLastValue() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        assertThat(intList.getLast()).isEqualTo(5);
    }

    @Test
    void shouldCreateArray() {
        final SortedLinkedList<Integer> intList = SortedLinkedListFactory.createIntegerList();

        intList.addAll(List.of(1, 2, 3, 4, 5));

        final Object[] array = intList.toArray();
        final Integer[] expectations = {1, 2, 3, 4, 5};

        assertThat(array).isEqualTo(expectations);
    }

    @Test
    void shouldCloneList() {
        final SortedLinkedList<Integer> original = SortedLinkedListFactory.createIntegerList();

        original.addAll(List.of(1, 2, 3, 4, 5));

        final SortedLinkedList<Integer> clone = original.clone();

        assertThat(clone).isNotSameAs(original);
        assertThat(clone).isEqualTo(original);

        clone.add(10);
        assertThat(clone.contains(10)).isTrue();
        assertThat(original.contains(10)).isFalse();
    }

    @Test
    void shouldSupportStream() {
        final SortedLinkedList<Integer> list = SortedLinkedListFactory.createIntegerList();

        list.addAll(List.of(1, 2, 3, 4, 5));

        final long biggerThanFive = list.stream()
                .map(item -> item * 2)
                .filter(item -> item > 5)
                .count();

        assertThat(biggerThanFive).isEqualTo(3);
    }
}
