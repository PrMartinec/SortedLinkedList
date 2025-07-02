# SortedLinkedList Library

This library provides a simple implementation of a **sorted singly linked list** in Java.  
Automatically maintains sorted order and supports either Integer or String, but not both in the same list.

> This implementation was created as part of a programming task for **ShipMonk** created by **Petr Martinec**.

## Features

- ✅ Maintains natural order (`Comparable`)
- ✅ Supports only one type per list (e.g. `Integer` or `String`)
- ✅ Allows duplicates
- ✅ Efficient insertions and deletions
- ✅ Factory-style API (static or Spring-friendly)

## Usage Example

```j[README.md](..%2F..%2FAppData%2FLocal%2FTemp%2FREADME.md)ava
SortedLinkedList<Integer> intList = LinkedListFactory.createIntegerList();
intList.add(3);
intList.add(1);
intList.add(2);
System.out.println(intList); // Output: [1, 2, 3]
