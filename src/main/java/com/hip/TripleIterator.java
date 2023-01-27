package com.hip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.nonNull;

public class TripleIterator {
    Iterator<Integer> a1;
    Iterator<Integer> a2;
    Iterator<Integer> a3;

    public TripleIterator(List<Integer> a1List, List<Integer> a2List, List<Integer> a3List) {
        this.a1 = resolveSortedIterator(a1List);
        this.a2 = resolveSortedIterator(a2List);
        this.a3 = resolveSortedIterator(a3List);
    }

    private Iterator<Integer> resolveSortedIterator(List<Integer> integerList) {
        if (nonNull(integerList) && !integerList.isEmpty()) {
            Collections.sort(integerList);
            return new ArrayList<>(integerList).iterator();
        }
        return Collections.emptyIterator();
    }

    public List<Integer> sort() {
        List<Integer> resultList = new ArrayList<>();

        boolean a1HasNext = a1.hasNext();
        boolean a2HasNext = a2.hasNext();
        boolean a3HasNext = a3.hasNext();
        int a1Value = a1HasNext ? a1.next() : Integer.MAX_VALUE;
        int a2Value = a1HasNext ? a2.next() : Integer.MAX_VALUE;
        int a3Value = a1HasNext ? a3.next() : Integer.MAX_VALUE;

        while (a1HasNext || a2HasNext || a3HasNext) {

            boolean needTurn12Iterators = false;
            boolean needTurn23Iterators = false;
            boolean needTurn13Iterators = false;
            if (isFirstElementMinimal(a1HasNext, a2HasNext, a3HasNext, a1Value, a2Value, a3Value)) {
                resultList.add(a1Value);
                System.out.println("add from first list : " + a1Value);
                a1.remove();
                a1HasNext = a1.hasNext();
                a1Value = a1HasNext ? a1.next() : Integer.MAX_VALUE;
                needTurn23Iterators = true;
            }
            if (isFirstElementMinimal(a2HasNext, a1HasNext, a3HasNext, a2Value, a1Value, a3Value)) {
                resultList.add(a2Value);
                System.out.println("add from second list : " + a2Value);
                a2.remove();
                a2HasNext = a2.hasNext();
                a2Value = a2HasNext ? a2.next() : Integer.MAX_VALUE;
                needTurn13Iterators = true;
            }
            if (isFirstElementMinimal(a3HasNext, a1HasNext, a2HasNext, a3Value, a1Value, a2Value)) {
                resultList.add(a3Value);
                System.out.println("add from third list : " + a3Value);
                a3.remove();
                a3HasNext = a3.hasNext();
                a3Value = a3HasNext ? a3.next() : Integer.MAX_VALUE;
                needTurn12Iterators = true;
            }
            boolean notProcessAny = !(needTurn12Iterators || needTurn13Iterators || needTurn23Iterators);
            if (notProcessAny) {
                if (a1HasNext) {
                    resultList.add(a1Value);
                    System.out.println("add from first list : " + a1Value);
                    a1.remove();
                    a1HasNext = a1.hasNext();
                    a1Value = a1HasNext ? a1.next() : Integer.MAX_VALUE;
                }
                if (a2HasNext) {
                    resultList.add(a2Value);
                    System.out.println("add from second list : " + a2Value);
                    a2.remove();
                    a2HasNext = a2.hasNext();
                    a2Value = a2HasNext ? a2.next() : Integer.MAX_VALUE;
                }
                if (a3HasNext) {
                    resultList.add(a3Value);
                    System.out.println("add from third list : " + a3Value);
                    a3.remove();
                    a3HasNext = a3.hasNext();
                    a3Value = a3HasNext ? a3.next() : Integer.MAX_VALUE;
                }
            }
        }
        return resultList;
    }

    private boolean isFirstElementMinimal(boolean isFirstElementExist, boolean isSecondElementExist, boolean isThirdElementExist,
                                          int firstValue, int secondValue, int thirdValue) {
        if (isFirstElementExist && !isSecondElementExist && !isThirdElementExist) {
            return true;
        }
        return isFirstElementExist && (isSecondElementExist && (firstValue <= secondValue)) && (isThirdElementExist && (firstValue <= thirdValue));
    }
}
