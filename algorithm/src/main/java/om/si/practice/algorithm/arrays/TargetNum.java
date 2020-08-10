package om.si.practice.algorithm.arrays;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;

@Slf4j
public class TargetNum {

    public static int[] getTargetIndexSort(int[] nums, int target){

        int[] storeNum = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(nums);
        log.info("sorted:{}", nums);
        Integer first = null;
        Integer end = null;
        for (int i = 0; i < nums.length - 1; i++){
            int temp = nums[i];
            int[] tempNums = Arrays.copyOfRange(nums, i, nums.length);
//            log.info("temp:{}, tempNums:{}", temp, tempNums);
            int index = Arrays.binarySearch(tempNums, target - temp);
            if (index > -1){
                first = temp;
                end = tempNums[index];
                break;
            }
        }
        if (first == null || end == null) {
            return null;
        }
        Integer firstIndex = null;
        Integer endIndex = null;
        for (int i = 0; i < storeNum.length; i++){
            if (storeNum[i] == first && firstIndex == null){
                firstIndex = i;
                continue;
            }
            if (storeNum[i] == end && endIndex == null){
                endIndex = i;
            }
        }
        return new int[]{firstIndex, endIndex};

    }

//    private static int getTargetIndex(int[] nums, int target){
//
//
//    }

    public static int[] getTargetIndexHash(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        log.info("map:{}", map);
        for (int i = 0 ; i < nums.length; i++){
            Integer targetIndex = map.get(target - nums[i]);
            if ( targetIndex != null && targetIndex != i){
                return new int[]{i, targetIndex};
            }
        }
        return null;
    }

    public static int[] getTargetIndexHashOnce(int[] nums, int target){

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){

            Integer index = map.get(target - nums[i]);
            if (index != null && index != i){
                return new int[]{i, index};
            }
            map.put(i, nums[i]);
        }
        return null;
    }

    private static int[] getTargetIndex(int[] source, int target){

        for(int i = 0; i < source.length; i++){

            int first = source[i];
            for (int j = i + 1; j < source.length; j++){
                if (first + source[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static int getRandom(int maxNumber){

        return (int)(Math.random() * maxNumber);
    }

    private static int[] init(int size, int maxNumber){
        int[] source = new int[size];
        for (int i = 0; i < size; i++){
            source[i] = getRandom(maxNumber);
        }
        return source;
    }

    public static void main(String[] args) {
        int size = 100000000;
//        int[] source = init(size, 1000);
        int[] source = {3, 3};
        int[] index =   getTargetIndexSort(source, 6);
//        int[] index = getTargetIndexHash(source, 6);
//        int[] index = getTargetIndexHashOnce(source, 6);
//        long startTime = System.currentTimeMillis();
////        int[] index =  getTargetIndex(source, 1999);
////        log.info("time:{}, index:{}", System.currentTimeMillis() - startTime, index);
//        int[] indexHash = getTargetIndexHash(source, 6);
//        if (indexHash != null){
//            log.info("first:{}, second:{}", source[indexHash[0]], source[indexHash[1]]);
//        }
//        log.info("time:{}, indexHash:{}", System.currentTimeMillis() - startTime, indexHash);
        log.info(" index:{}", index);
    }
}
