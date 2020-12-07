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

//        MyOneDirentNode oneDirentNode = generateMyOneDirentNode(10);
//
//        log.info("MyOneDirentNode:{}", oneDirentNode);
//        //oneDirentNode 需要重新赋值，因为参数传的是引用
//        oneDirentNode = revertNodeDeal(oneDirentNode);
//        log.info("MyOneDirentNode:{}", oneDirentNode);
//
//        MyNode myNode = generateMyNode(20);
//        log.info("myNode:{}", myNode);
//        myNode = revertNodeDeal(myNode);
//        log.info("myNode:{}", myNode);
//        myNode = myNode.deleteSpecifyDataNode(1);

        containChars("How do you do? 你好呀");

    }

    public static MyNode revertNodeDeal(MyNode head){

        if (head ==  null || head.getNextNode() == null) return head;
        MyNode newHeadNode = head;
        MyNode dealNode = head.getNextNode();
        newHeadNode.setNextNode(null);
//        tailNode.setPreNode(dealNode);
        while (dealNode != null){

            MyNode nextNode = dealNode.getNextNode();
            dealNode.setPreNode(null);

            dealNode.setNextNode(newHeadNode);
            newHeadNode.setPreNode(dealNode);

            newHeadNode = dealNode;
            dealNode = nextNode;
        }
        return newHeadNode;
    }



    private static MyNode generateMyNode(int size){

        MyNode headNode = new MyNode();
        headNode.setData(0);
        headNode.setPreNode(null);
        MyNode trailNode = headNode;
        for (int i = 1; i < size; i++){

            MyNode tempNode = new MyNode();
            tempNode.setData(i);
            tempNode.setPreNode(trailNode);
            trailNode.setNextNode(tempNode);
            trailNode = tempNode;

        }
        return headNode;
    }


    private static MyOneDirentNode generateMyOneDirentNode(int size){

        MyOneDirentNode oneDirentNode = new MyOneDirentNode();
        oneDirentNode.setData(0);
        MyOneDirentNode tailNode = oneDirentNode;
        for (int i = 1; i < size; i++){

            MyOneDirentNode node = new MyOneDirentNode();
            node.setData(i);
            tailNode.setNextNode(node);
            tailNode = tailNode.getNextNode();
        }
        return oneDirentNode;
    }

    public static MyOneDirentNode revertNodeDeal(MyOneDirentNode node){

        if (node == null || node.getNextNode() == null) return node;
        MyOneDirentNode tailNode = node;
        node = node.getNextNode();
        tailNode.setNextNode(null);
        while (node != null){

            MyOneDirentNode nextNode = node.getNextNode();
            node.setNextNode(tailNode);
            tailNode = node;
            node = nextNode;
        }
        return tailNode;
//        while (node != null){
//
////            log.info("data:{}", node.getData());
//            node = node.getNextNode();
//        }
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
