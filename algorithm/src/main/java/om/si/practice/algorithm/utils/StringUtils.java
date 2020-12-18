package om.si.practice.algorithm.utils;


/**
 * @author: HongZhenSi
 * @date: 2020/12/18
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class StringUtils {


    public static String generateRandomStr(int maxSize){

        int size = NumberUtil.randomData(maxSize);
        while(size == 0){
            size = NumberUtil.randomData(maxSize);
        }
        char[] words = new char[size];
        for(int i = 0; i < size; i++){

            words[i] = (char)NumberUtil.randomData('a', 'z');
        }
        return new String(words);
    }

    public static String[] generateRandomStr(int length, int maxSize){

        String[] strings = new String[length];
        for(int i = 0; i < length; i++){

            strings[i] = generateRandomStr(maxSize);
        }
        return strings;
    }
}
