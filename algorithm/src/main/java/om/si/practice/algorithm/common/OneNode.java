package om.si.practice.algorithm.common;

import om.si.practice.algorithm.utils.NumberUtil;

/**
 * @author: HongZhenSi
 * @date: 2020/12/21
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class OneNode {

    private int val;

    private OneNode next;


    public OneNode(int val){
        this.val = val;
    }

    /**
     *  快慢指针 找最中间。偶数时取中间的第一个
     * @return
     */
    public OneNode middleUp(){

        if(this.next == null || this.next.next == null) return this;
        OneNode slow = this.next;
        OneNode quick = this.next.next;
        while(quick.next != null && quick.next.next != null){

            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public OneNode middleUpCom(){

        if(this.next == null || this.next.next == null) return this;
        int count = 0;
        OneNode cur = this;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        cur = this;
        int target = count % 2 == 0 ? count / 2 : count / 2 + 1;
       for(int i  = 1; i < target; i++){
           cur = cur.next;
       }
        return cur;
    }

    /**
     *  快慢指针 找最中间。偶数时取中间的第二个
     * @return
     */
    public OneNode middleDown(){

        if(this.next == null) return this;
        if(this.next.next == null) return this.next;
        OneNode slow = this.next;
        OneNode quick = this.next.next;
        int count = 3;
        while(quick.next != null){
            count++;
            if(quick.next.next == null){
                break;
            }
            count++;
            quick = quick.next.next;
            slow = slow.next;
        }
        if(count % 2 == 1) return slow;
        return slow.next;
    }

    public OneNode middleDownCom(){

        if(this.next == null) return this;
        int count = 0;
        OneNode cur = this;
        while(cur != null){
            count++;
            cur = cur.next;
        }
        int target = count / 2 + 1;
        cur = this;
        for(int i = 1; i < target; i++){
            cur = cur.next;
        }
        return cur;
    }

    public OneNode middleUpPre(){

        return null;
    }

    public OneNode middleDownNext(){

        return null;
    }

    public String toString(){

        OneNode cur = this;
        StringBuilder b = new StringBuilder();
        while(cur != null){
            b.append(":").append(cur.val);
            cur = cur.next;
        }
        return b.toString();
    }

    public static OneNode init(int maxSize, int maxNum){

        int size = NumberUtil.randomData(maxSize);
        size = Math.max(1, size);
        OneNode head = new OneNode(NumberUtil.randomData(maxNum));
        OneNode cur = head;
        for(int i = 0; i < size; i++){

            OneNode node = new OneNode(NumberUtil.randomData(maxNum));
            cur.next = node;
            cur = cur.next;
        }
//        System.out.println("node:" + head);
        return head;
    }

    public static void upDuiShuQi1(int loop){

        for(int i = 0; i < loop; i++) {
            OneNode node = init(15, 20);
            OneNode up1 = node.middleUp();
            OneNode comUp1 = node.middleUpCom();
//            System.out.println("up1:"  + up1 +  "  comUp1" + comUp1);
            if(!up1.equals(comUp1)){
                System.out.println("not right. up1:"  + up1 +  "  comUp1:" + comUp1 +  " node:" + node);
            }
        }

    }

    public static void downDuiShuQi1(int loop){

        for(int i = 0; i < loop; i++) {
            OneNode node = init(15, 20);
            OneNode down1 = node.middleDown();
            OneNode comDown1 = node.middleDownCom();
//            System.out.println("down1:"  + down1 +  "  comDown1" + comDown1);
            if(!down1.equals(comDown1)){
                System.out.println("not right. down1:"  + down1 +  "  comDown1:" + comDown1 +  " node:" + node);
            }
        }

    }

    public static void main(String... args){


//        upDuiShuQi1(10000);
        downDuiShuQi1(10000);
    }
}
