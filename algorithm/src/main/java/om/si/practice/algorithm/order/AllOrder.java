package om.si.practice.algorithm.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.si.practice.algorithm.utils.NumberUtil;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author: HongZhenSi
 * @date: 2020/11/27
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class AllOrder {


    public static void main(String[] args) {

        multiCheck(100, 1000, 100000);
    }

    public static boolean multiCheck(int checkTimes, int maxSize, int maxNumber){

        for (int i = 0; i < checkTimes; i++){

            int[] arrays = NumberUtil.generateRandomArray(maxSize, maxNumber);
            log.info("generate arrays:{}", arrays);
            boolean check = check(arrays);
            Assert.isTrue(check, "order failed");
        }
        return true;
    }

    /**
     *  校验器。校验排序是否正常
     * @param arrays
     * @return
     */
    public static boolean check(int[] arrays){

        int[] arrays1 = Arrays.copyOf(arrays, arrays.length);
        int[] arrays2 = Arrays.copyOf(arrays, arrays.length);
        Arrays.sort(arrays1);
//        chooseOrder(arrays2);
//        maoPaoOrder(arrays2);
//        insertOrder(arrays2);
//        quickOrderDiGui(arrays);
        guiBingTest(arrays2);
        List<NoEqual> noEquals = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++){
            if (arrays1[i] != arrays2[i]){
                noEquals.add(new NoEqual(i, arrays2[i], arrays1[i]));
            }
        }
        if (CollectionUtils.isEmpty(noEquals)){
//            log.info("all is the same");
            return true;
        }

        log.info("noEquals:{}", noEquals);
        return false;
    }

    /**
     *  选择排序，从左往右依次确定相应index上的排序后的数据是多少
     * @param arrays
     * @return
     */
    public static int[] chooseOrder(int[] arrays){
        if (arrays == null || arrays.length <= 1){
            return arrays;
        }

//        log.info("before order:{}", arrays);
        for (int i = 0; i < arrays.length; i++){
            //第一层循环决定找到 index 为 i上的那个排序之后的数是什么
            int smallerIndex = i;
            for (int j = i; j < arrays.length - 1; j++){
                //第二层循环寻找 i 以及 i之后的数组中最小的数字
                smallerIndex = arrays[smallerIndex] > arrays[j + 1] ? j + 1 : smallerIndex;
            }

            //交换数据
            int smallerData = arrays[smallerIndex];
            if (smallerIndex == i) continue;
            arrays[smallerIndex] = arrays[i];
            arrays[i] = smallerData;
        }

//        log.info("after order:{}", arrays);
        return arrays;
    }


    public static void maoPaoOrder(int[] arrays){

        if (arrays == null || arrays.length <= 1) return;
        for (int i = arrays.length - 1; i > 0; i--){

            for (int j = 0; j < i; j++){

                int biggerData =  arrays[j];
                if (biggerData > arrays[j+1]){



                    arrays[j] = arrays[j+1];
                    arrays[j+1] = biggerData;
                }

            }
        }
    }


    /**
     *  插入排序为什么在确定index 为i的数据在左边（0 ~ i-1）已经排好序中的位置时，采用的是从右往左依次比较，而不是二分比较？
     *   如果数据本来就大体有序，那么实际上排序的时间复杂度接近于 O(n)，使用二分插入也得要O(n log^n),还不如直接使用快速排序
     *
     * @param arrays
     */
    public static void insertOrder(int[] arrays){
        if (arrays == null || arrays.length <= 1) return;

        for (int i = 1; i < arrays.length; i++){

            for (int j = i; j > 0; j--){
                if (arrays[j] >= arrays[j -1]) break;

                NumberUtil.changeArrayIndexData(arrays, j, j - 1);
            }
        }
    }

    public static void quickOrderDiGui(int[] arrays){

        if (arrays == null || arrays.length <= 1) return;
        arrays = quickOrderDiGui(arrays, 0, arrays.length - 1);

    }

    private static int[] quickOrderDiGui(int[] arrays, final int startIndex, final int endIndex){
        if (endIndex - startIndex < 1) return arrays;
//        int startData = arrays[startIndex];
        int finalIndex = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++){
            if (arrays[i] < arrays[finalIndex]){
                int startData = arrays[finalIndex];
                arrays[finalIndex] = arrays[i];
                ++finalIndex;
                NumberUtil.moveArrayData(arrays, finalIndex, i);
                arrays[finalIndex] = startData;
            }
        }
        final int finalIndexTemp = finalIndex;
        quickOrderDiGui(arrays, startIndex, finalIndexTemp - 1);
        quickOrderDiGui(arrays, finalIndexTemp + 1, endIndex);
        log.info("arrays:{}", arrays);
        return arrays;
    }

//    private static int[] quickOrderFor(int[] arrays, int startIndex, int endIndex){
//
//        if (arrays == null || arrays.length <= 1) return arrays;
//        while (true){
//            if (sta)
//        }
//    }


    public static void guiBingTest(int[] arrays){

        guiBingOrder(arrays, 0, arrays.length - 1);
        int a = 0;
    }

    public static void guiBingOrder(int[] arrays, int left, int right){

        if (left >= right) return;
        int middle = left + (right - left) / 2;
        guiBingOrder(arrays, left, middle);
        guiBingOrder(arrays, middle + 1, right);
        merge(arrays, left, middle, right);
    }

    public static void merge(int[] arrays, int left, int m, int right){

        int[] temp = new int[right - left + 1];
        int r = m + 1;
        int l = left;
        int index = 0;
        while (l <= m && r <= right){

            temp[index++] = arrays[l] <= arrays[r] ? arrays[l++] : arrays[r++];
        }
        while (l <= m){
            temp[index++] = arrays[l++];
        }
        while (r <= right){

            temp[index++] = arrays[r++];
        }
        index = 0;
        for (int i = left; i <= right; i++){
            arrays[i] = temp[index++];
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class NoEqual{

        private int index;

        private int mySortData;

        private int rightData;


    }
}
