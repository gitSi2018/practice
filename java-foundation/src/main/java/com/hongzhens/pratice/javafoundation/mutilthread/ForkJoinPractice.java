package com.hongzhens.pratice.javafoundation.mutilthread;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForkJoinPractice {

    // Active counts
    private static final int  AC_SHIFT   = 48;
    private static final long AC_UNIT    = 0x0001L << AC_SHIFT;
    private static final long AC_MASK    = 0xffffL << AC_SHIFT;

    // Total counts
    private static final int  TC_SHIFT   = 32;
    private static final long TC_UNIT    = 0x0001L << TC_SHIFT;
    private static final long TC_MASK    = 0xffffL << TC_SHIFT;
    public static long move(long toMove){

        toMove = -toMove;
        long moved = ((toMove << AC_SHIFT) & AC_MASK) | ((toMove << TC_SHIFT) & TC_MASK);
        return moved;
    }

    public static void main(String[] args) {

        log.info("move 1 moved:{}", move(1));
    }
}
