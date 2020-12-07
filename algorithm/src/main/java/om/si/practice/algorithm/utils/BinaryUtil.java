package om.si.practice.algorithm.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: HongZhenSi
 * @date: 2020/11/29
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class BinaryUtil {


    public static void main(String[] args) {

//        int a = 9;
//        int b = 10;
//        int c  = a ^ b;
//        int c1 = a ^ a;
//        a = a ^ b;
//        b = a ^ b; // ((a ^ b) ^ b) ^ (a ^ b)
//        a = a ^ b;
//
//        log.info("test");

        int[] a = {2, 4, 6, 10, 11};
        changeData(a, 1,2);
        int[] b = {1,2,1,2,5,2,1,1, 2, 5,5};
        int data = jiShuCountData(b);

        log.info("a:{}, data:{}", a, data);
    }

    public static void yiHuo(Integer a, Integer b){


    }


    public static int jiShuCountData(int[] arrays){

        int temp = arrays[0];
        for (int i = 1; i < arrays.length; i++){

            temp = temp ^ arrays[i];
        }
        return temp;

    }

    /**
     *  a ^ a ==  0
     *  a ^ 0 == a
     *  a ^ b ^ c = a ^ (b ^ c)
     *  a ^ b = b ^ a
     * @param arrays
     * @param indexA
     * @param indexB
     */
    public static void changeData(int[] arrays, int indexA, int indexB){

        arrays[indexA] = arrays[indexA] ^ arrays[indexB];
        arrays[indexB] = arrays[indexA] ^ arrays[indexB];
        arrays[indexA] = arrays[indexA] ^ arrays[indexB];

    }
}
