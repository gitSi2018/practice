package com.hongzhens.pratice.javafoundation.bitoperate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitMove {


    // 一个int型的整数 表示两个数据，低16位表示基本薪资，高十六位表示奖金
    public static int getSalary(int a, byte whichOne){

        int temp = whichOne == 1 ? a : a >> 16;
        return temp & 0xFFFF;
    }

    //扩大两倍之后取单数
    public static int getDanShu(int a){

        return a << 1 | 1;
    }

    public static void main(String[] args) {

        int a = Integer.valueOf("00010000000000000111", 2);
        log.info("low:{}", getSalary(a, (byte) 1));
        log.info("high:{}", getSalary(a, (byte) 2));
        log.info("        \na:                        {}\n0xFFFF0000:{}",
                Integer.toBinaryString(a),
                Integer.toBinaryString(0xFFFF0000));
        log.info("qudan:{}",getDanShu(1));
        int b = 0;
        b += 0x9e3779b9;
        log.info("0x9e3779b9:{}, 0x9e3779b9:{}, 0x9e3779b9:{}", b, b + 0x9e3779b9, 2 * 0x9e3779b9);
        log.info("0x9e3779b9:{}", Integer.toBinaryString(0x9e3779b9));
        byte c = (byte)( 1 << 7);
        byte d = (byte) (1 << 6);
        log.info("c:{}, c>>:{},d:{}, c>>>:{}", c, c >> 1,
                d,   c >>> 1);
        log.info("\n   c:{}, \nc >>:{},d:{}, \nc>>>:{}", Integer.toBinaryString(c), Integer.toBinaryString(c >> 1),
                Integer.toBinaryString(d),   Integer.toBinaryString(c >>> 1));
    }
}
