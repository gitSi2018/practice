package om.si.practice.algorithm.tree;

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
        for(int i = 0; i < words.length; i++){

            int road = words[i] - 'a';
            curNode = curNode.children[road];
            if(curNode == null) return 0;
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
        for(int i = 0; i < words.length; i++){

            int road = words[i] - 'a';
            if(curNode.children[road] == null){
                curNode.children[road] = new Node();
            }
            curNode = curNode.children[road];
            curNode.pass++;
        }
        curNode.end++;
    }


    public static void main(String... args){

        test();

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
