package om.si.practice.algorithm.lists;

import lombok.extern.slf4j.Slf4j;
import om.si.practice.algorithm.common.MyNode;
import om.si.practice.algorithm.common.MyOneDirentNode;

import java.util.BitSet;

/**
 * @author: HongZhenSi
 * @date: 2020/11/29
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class NodeDeal {


    public static void main(String[] args) {

        MyOneDirentNode<Integer> oneDirentNode = generateMyOneDirentNode(5);
        log.info("MyOneDirentNode:{}", oneDirentNode);
        oneDirentNode = oneDirentNode.delete(0);
        log.info("MyOneDirentNode:{}", oneDirentNode);
//        //oneDirentNode 需要重新赋值，因为参数传的是引用
//        oneDirentNode = revertNodeDeal(oneDirentNode);
//        log.info("MyOneDirentNode:{}", oneDirentNode);
//
//        MyNode<Integer> myNode = generateMyNode(1);
//        log.info("myNode:{}", myNode);
//        myNode = revertNodeDeal(myNode);
//        log.info("revertNodeDeal myNode:{}", myNode);
//        myNode = myNode.deleteSpecifyDataNode(0);
//        log.info(" delete myNode:{}", myNode);
//
//        containChars("How do you do? 你好呀");

    }

    public static MyNode<Integer> revertNodeDeal(MyNode<Integer> head){

        if(head == null || head.getNextNode() == null) return head;
        MyNode<Integer> cur = head;
        MyNode<Integer> tail = new MyNode<Integer>();
        while(cur != null){

            MyNode<Integer> next = cur.getNextNode();
            MyNode<Integer> pre = cur.getPreNode();
            cur.setPreNode(next);
            cur.setNextNode(pre);
            tail = cur;
            cur = next;

        }
        return tail;
    }



    private static MyNode<Integer> generateMyNode(int size){

        MyNode<Integer> headNode = new MyNode<>();
        headNode.setData(0);
        headNode.setPreNode(null);
        MyNode<Integer> trailNode = headNode;
        for (int i = 1; i < size; i++){

            MyNode<Integer> tempNode = new MyNode<>();
            tempNode.setData(i);
            tempNode.setPreNode(trailNode);
            trailNode.setNextNode(tempNode);
            trailNode = tempNode;

        }
        return headNode;
    }


    private static MyOneDirentNode<Integer> generateMyOneDirentNode(int size){

        MyOneDirentNode<Integer> oneDirentNode = new MyOneDirentNode<>();
        oneDirentNode.setData(0);
        MyOneDirentNode<Integer> tailNode = oneDirentNode;
        for (int i = 1; i < size; i++){

            MyOneDirentNode<Integer> node = new MyOneDirentNode<>();
            node.setData(0);
            tailNode.setNextNode(node);
            tailNode = tailNode.getNextNode();
        }
        return oneDirentNode;
    }

    public static MyOneDirentNode<Integer> revertNodeDeal(MyOneDirentNode<Integer> node){

       if(node == null || node.getNextNode() == null) return node;
        MyOneDirentNode<Integer> pre = null;
        MyOneDirentNode<Integer> cur = node;
        while(cur != null){

            MyOneDirentNode<Integer> next = cur.getNextNode();
            cur.setNextNode(pre);
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void containChars(String str) {
        BitSet used = new BitSet();
        for (int i = 0; i < str.length(); i++)
            used.set(str.charAt(i)); // set bit for char

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = used.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            if (used.get(i)) {
                sb.append((char) i);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
