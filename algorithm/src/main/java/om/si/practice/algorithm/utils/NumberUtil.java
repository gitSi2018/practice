package om.si.practice.algorithm.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: HongZhenSi
 * @date: 2020/11/27
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class NumberUtil {


    public static int[] generateRandomArray(int maxSize, int maxNumber) {

        int size = randomData(maxSize);
        if (size == 0) return new int[0];

        int[] arrays = new int[size];
        for (int i = 0; i < size; i++) {
            arrays[i] = randomData(maxNumber);
        }
        return arrays;
    }

    public static Integer[] generateRandomIntegerArray(int maxSize, int maxNumber) {

        int size = randomData(maxSize);
        if (size == 0) return new Integer[0];

        Integer[] arrays = new Integer[size];
        for (int i = 0; i < size; i++) {
            arrays[i] = randomData(maxNumber);
        }
        return arrays;
    }

    public static int randomData(int max) {

        return (int) (Math.random() * max);
    }


    public static void changeArrayIndexData(int[] arrays, int indexA, int indexB) {

        int temp = arrays[indexA];
        arrays[indexA] = arrays[indexB];
        arrays[indexB] = temp;
    }

    public static void moveArrayData(int[] arrays, int moveStartIndex, int moveEndIndex) {
        if (moveEndIndex - moveStartIndex <= 0) return;
        for (int i = moveEndIndex - 1; i >= moveStartIndex; i--) {
            arrays[i + 1] = arrays[i];
        }
    }

    public static int randomData(int start, int end) {

        return (int) (start + (end - start) * Math.random());
    }
}