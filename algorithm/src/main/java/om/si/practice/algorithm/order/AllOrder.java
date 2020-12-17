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

        multiCheck(1000, 1000, 1000);
//        zuiXiaoHCheck(10, 100, 1000);
//        niXuCheck(1000, 10000, 1000000);
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
        quickOrderDiGui(arrays2);
//        quickOrderDiGuiTempSpace(arrays2, 0, arrays2.length - 1);
//        guiBingTest(arrays2);

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
//        arrays = quickOrderDiGui(arrays, 0, arrays.length - 1);

        quickOrder(arrays, 0, arrays.length - 1);
    }

    //该方法使用了中间数组，空间复杂度为O(n)，减少了移位操作。但实际上空间复杂度在O(1)的情况下，频繁的移位操作可以巧妙的避免。
    private static int[] quickOrderDiGui(int[] arrays, final int startIndex, final int endIndex){
        if (endIndex - startIndex < 1) return arrays;
//        int startData = arrays[startIndex];
        int finalIndex = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++){
            //使用中间数组来存储吧，如果不用额外的空间，移位的操作也可能很频繁
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
//        log.info("arrays:{}", arrays);
        return arrays;
    }

//    private static void quickOrderDiGuiTempSpace(int[] arrays, final int startIndex, final int endIndex){
//        if (endIndex - startIndex <= 0) return;
//        int firstData = arrays[startIndex];
//        int[] tempArray = new int[endIndex - startIndex + 1];
//        int tailIndex = 0;
//        int headIndex = tempArray.length - 1;
//        for (int i = startIndex + 1; i <= endIndex; i++){
//            if (arrays[i] > firstData){
//                tempArray[headIndex--] = arrays[i];
//                continue;
//            }
//
//            tempArray[tailIndex++] = arrays[i];
//        }
//        final int finalIndex = startIndex + tailIndex;
//        for (int i = startIndex; i <= endIndex; i++){
//            if(i == finalIndex) {
//                arrays[i] = firstData;
//                continue;
//            }
//            arrays[i] = tempArray[i - startIndex];
//        }
//        quickOrderDiGuiTempSpace(arrays, startIndex, finalIndex - 1);
//        quickOrderDiGuiTempSpace(arrays, finalIndex + 1, endIndex);
//    }

//    private static int[] quickOrderFor(int[] arrays, int startIndex, int endIndex){
//
//        if (arrays == null || arrays.length <= 1) return arrays;
//        while (true){
//            if (sta)
//        }
//    }

    private static void quickOrderDiGuiTempSpace(int[] arrays, final int startIndex, final int endIndex){

        if (endIndex - startIndex  <= 0) return;
        int firstData = arrays[startIndex];
        int[] tempArray = new int[endIndex - startIndex + 1];
        int headIndex = tempArray.length - 1;
        int tailIndex = 0;
        for (int i = startIndex + 1; i <= endIndex; i++){
            if (arrays[i] < firstData){
                tempArray[tailIndex++] = arrays[i];
                continue;
            }
            tempArray[headIndex--] = arrays[i];
        }
        final int finalIndex = startIndex + tailIndex;
        for (int i = startIndex; i <= endIndex; i++){
            if (i == finalIndex) {
                arrays[i] = firstData;
                continue;
            }
            arrays[i] = tempArray[i - startIndex];
        }
        quickOrderDiGuiTempSpace(arrays, startIndex, finalIndex - 1);
        quickOrderDiGuiTempSpace(arrays, finalIndex + 1, endIndex);

    }


    public static void guiBingTest(int[] arrays){


        int minSum = guiBingOrderZuiXiaoHe(arrays, 0, arrays.length - 1);

    }

    public static void zuiXiaoHCheck(int checkTimes, int maxSize, int maxNumber){


        for (int i = 0; i < checkTimes; i++){

            int[] arrays = NumberUtil.generateRandomArray(maxSize, maxNumber);
            log.info("generate arrays:{}", arrays);
            int sum1 = zuiXiaoHeCommon(arrays);
            int sum2 = guiBingOrderZuiXiaoHe(arrays, 0, arrays.length - 1);
            log.info("sum1:{}, sum2:{}", sum1, sum2);
            boolean check = sum1 == sum2;
            Assert.isTrue(check, "order failed");
        }
    }

    public static void niXuCheck(int checkTimes, int maxSize, int maxNumber){

        for (int i = 0; i  < checkTimes; i++){

            int[] arrays = NumberUtil.generateRandomArray(maxSize, maxNumber);
            int count1 = niXuDuiCommon(arrays);
            int count2 = guiBingNiXuDui(arrays, 0, arrays.length - 1);
            log.info("count1:{}, count2:{}", count1, count2);
            Assert.isTrue(count1 == count2, "failed");
        }
    }

    // n^2 最小和
    public static int zuiXiaoHeCommon(int[] arrays){
        if (arrays.length <= 1) return 0;
        int sum = 0;
        for (int i = 1; i < arrays.length; i++){
            int data = arrays[i];
            for (int j = 0; j < i; j++){
                sum += arrays[j] < data ? arrays[j] : 0;
            }
        }
        return sum;
    }

    // n^2 逆序对
    public static int niXuDuiCommon(int[] arrays){

        if (arrays.length <= 1) return 0;
        int sum = 0;
        for (int l = 0; l < arrays.length; l++){
            int lData = arrays[l];
            for (int r = l + 1; r < arrays.length; r++){

                sum += lData > arrays[r] ? 1  : 0;
            }
        }
        return sum;
    }

//    public static void guiBingOrder(int[] arrays, int left, int right){
//
//        if (left >= right) return;
//        int middle = left + (right - left) / 2;
//        guiBingOrder(arrays, left, middle);
//        guiBingOrder(arrays, middle + 1, right);
//        merge(arrays, left, middle, right);
//    }

    // 最小和
    public static int guiBingOrderZuiXiaoHe(int[] arrays, int start, int end){

        if (end - start <= 0) return 0;
        int m = start + ((end - start) >> 1);
        return guiBingOrderZuiXiaoHe(arrays, start, m) +
                guiBingOrderZuiXiaoHe(arrays, m + 1, end) +
                mergeZuiXiaohe(arrays, start, m, end);

    }
    // 最小和merge
    public static int mergeZuiXiaohe(int[] arrays, final int left, final int m, final int right){

        int[] temp = new int[right - left + 1];
        int index = 0;
        int l = left;
        int r = m + 1;
        int sum = 0;
        while (l <= m && r <= right){

            if (arrays[l] < arrays[r]){
                sum += arrays[l] * (right - r + 1);
                temp[index++] = arrays[l++];
                continue;
            }
            temp[index++] = arrays[r++];
        }
        while (l <= m){
            temp[index++] = arrays[l++];
        }
        while (r <= right){
            temp[index++] = arrays[r++];
        }
        for (int i = 0; i < temp.length; i++){
            arrays[i + left] = temp[i];
        }
        return sum;
    }

    // 归并逆序对
    public static int guiBingNiXuDui(int[] arrays, final int start, final int end){

        if (end - start <= 0) return 0;
        int m = start + ( (end - start) >> 1 );

        return guiBingNiXuDui(arrays, start, m) +
                guiBingNiXuDui(arrays, m + 1, end) +
                mergeNiXuDui(arrays, start, m, end);
    }

    // 归并逆序对合并
    public static int mergeNiXuDui(int[] arrays, final int left, final int m, final int right){

        int[] temp = new int[right - left + 1];
        int l = left;
        int r = m + 1;
        int sum = 0;
        int index =  0;
        while (l <= m && r <= right){

            if (arrays[r] < arrays[l]){
                //
                sum += m - l + 1;
                temp[index++] = arrays[r++];
                continue;
            }
            temp[index++] = arrays[l++];
        }
        while (l <= m){
            temp[index++] = arrays[l++];
        }
        while (r <= right){
            temp[index++] = arrays[r++];
        }
        for (int i = 0; i < temp.length; i++){

            arrays[i + left] = temp[i];
        }
        return sum;
    }


//    public static void merge(int[] arrays, int left, int m, int right){
//
//        int[] temp = new int[right - left + 1];
//        int r = m + 1;
//        int l = left;
//        int index = 0;
//        while (l <= m && r <= right){
//
//            temp[index++] = arrays[l] <= arrays[r] ? arrays[l++] : arrays[r++];
//        }
//        while (l <= m){
//            temp[index++] = arrays[l++];
//        }
//        while (r <= right){
//
//            temp[index++] = arrays[r++];
//        }
//        index = 0;
//        for (int i = left; i <= right; i++){
//            arrays[i] = temp[index++];
//        }
//    }


    // todo 主定理 T(n) = aT(n/b) + O(n^d). 只对子问题规模一致的问题使用。
    // 分为小于的一组，等于的一组，大于的一组。小于大于两头逼近，知道遍历的i 等于大于这组的头。此时确定了这三组。
    // 等于这一组的最终位置已经确定。剩下的就是小于、大于这两组组内重复同样的操作即可。
    public static void quickOrder(int[] arrays, final int start, final int end){

        if (end - start <= 0) return;
        //随机快排。非随机的情况下，O(n)为n^2. 随机情况下(n^2出现的情况是一个概率)，用数学方式计算出时间复杂度为 nlogn
        int data = arrays[start + (int)(Math.random() *  (end - start))];
        int tailIndex = start;
        int headIndex = end;
        for (int i = start; i <= headIndex; ){

            if (arrays[i] == data) {
                i++;
                continue;
            }
            if (arrays[i] < data){
                //交换
                int tailData = arrays[tailIndex];
                arrays[tailIndex++] = arrays[i];
                arrays[i++] = tailData;
                continue;
            }
            int headData = arrays[headIndex];
            arrays[headIndex--] = arrays[i];
            arrays[i] = headData;
        }
        quickOrder(arrays, start, tailIndex - 1);
        quickOrder(arrays, headIndex + 1, end);
    }

    private void cqs(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //选定一个基准(此处选定最后一个值) 通过这个基准进行操作分为两部分 使其中一部分小于另一部分 然后对每一部分进行递归操作； 最后合并所有分治项
        int middleIndex = cpartition(arr, left, right, right);
        cqs(arr, left, middleIndex - 1);
        cqs(arr, middleIndex + 1, right);
    }

    /**
     *
     * @param arr
     * @param left 索引
     * @param right 索引
     * @param pivot 选定的基准值的索引
     * @return
     * todo 验证是否正确
     */
    private int cpartition(int[] arr, int left, int right, int pivot) {
        int pivotVal = arr[pivot];
        arr[pivot] = arr[right];
        arr[right] = pivotVal;

        int middleIndex = left; // 初始先选定最左值索引为中间位置，此时左边部分没有任何值
        for (int i = left; i <= right; i++) {
            if (arr[i] < pivotVal) {
                //交换值 是左边部分全部小于右边部门
                if (i != middleIndex) { // 该if语句只是一个微小的优化操作
                    int oldMiddleVal = arr[middleIndex];
                    arr[middleIndex] = arr[i];
                    arr[i] = oldMiddleVal;
                }
                middleIndex++; //更新中间位置
            }
        }
        return middleIndex;
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
