package om.si.practice.algorithm.items.linktype;

import lombok.extern.slf4j.Slf4j;
import om.si.practice.algorithm.base.ListNode;

import java.util.List;

@Slf4j
public class AddTwoNumber {


    public static void main(String[] args) {
        ListNode firstNode = init(8);
        ListNode secondNode = init(9);
        ListNode targetNode = addTwoNumbers(firstNode, secondNode);
        log.info("\ntargetNode:{}", targetNode);
        log.info("\nresult:{}", 87654321 + 987654321);
    }

    public static ListNode init(int size){

        ListNode node = new ListNode(1);
        ListNode nextNode = node;
        for (int i = 1 + 1; i <= size; i++){
            ListNode tempNode = new ListNode(i);
            nextNode.next = tempNode;
            nextNode = nextNode.next;
        }
//        log.info("\nnode:{}", node);
//        log.info("\nnextNode:{}", nextNode);
        return node;
    }

    public static ListNode addTwoNumbers(ListNode first, ListNode second){

        ListNode targetNode = new ListNode(0);
        ListNode nextNode = targetNode;
        int[] firstVals = convert(first);
        int[] secondVals = convert(second);
        int firstSize = firstVals.length;
        int secondSize =  secondVals.length;
        boolean firstBigger = firstSize - secondSize >= 0;
        int[] smaller = firstBigger ? secondVals : firstVals;
        int[] bigger = firstBigger ? firstVals : secondVals;
//        log.info("\nsmaller:{}, bigger:{}", smaller, bigger);
        int additionalNextDigit = 0;
        for (int i = 0; i < bigger.length; i++){
            int smallerVal = 0;
            int biggerVal = bigger[i];
            if (i < smaller.length){
                smallerVal = smaller[i];
            }
            int total = smallerVal + biggerVal + additionalNextDigit;
            //进位
            additionalNextDigit = total / 10;
//            log.info("i:{}, smallerVal:{}, biggerVal:{}, additionalNextDigit:{}, total:{}", i, smallerVal, biggerVal, additionalNextDigit,total);
            //改位上的计算结果
            ListNode newNode = new ListNode(total % 10);
            //链入新节点
            nextNode.next = newNode;
            //指向下一个节点
            nextNode = nextNode.next;
            if (i == bigger.length - 1 && additionalNextDigit > 0){
                //最后一位如果还有进位，则还需要再加一个节点
                ListNode lastNode = new ListNode(additionalNextDigit);
                nextNode.next = lastNode;
            }

        }
        return targetNode.next;
    }

    private static int[] convert(ListNode node){

        int size = 0;
        ListNode nextNode = node;
        while (true){
            if (nextNode == null){
                break;
            }
            size++;
            nextNode = nextNode.next;
        }
        int[] target = new int[size];
        int index = 0;
        nextNode = node;
//        log.info("\nnode:{}, \nnextNode:{}", node, nextNode);
        while (true){
            if (nextNode == null){
                break;
            }
            target[index] = nextNode.val;
            nextNode = nextNode.next;
            index++;
        }
//        log.info("\ntarget array:{}", target);
        return target;
    }

    private static int getSize(ListNode node){
        int size = 0;
        ListNode firstNode = new ListNode(node.val);
        firstNode.next = node.next;
        log.info("firstNode:{}", firstNode);
        while (true){
            if (node == null){
                break;
            }
            size++;
            node = node.next;
        }
        log.info("firstNode:{}", firstNode);
        return size;
    }
}
