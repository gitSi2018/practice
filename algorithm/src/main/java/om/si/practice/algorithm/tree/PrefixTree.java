package om.si.practice.algorithm.tree;

import om.si.practice.algorithm.base.ListNode;
import om.si.practice.algorithm.utils.StringUtils;

/**
 * @author: HongZhenSi
 * @date: 2020/12/18
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class PrefixTree {


    private Node root;

    private static class Node{

        private int pass;

        private int end;

        private Node[] children = new Node[26];
    }

    public PrefixTree(){

    }

    public PrefixTree(String[] strings){

        if(strings == null || strings.length == 0){
            return;
        }
        // 初始化 前缀树
        for(String str : strings){
            addWord(str);
        }
    }

    public int wordCount(String word){

        if(root == null) return 0;
        //查询完全匹配的总数
        Node curNode = root;
        char[] words = word.toCharArray();
        for (char c : words) {

            int road = c - 'a';
            curNode = curNode.children[road];
            if (curNode == null) return 0;
        }
        return curNode.end;
    }

    public int prefixCount(String prefix){
        if(root == null || prefix.equals("")) return 0;
        // 查询匹配的总数
        Node curNode = root;
        char[] words = prefix.toCharArray();
        for(int i = 0; i < words.length; i++){

            int road = words[i] - 'a';
            curNode = curNode.children[road];
            if(curNode == null) return 0;
        }
        return curNode.pass;
    }

    public void addWord(String word){

        if(root == null){
            // 构造头结点
            root = new Node();
        }
        // 处理
        char[] words = word.toCharArray();
        Node curNode = root;
        root.pass++;
        for (char c : words) {

            int road = c - 'a';
            if (curNode.children[road] == null) {
                curNode.children[road] = new Node();
            }
            curNode = curNode.children[road];
            curNode.pass++;
        }
        curNode.end++;
    }



    private static String[] strings;

    public static int commonWordCount(String word){

        int count = 0;
        for(String str : strings){

            count += str.equals(word) ? 1 : 0;
        }
        return count;
    }

    public static int commonPrefixCount(String word){

        int count = 0;
        for(String str : strings){

            count += str.startsWith(word) ? 1 : 0;
        }
        return count;
    }

    public static void duiShuQi(){

        int[] a = new int[]{1, 3};
        String[] initStrings = StringUtils.generateRandomStr(1000, 26);
        PrefixTree tree = new PrefixTree(initStrings);
        strings = initStrings;

        String[] checkStrings = StringUtils.generateRandomStr(100000, 26);;
        for(String str : checkStrings){

            int prefix1 = tree.prefixCount(str);
            int prefix2 = commonPrefixCount(str);

            int count1 = tree.wordCount(str);
            int count2 = tree.wordCount(str);

            if(prefix1 != prefix2){

                System.out.println("prefix1 <> prefix2. prefix1:" + prefix1 + " prefix2:" + prefix2);
            }
            if(count1 != count2){

                System.out.println("count1 <> count2. count1:" + count1 + " count2:" + prefix2);
            }
        }

    }


    public static void main(String... args){

//        test();
        duiShuQi();
    }

    public static void test(){

        String[] strings = StringUtils.generateRandomStr(10, 5);
        PrefixTree tree = new PrefixTree(strings);

        for(String str : strings){

            String temp = str.substring(0, str.length() - 1);
            int strWord = tree.wordCount(str);
            int tempWord = tree.wordCount(temp);

            int strPrefix = tree.prefixCount(str);
            int temPrefix = tree.prefixCount(temp);

            System.out.print("str:" + str + "  strWord:" + strWord + "  strPrefix:" +  strPrefix +
                    " temp:" + temp + "  tempWord:" + tempWord + " temPrefix:" + temPrefix + "\n");
        }
    }


}
