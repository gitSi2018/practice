package om.si.practice.algorithm.items.linktype;

import om.si.practice.algorithm.base.ListNode;
import om.si.practice.algorithm.utils.RunTimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static om.si.practice.algorithm.base.ListNode.buildListNode;


public class LinkTest {

    @Before
    public void before(){
        System.out.println("===Start===");
//        RunTimeUtil.init();
    }

    @After
    public void after(){
//        RunTimeUtil.end();
        System.out.println("===End===");
    }

    /**
     * Leetcode
     * ==================================
     */

    /**
     * 找出两个链表的交点
     * 160. Intersection of Two Linked Lists (Easy)
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
     */
    @Test
    public void IntersectionLinkedListstest(){


//        ListNode headA = buildListNode(new int[]{1,2,3,4});
//        ListNode headB = buildListNode(new int[]{4,3,2,3,4});

        ListNode head = buildListNode(new int[]{2,3,4});

        ListNode headA = new ListNode(1);
        headA.next = head;
        ListNode headB = buildListNode(new int[]{3,6});
        headB.next = head;

        ListNode r = getIntersectionNode(headA,headB);

//        System.out.println(r.val);

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    /**
     * 链表反转
     * 206. Reverse Linked List (Easy)
     * https://leetcode-cn.com/problems/reverse-linked-list/description/
     */
    @Test
    public void reverseLinkedListTest(){

        ListNode head = buildListNode(new int[]{1,2,3,4,5});
        ListNode r = reverseList(head);
        System.out.println(r);

    }
    private ListNode reverseList(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverseList(next);

        next.next = head;
        head.next = null;

        return newHead;

    }
    private ListNode reverseListIterative(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            curr = nextTemp; //当前指针后移
        }
        return prev;
    }

    /**
     * 归并两个有序的链表
     * 21. Merge Two Sorted Lists (Easy)
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    @Test
    public void mergeTwoSortedListsTest(){

        ListNode l1 = buildListNode(new int[]{1,3,5,6});
        ListNode l2 = buildListNode(new int[]{2,3,4,5,7});
        ListNode r = mergeTwoLists(l1,l2);


    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


    /**
     *  从有序链表中删除重复节点
     *  83. Remove Duplicates from Sorted List (Easy)
     *  https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
     *  输入: 1->1->2
     *  输出: 1->2
     */
    @Test
    public void removeDuplicatesFromSortedListTest(){

        ListNode head = buildListNode(new int[]{1,2,2,3,3,3,5,8});

        ListNode r = deleteDuplicates(head);

    }
    private ListNode deleteDuplicates(ListNode head){
        if (head == null || head.next == null) return head;

        head.next = deleteDuplicates(head.next);

        return head.val == head.next.val ? head.next : head;
    }


    /**
     * 删除链表的倒数第 n 个节点
     * 19. Remove Nth Node From End of List (Medium)
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    @Test
    public void removeNthNodeFromEndofListTest(){

        ListNode head = buildListNode(new int[]{1,2,3,4,5,6});
        int n = 2;

        //递归的 retrun 计数法
        ListNode r = removeNthFromEnd(head , n) == n ? head.next : head;

        //双指针
        ListNode r1 = removeNthFromEnd1(head , n);


    }
    //递归的 retrun 计数法
    private int removeNthFromEnd(ListNode head , int n){

        if (head == null|| head.next == null) return 1;

        int m =  removeNthFromEnd(head.next , n);

        if (m == n){

            if (m == 1){
                head.next = null;
            }else {
                head.next = head.next.next;
            }

        }
        return m+1;

    }
    /**
     * 双指针 快慢指针
     * 快指针 先走 n 步，当快指针走到头时的步数，走了len-n，即正向数第几个，再用慢指针走到并跳过
     * @param head
     * @param n
     * @return
     */
    private ListNode removeNthFromEnd1(ListNode head , int n){

        ListNode rec = new ListNode(0);
        rec.next = head;
        ListNode p = rec;
        ListNode q = head;

        for(int i=1;i<=n;i++){
            q = q.next;
        }

        while(q!=null){
            p = p.next;
            q = q.next;
        }

        p.next = p.next.next;
        return rec.next;
    }



    /**
     * 交换链表中的相邻结点
     * 24. Swap Nodes in Pairs (Medium)
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    @Test
    public void swapNodesinPairsTest(){
        ListNode r = swapPairs(buildListNode(new int[]{1,2,3,4}));
    }

    private ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }


    /**
     *  链表求和
     *  445. Add Two Numbers II (Medium)
     *  https://leetcode-cn.com/problems/add-two-numbers-ii/description/
     *  输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     *  输出：7 -> 8 -> 0 -> 7
     *
     *  todo 思路
     *  链表反转
     *  栈
     */
    @Test
    public void AddTwoNumbersIITest(){

    }

    /**
     * 回文链表
     * 234. Palindrome Linked List (Easy)
     * https://leetcode-cn.com/problems/palindrome-linked-list/description/
     * 输入: 1->2->2->1
     * 输出: true
     *
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */
    //根据快慢指针，找到链表的中点
    //链表反转
    @Test
    public void palindromeLinkedListTest(){
        Boolean flag = isPalindrome(buildListNode(new int[]{1,2,2,1}));
        System.out.println(flag);

    }
    private boolean isPalindrome(ListNode head) {
        // 要实现 O(n) 的时间复杂度和 O(1) 的空间复杂度，需要翻转后半部分
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 根据快慢指针，找到链表的中点
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while(slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head){
        // 递归到最后一个节点，返回新的新的头结点
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;

    }


    /**
     * 分隔链表
     * 725. Split Linked List in Parts(Medium)
     * https://leetcode-cn.com/problems/split-linked-list-in-parts/description/
     */
    @Test
    public void SplitLinkedListinPartsTest(){

    }

    /**
     * 链表元素按奇偶聚集
     * 328. Odd Even Linked List (Medium)
     * https://leetcode-cn.com/problems/odd-even-linked-list/description/
     */
    @Test
    public void OddEvenLinkedListTest(){

        ListNode r = oddEvenList(buildListNode(new int[]{1,2,3,4,5}));

    }
    private ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // head 为奇链表头结点，o 为奇链表尾节点
        ListNode o = head;
        // p 为偶链表头结点
        ListNode p = head.next;
        // e 为偶链表尾节点
        ListNode e = p;
        while (o.next != null && e.next != null) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }









}
