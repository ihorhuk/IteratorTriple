package com.hip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TripleIteratorTest {

    private TripleIterator testObject;
    List<Integer> expectedList = List.of(1,2,3,4,5,6,8,9,18,19,21,22,24,32,33,44,45,46,57,59,66,67,77,89,89,89,89);

    @Test
    void sort() {
        List<Integer> listA1 = List.of(6, 8, 19, 21, 32, 66, 67, 77, 89);
        List<Integer> listA2 = List.of(1, 3, 5,  24, 33, 45, 57, 59, 89);
        List<Integer> listA3 = List.of(2, 4, 9,  18, 22, 44, 46, 89, 89);

        testObject = new TripleIterator(new ArrayList<>(listA1),new ArrayList<>(listA2),new ArrayList<>(listA3));
        List<Integer> sortedList = testObject.sort();
        Assertions.assertArrayEquals(expectedList.toArray(),sortedList.toArray());
    }
}