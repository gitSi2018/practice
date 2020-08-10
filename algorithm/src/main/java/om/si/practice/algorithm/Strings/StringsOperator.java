package om.si.practice.algorithm.Strings;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class StringsOperator {


    public static void main(String[] args) {

//        String sourceStr = "abcabcbb";
//        String sourceStr = "au";
//        String sourceStr = "bbb";
//        String sourceStr = " ";
//        String sourceStr = "dvdf";
        String sourceStr = "abcdd";

        int length = lengthOfLongestSubstring(sourceStr);
        log.info("\nlength:{}", length);
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

}
