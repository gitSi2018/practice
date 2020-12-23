package om.si.practice.algorithm.base;

import lombok.ToString;

/**
 * om.si.practice.algorithm.utils.base - 链表结构
 */
@ToString
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){

    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode buildListNode(int[] input){
        ListNode first = null,last = null,newNode;
        int num;
        if(input.length>0){
            for(int i=0;i<input.length;i++){
                newNode=new ListNode(input[i]);
                newNode.next=null;
                if(first==null){
                    first=newNode;
                    last=newNode;
                }
                else{
                    last.next=newNode;
                    last=newNode;
                }

            }
        }
        return first;
    }

    public static void main(String... args){

        int a = Integer.MIN_VALUE;
        int b = 0 - a;
        int c =  ~a;
        int d = a / 10;
        int e = a % 10;
        ListNode head = new ListNode();
        if( (head = head.next).next == null ){

        }
    }

}