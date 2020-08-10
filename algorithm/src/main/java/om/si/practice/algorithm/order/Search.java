package om.si.practice.algorithm.order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Search {


    public static void main(String[] args) {

        int[] sortedTest = {2, 3, 5, 4, 6, 2, 9, 13,32,2,56, 55};
//        int[] test = {2, 1, 5, 4, 6};
//        int[] sortedTest = DivideAndConquer.divideAndConquerSort(test, true);
        log.info("maxIncome:{}", maxIncome(sortedTest));
        log.info("sortedTest:{}", sortedTest);
        log.info("search 0:{}", binarySearch(sortedTest, 0));
        log.info("search 10:{}", binarySearch(sortedTest, 10));
        log.info("search 2:{}", binarySearch(sortedTest, 2));
        log.info("search 100:{}", binarySearch(sortedTest, 100));

        log.info("searchIndex 0:{}", binaryInsertIndex(sortedTest, 0, false));
        log.info("searchIndex 10:{}", binaryInsertIndex(sortedTest, 10, false));
        log.info("searchIndex 2:{}", binaryInsertIndex(sortedTest, 2, false));
        log.info("searchIndex 100:{}", binaryInsertIndex(sortedTest, 11, false));
    }

    public static int binarySearch(int[] source, int target){

        if (source.length == 1) return source[0] == target ? 0 : -1;

        int indexStart = 0;
        int indexEnd = source.length - 1;
        int middle = (indexStart + indexEnd) >> 1;
        while (true){
            if (middle < 0 || middle >= source.length) return -1;
            int compareMiddle = source[middle] - target;
            if (compareMiddle == 0) return middle;
            if (indexStart >= indexEnd) return -1;
            if (compareMiddle > 0){
                indexEnd = middle - 1;
                middle = (indexStart + indexEnd) >> 1;
                continue;
            }

            indexStart = middle + 1;
            middle = (indexStart + indexEnd) >> 1;

        }
    }

    public static int binaryInsertIndex(int[] source, int index, boolean asc){

        if (index == 0){
            return 0;
        }
        int startIndex = 0;
        int endIndex = index - 1;
        int middle = (startIndex + endIndex) >> 1;
        while (true){

            //
            if (middle < 0) return 0;
            if ( middle >= index) return index;
            if (source[index] == source[middle]) return middle;
            if (source[index] > source[middle]){
                if (source[index] <= source[middle + 1]){
                    return middle + 1;
                }
                // 往右边找
                startIndex = middle + 1;
                middle = (startIndex + endIndex) >> 1;
                continue;
            }
            if (middle == 0 || source[index] >= source[middle - 1]){
                return middle;
            }
            endIndex = middle - 1;
            middle = (startIndex + endIndex) >> 1;
        }
    }


//    public static double maxIncome(int[] prices){
//        if (prices == null) return 0;
//        if (prices.length == 1) return prices[0];
//
//        double[] quoteChange = new double[prices.length];
//        quoteChange[0] = 0;
//        for (int i = 1; i < prices.length; i++){
//            quoteChange[i] = prices[i] - prices[i -1];
//        }
//        // 计算最大收益
//        double maxIncome = 0.0;
//        double tempIncome = 0.0;
//        int startIndex = 0;
//        int tempStartIndex = 0;
//        int endIndex = 0;
//        int tempEndIndex = 0;
//        for (int i = 0; i < quoteChange.length; i++){
//
//            if (i == quoteChange.length - 1 && quoteChange[i] >= 0) {
//                tempIncome += quoteChange[i];
//                tempEndIndex = i;
//                if (maxIncome < tempIncome){
//                    maxIncome = tempIncome;
//                    startIndex = tempStartIndex;
//                    endIndex = tempEndIndex;
//                }
//            }
//            if (quoteChange[i] < 0) {
//                if (maxIncome < tempIncome){
//                    maxIncome = tempIncome;
//                    startIndex = tempStartIndex;
//                    endIndex = tempEndIndex;
//                }
//                tempIncome = 0;
//                tempEndIndex = i;
//                tempStartIndex = i;
//                continue;
//            }
//            tempIncome += quoteChange[i];
//            tempEndIndex = i;
//        }
//        log.info("startIndex:{}, endIndex:{}", startIndex, endIndex);
//        return maxIncome;
//    }

    public static double maxIncome(int[] prices){
        if (prices == null) return 0;
        if (prices.length == 1) return prices[0];

        double[] quoteChange = new double[prices.length];
        quoteChange[0] = prices[0];
        for (int i = 1; i < prices.length; i++){
            quoteChange[i] = prices[i];
        }
        // 计算最大收益
        int startIndex = 0;
        int tempStartIndex = 0;
        int endIndex = 0;
        int tempEndIndex = 0;

        double maxIncome = 0.0;
        double minPrice = quoteChange[0];
        for (int i = 1; i < quoteChange.length; i++){
            double currentPrice = quoteChange[i];
            double tempIncome = currentPrice - minPrice;
            if(tempIncome > maxIncome) {
                maxIncome = tempIncome;
                endIndex = i;
            }
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
                startIndex = i;
            }
        }
        log.info("startIndex:{}, endIndex:{}", startIndex, endIndex);
        return maxIncome;
    }
}
