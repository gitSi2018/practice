package om.si.practice.algorithm.order;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;

@Slf4j
public class InsertOrder {


    public static void main(String[] args) {
        int[] ages = {400, 5, 3, 6, 12,87,2,1,56,23,9,1,7,8};

        log.info("after order:{}", insertOrder(ages, false));
    }

    /**
     *  使用的是循环不变式增量的进行插入。类似于数学的归纳法（循环次数可无限）。但是注意，这里的实际循环次数是要有限的。
     *   1. 找出循环不变式（todo 有的问题并不需要转换为循环问题，比如生成一个伪随机数。循环问题中，所有问题的都有循环不变式？）
     *   2. 每个循环不变式中需要做哪些步骤，不能漏掉（这一步非常重要，处理不好就是bug）todo 集中考虑这一步要做什么，会有哪些case，每个case小心处理
     *   3. 循环累加之后的输出是正确的
     * @param source
     * @param asc
     * @return
     */
    public static int[] insertOrder(int[] source, boolean asc){

        if (source == null || source.length <= 1){
            return source;
        }
        for (int i = 1; i < source.length; i++){

            int thisElement = source[i];
            int insertIndex = i;
            //确定插入位置
//            for (int j = i - 1; j >= 0; j--){
//                if (asc && thisElement >= source[j]){
//                    insertIndex = j + 1;
//                    break;
//                }else if (!asc && thisElement <= source[j]){
//                    insertIndex = j + 1;
//                    break;
//                }
//                // todo 一开始的实现中，漏掉了下面这一步。导致在某些情况下排序有问题。千万不能漏掉case，先列举出case
//                if (j == 0) insertIndex = 0;
//            }
            insertIndex = Search.binaryInsertIndex(source, insertIndex, true);

            //移位
            for (int j = i - 1;j >= insertIndex; j--){
                source[j + 1] = source[j];
            }
            //执行插入
            source[insertIndex] = thisElement;
        }
        return source;
    }


}
