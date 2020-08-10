package om.si.practice.algorithm.order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DivideAndConquer {


    public static void main(String[] args) {
        int[] source = {2, 5,1, 7, 4, 12, 87, 34, 76,90,0,1};
        log.info("order:{}", divideAndConquerSort(source, true));
    }

    /**
     *  分治法。
     *  1. 考量怎么分解子问题
     *  2. 考量子问题何时到底（不再分解的子问题）
     *  3. 合并如何正确的处理
     * @param source
     * @return
     */
    public static int[] divideAndConquerSort(int[] source, boolean asc){

        // 参数校验
        if(source == null || source.length == 0){
            return source;
        }
        // 子问题出口
        if (source.length == 1){
            return source;
        }

        // 二分法分解问题规模 为两个子问题
        int length = source.length;
        int middleIndex = length >> 1;
        int[] leftSource = copy(source, 0, middleIndex);
        int[] rightSource = copy(source, middleIndex, source.length);
        int[] leftResult = divideAndConquerSort(leftSource, asc);
        int[] rightResult = divideAndConquerSort(rightSource, asc);

        return merge(leftResult, rightResult, asc);
    }
    // 左闭右开区间
    public static int[] copy(int[] source, int from, int to){

        int[] temp = new int[to - from];
        for (int i = from; i < to; i++){
            try {
                temp[i - from] = source[i];
            }catch (Exception e){
                log.error("source:{}, from:{}, to:{}", source, from, to, e);
            }

        }
        return temp;
    }

    public static int[] merge(int[] left, int[] right, boolean asc){

        int leftLength = left.length;
        int rightLength = right.length;

        // todo 使用哨兵，谁大谁小一点关系都没有。后续合并使用这个bigger导致数组越界
//        boolean leftBigger = leftLength - rightLength > 0;
        int maxLength = leftLength + rightLength;
        int[] merged = new int[maxLength];
        int leftNextIndex = 0;
        int rightNextIndex = 0;
        for (int i = 0; i < maxLength; i++){
            if(rightNextIndex >= rightLength){
                merged[i] = left[leftNextIndex++];
                continue;
            }
            if (leftNextIndex >= leftLength){
                merged[i] = right[rightNextIndex++];
                continue;
            }
            boolean leftSmaller = left[leftNextIndex] - right[rightNextIndex] < 0;
            // 下面这些逻辑 比较复杂，分清楚一点易于维护
            // 升序
            if (asc){
                if (leftSmaller) {
                    merged[i] = left[leftNextIndex++];
                    continue;
                }
                merged[i] = right[rightNextIndex++];
                continue;
            }
            // 降序，并且left大
            if (!leftSmaller) {
                merged[i] = left[leftNextIndex++];
                continue;
            }
            merged[i] = right[rightNextIndex++];
            //
        }
        return merged;
    }
}
