package om.si.practice.algorithm.Strings;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.*;

@Slf4j
public class StringsOperator {


    public static void main(String[] args) {

//        String sourceStr = "abcabcbb";
//        String sourceStr = "au";
//        String sourceStr = "bbb";
//        String sourceStr = "b";
//        String sourceStr = "bpfbhmipx";
//        String sourceStr = "dvdf";
//        String sourceStr = "abcdd";

//        int length1 = lengthOfLongestSubstring(sourceStr);
//        int length2 = maxSubStringLength(sourceStr);
//        int length3 = maxSubStr(sourceStr);
//        log.info("\nlength1:{} \nlength2:{} \nlength3:{}", length1, length2, length3);
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("A", 1);
//        Integer temp = map.putIfAbsent("A", 2);
//        log.info("temp:{}", temp);
//
//        int[] a = {3};
//        int[] b = {2};
//        double middle = middleDouble(a, b);
//        log.info("middle:{}", middle);
        int[] array = {12, 15,16, 17};
//        int index = binarySearch(array, 12);
//        int index = insetIndex(array, 17);
//        int index = insertIndex(array, 1, 2, 1);
//        log.info("index:{}", index);
        int[] arrayB = {20, 33, 50, 50};
        double middle = middleDoubleThree(array, arrayB);
        log.info("middle:{}", middle);
    }

    public static int lengthOfLongestSubstring(String sourceStr) {

        char[] dataChars = sourceStr.toCharArray();
        if (dataChars.length == 0){
            return 0;
        }
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int startIndex = 0;
//        char[] maxLengthChar = new char[0];
        int maxLength = 1;

        for (int i = 0; i < dataChars.length; i++){
            Integer targetIndex = charIndexMap.get(dataChars[i]);
            if (targetIndex == null){
                // 还没有重复，保存当前的char 后进行下一轮循环。错了，要考虑最后一次一直没有重复的情况。
                charIndexMap.put(dataChars[i], i);
                if (i == dataChars.length - 1){
//                    char[] subChar = Arrays.copyOfRange(dataChars, startIndex, dataChars.length);
//                    log.info("last sunChar:{}", subChar);
                    maxLength = Math.max(maxLength, dataChars.length - startIndex);
                    return maxLength;
                }
                continue;
            }
//            char[] subChar = Arrays.copyOfRange(dataChars, startIndex, i);
//            log.info("subChar:{}, start={}, i={}", subChar, startIndex, i);
//            maxLengthChar = maxLengthChar.length >= subChar.length ? maxLengthChar : subChar;
            maxLength = Math.max(maxLength, i - startIndex);
            startIndex = targetIndex + 1;
            charIndexMap = new HashMap<>();
            for (int j = startIndex; j <= i; j++) {
                charIndexMap.put(dataChars[j], j);
            }

        }
//        log.info("maxLengthChar:{}", maxLengthChar);
//        return maxLengthChar.length;
        return maxLength;
    }


    public static int maxSubStringLength(String source){
        if (source == null || "".equals(source)){
            return 0;
        }
//        char[] chars = source.toCharArray();
        int maxSubStrLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < source.length(); i++){
            Character temp = source.charAt(i);
            Integer repeatCharIndex;
            if( (repeatCharIndex = map.putIfAbsent(temp, i)) == null){
                continue;
            }
            maxSubStrLength = Math.max(maxSubStrLength, map.size());
//            log.info("map:{}", map);
            for (int j = repeatCharIndex; j >= 0; j--){
                Integer index;
                if ( ( index = map.get(source.charAt(j))) == null){
                    break;
                }
                if (index == j) {
                    map.remove(source.charAt(j));
                }
            }
            map.put(temp, i);
        }
        return Math.max(maxSubStrLength, map.size());
    }

    public static int maxSubStr(String string){

        if (string == null || "".equals(string)) return 0;
        int length = string.length();
        if (length == 1) return 1;
        int startIndex = 0;
        int maxLength = 0;
//        int endIndex = 1;
        for (int i = 1 ; i < length; i++){
//            if(startIndex == i) continue;
            for (int j = startIndex; j < i; j++){
                if (string.charAt(j) != string.charAt(i)){
                    continue;
                }
                maxLength  = Math.max(maxLength, Math.max(j - startIndex  + 1, i - j));
                startIndex = j + 1;
                break;
            }
        }
        return Math.max(maxLength, length - startIndex);
    }


    /**
     *  使用划线的方式确定。 基数个数时
     * @param a
     * @param b
     * @return
     */
    public static double middleDoubleThree(int[] a, int[] b){

        if (a == null) a = new int[0];
        if (b == null) b = new int[0];
        if (a.length > b.length){
            int[] temp = a;
            a = b;
            b = temp;
        }
        int leftLength = a.length +  (b.length - a.length + 1)/ 2;
        int start = 0;
        int end = a.length;
        int middleA = 0;
        int middleB = 0;
        int maxLeftMiddleA = 0;
        int minRightMiddleA = 0;
        int maxLeftMiddleB = 0;
        int minRightMiddleB = 0;
        while (start <= end){

            middleA = start + (end - start + 1) / 2;
            maxLeftMiddleA = middleA <= 0 ? Integer.MIN_VALUE : a[middleA - 1];
            minRightMiddleA = middleA >= a.length ? Integer.MAX_VALUE : a[middleA];
            middleB = leftLength - middleA;
            maxLeftMiddleB = middleB <= 0 ? Integer.MIN_VALUE : b[middleB - 1];
            minRightMiddleB = middleB >= b.length ? Integer.MAX_VALUE : b[middleB];
            if (maxLeftMiddleA > minRightMiddleB){
                end = middleA - 1;
                continue;
            }
            if (maxLeftMiddleB > minRightMiddleA){
                start = middleA + 1;
                continue;
            }
            break;
        }
        int minRight =  Math.min(minRightMiddleA, minRightMiddleB);
        int maxLeft = Math.max(maxLeftMiddleA, maxLeftMiddleB);
        if ((b.length - a.length) % 2 == 0){
            // 偶数
            return minRight + ( maxLeft - minRight) / 2.0;
        }
        //奇数个
        return maxLeft;

    }

    /**
     *  实现上太复杂了。因为要确定在第二个数组的的index，当需要插入的数据相等，或者另外个数组有相同的数据时，
     *  插入就无法每次都准确的确定位置，导致程序出bug。
     *   总结，想通过查询当前元素在另外一个数组的位置来确定 比他小的和大的数据数量，不是一个合适的方法。理由如上
     *
     *   正确方式： 看 middleDoubleThree
     * @param a
     * @param b
     * @return
     */
    public static double middleDoubleTwo(int[] a, int[] b){

        int aNoSureBeginIdx = 0;
        int aNoSureEndIdx = a == null ? 0 : Math.max(a.length - 1, 0);
        int bNoSureBeginIdx = 0;
        int bNoSureEndIdx = b == null ? 0 : Math.max(b.length - 1, 0);
        int aLength = a.length;
        int bLength = b.length;
        while (true){

            int aNoSureLength = aNoSureEndIdx - aNoSureBeginIdx;
            int bNoSureLength = bNoSureEndIdx - bNoSureBeginIdx;
            int middleSize = (aNoSureLength + bNoSureLength) / 2;
            // 确定middle 落在那个数组，以及在该数组中的真实index
            int[] middleInArray = b;
            int[] middleNoInArray = a;
            //向下取整
            int middleIndex = bNoSureBeginIdx + (bNoSureLength - middleSize) / 2 * 2;
            int middleInNoSureBeginIdx = bNoSureBeginIdx;
            int middleInNoSureEndIdx = bNoSureEndIdx;
            int middleNotInNoSureBeginIdx = aNoSureBeginIdx;
            int middleNotInNoSureEndIdx = aNoSureEndIdx;
            if (aNoSureLength > bNoSureLength){
                middleInArray = a;
                middleNoInArray = b;
                middleIndex = aNoSureBeginIdx + middleSize;
                middleInNoSureBeginIdx = aNoSureBeginIdx;
                middleInNoSureEndIdx = aNoSureEndIdx;
                middleNotInNoSureBeginIdx = bNoSureBeginIdx;
                middleNotInNoSureEndIdx = bNoSureEndIdx;
            }
            //      确定middle是否是中点
            // 查询出在另外一个数组中的插入位置
            int insertIdx = insertIndex(middleNoInArray, middleNotInNoSureBeginIdx, middleNotInNoSureEndIdx, middleInArray[middleIndex]);
            // 计算middle left 与 right的长度差
            int leftLength = middleIndex + insertIdx;
            int rightLength = (middleInArray.length - 1 - middleIndex) + (Math.max(0, middleNoInArray.length - 1 - insertIdx));
            if (leftLength == rightLength) return  0.0;
        }
    }

    public static double middleDouble(int[] one, int[] two){
        if (one == null || one.length == 0){
            int length = two.length;
            if (length == 1) return two[0];
            int middle = length / 2;
            return length % 2 == 1 ? two[middle] : two[middle] + two[middle - 1];
        }
        if (two == null || two.length == 0){
            int length = one.length;
            if (length == 1) return one[0];
            int middle = length / 2;
            return length % 2 == 1 ? one[middle] : one[middle] + one[middle - 1];
        }
        int lengthOne = one.length;
        int lengthTwo = two.length;
        int notSureStartIndexInOne = 0;
        int notSureEndIndexInOne = one.length - 1;
        int notSureStartIndexInTwo = 0;
        int notSureEndIndexInTwo = two.length - 1;
        boolean isGiShu = (lengthOne + lengthTwo) % 2 == 1;

        // 以数组较长的为基准进行搜索
        while (true){
            int notSureLengthInOne = notSureEndIndexInOne - notSureStartIndexInOne;
            int notSureLengthInTwo = notSureEndIndexInOne - notSureStartIndexInTwo;
            int middle = (notSureLengthInOne + notSureLengthInTwo) / 2;
            //确定此次middle落在哪个数组
            if (notSureLengthInOne > notSureLengthInTwo){
                //middle 落在one数组
                int middleIndex = notSureStartIndexInOne + middle;

                int middleLeftLengthA = middleIndex;
                int middleRightLengthA = lengthOne - middleIndex;
                int insertIndex = insertIndex(two, notSureStartIndexInTwo, notSureEndIndexInTwo, one[middleIndex]);
                // 计算是否是中间的数字
                int middleLeftLengthB = insertIndex;
                int middleRightLengthB = lengthTwo - insertIndex;

                int leftSubRight = (middleLeftLengthA + middleLeftLengthB) - (middleRightLengthA + middleRightLengthB);
                if (leftSubRight == 0){
                    return one[middleIndex];
                }
                if (leftSubRight == -1 && !isGiShu){
                    if (middleIndex == lengthOne - 1) return (one[middleIndex] + two[insertIndex + 1]) / 2.0;
                    if (insertIndex == lengthTwo - 1) return (one[middleIndex] + one[middleIndex + 1]) / 2.0;
                    return (one[middleIndex] + Math.min(one[middleIndex + 1], two[insertIndex + 1])) / 2.0;
                }
                if (leftSubRight == 1 && !isGiShu){
                    if (middleIndex == 0) return (one[middleIndex] + two[insertIndex - 1]) / 2.0;
                    if (insertIndex == 0) return (one[middleIndex] + one[middleIndex - 1]) / 2.0;
                    return (one[middleIndex] + Math.max(one[middleIndex - 1], two[insertIndex - 1]));
                }
                // 调整
                if (leftSubRight < 0){
                    // 不可能出现在这些左边的数据中了
                    notSureStartIndexInOne = middleIndex;
                    notSureStartIndexInTwo = insertIndex;
                    continue;
                }
                notSureEndIndexInOne = middleIndex;
                notSureEndIndexInTwo = insertIndex;
            }
            // middle 落在two数组
            int middleIndex = notSureStartIndexInTwo + middle;

            int middleLeftLengthA = middleIndex;
            int middleRightLengthA = lengthOne - middleIndex;
            int insertIndex = insertIndex(one, notSureStartIndexInOne, notSureEndIndexInOne, two[middleIndex]);
            // 计算是否是中间的数字
            int middleLeftLengthB = insertIndex;
            int middleRightLengthB = lengthTwo - insertIndex;

            int leftSubRight = (middleLeftLengthA + middleLeftLengthB) - (middleRightLengthA + middleRightLengthB);
            if (leftSubRight == 0){
                return two[middleIndex];
            }
            if (leftSubRight == -1 && !isGiShu){
                if (middleIndex == lengthTwo - 1) return (two[middleIndex] + one[insertIndex + 1]) / 2.0;
                if (insertIndex == lengthOne - 1) return (two[middleIndex] + two[middleIndex + 1]) / 2.0;
                return (two[middleIndex] + Math.min(one[middleIndex + 1], two[insertIndex + 1])) / 2.0;
            }
            if (leftSubRight == 1 && !isGiShu){
                if (middleIndex == 0) return (two[middleIndex] + one[insertIndex - 1]) / 2.0;
                if (insertIndex == 0) return (two[middleIndex] + two[middleIndex - 1]) / 2.0;
                return (two[middleIndex] + Math.max(one[middleIndex - 1], two[insertIndex - 1]));
            }
            // 调整
            if (leftSubRight < 0){
                // 不可能出现在这些左边的数据中了
                notSureStartIndexInOne = insertIndex;
                notSureStartIndexInTwo = middleIndex;
                continue;
            }
            notSureEndIndexInOne = insertIndex;
            notSureEndIndexInTwo = middleIndex;

        }

    }



    public static int insertIndex(int[] array, int sourceStartIndex, int sourceEndIndex, int target){

        Assert.notNull(array, "null");

        int startIndex = Math.max(0, sourceStartIndex);
        int endIndex = Math.min(array.length, sourceEndIndex);
        while (startIndex <= endIndex){

            int middle = (startIndex + endIndex) / 2;
            if (target == array[middle]) return middle;

            if (target > array[middle]){
                if (middle == sourceEndIndex) return middle + 1;
                if (target <= array[middle + 1]) return middle + 1;
                startIndex = middle + 1;
                continue;
            }
            if (middle == startIndex) return middle;
            if (target >= array[startIndex - 1]) return middle;
            endIndex = middle - 1;
        }
        throw new RuntimeException("insertIndex no index found bug!");
    }

    public static int binarySearch(int[] array, int target){

        Assert.notNull(array, "array must not be null!");
        int startIndex = 0;
        int endIndex = array.length - 1;
        while (startIndex <= endIndex){
            int middle = (startIndex + endIndex) / 2;
            if (target == array[middle]) return middle;
            if (target > array[middle]) startIndex = middle + 1;
            else endIndex = middle - 1;
        }
        return -1;
    }

    public static int insetIndex(int[] array, int target){
        Assert.notNull(array, "");
        int startIndex = 0;
        int endIndex = array.length - 1;
        while (startIndex <= endIndex){
            int middle = (startIndex + endIndex) / 2;
            // 有相同元素时在 相同元素当前位置
            if (target == array[middle]) return middle;

            if(target > array[middle]){
                if (middle == array.length - 1) return middle + 1;
                if (target <= array[middle +1]) return middle + 1;
                startIndex = middle + 1;
                continue;
            }
            if (middle == 0) return 0;
            if (target >= array[middle - 1]) return middle;
            endIndex = middle - 1;
        }
        return -1;
    }
}
