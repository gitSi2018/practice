package om.si.practice.algorithm.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单的计算时间和内存方法
 */
@Slf4j
public class RunTimeUtil {

    private static long startTime , startMem , endMem , endTime;

    public static void init(){

        Runtime r = Runtime.getRuntime();
        r.gc();//计算内存前先垃圾回收一次
        startTime = System.currentTimeMillis();//开始Time
        startMem = r.freeMemory(); // 开始Memory

    }

    public static void end(){

        Runtime r = Runtime.getRuntime();
        endMem = r.freeMemory(); // 末尾Memory
        endTime = System.currentTimeMillis();//末尾Time
        //输出
        System.out.println("TimeCost: "+ String.valueOf(endTime - startTime)+"ms");
        System.out.println("MemoryCost: "+ String.valueOf((startMem- endMem)/1024)+"KB");

    }


}
