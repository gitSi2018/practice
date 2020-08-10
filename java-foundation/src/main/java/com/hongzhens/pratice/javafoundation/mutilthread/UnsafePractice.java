package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
//import sun.misc.Unsafe;

@Slf4j
public class UnsafePractice {

    private static Driver driver = new Driver(1, 1, 1, "zhang1");

    private static final long ageOffset;

    private static final long levelOffset;

    private static final long salaryOffset;

    private static final long nameOffset;

    private static final sun.misc.Unsafe unsafe;

    static {
        try {
            // 通过反射实例化Unsafe
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            Class<?> dClass = Driver.class;
            ageOffset = unsafe.objectFieldOffset(dClass.getDeclaredField("age"));
            levelOffset = unsafe.objectFieldOffset(dClass.getDeclaredField("level"));
            salaryOffset = unsafe.objectFieldOffset(dClass.getDeclaredField("salary"));
            nameOffset = unsafe.objectFieldOffset(dClass.getDeclaredField("name"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws Throwable{

//        log.info("");
        setDriverAge(driver, 10, ageOffset);
//        initDriver();

    }



    public static boolean setDriverAge(Driver driver, int age, long ageOffset){

        log.info("before setDriverAge driver:\n{}", driver);
        boolean update = unsafe.compareAndSwapInt(driver, ageOffset, driver.getAge(), age);
        log.info("update:\n{}, after setDriverAge driver:\n{}", update, driver);
        return update;
    }

    public static void initDriver() throws InstantiationException {

        Driver temp = (Driver)unsafe.allocateInstance(Driver.class);
        log.info("initDriver driver:{}", temp);
    }


}

@Slf4j
@Getter
@ToString
class Driver{

    private int age;

    private Integer level;

    private double salary;

    private String name;

    public Driver(){
        log.info("Driver()");
    }

    public Driver(int age, Integer level, double salary, String name){
        log.info("Driver(...)");
        this.age = age;
        this.level = level;
        this.salary = salary;
        this.name = name;
    }
}
