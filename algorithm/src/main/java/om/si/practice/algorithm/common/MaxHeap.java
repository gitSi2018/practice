package om.si.practice.algorithm.common;

import om.si.practice.algorithm.utils.NumberUtil;

import java.util.PriorityQueue;

/**
 *
 *   堆是一种完全二叉树，且要么是大堆，要么是小堆。
 *   使用堆进行排序，时间复杂度为O(n log n)，可以不用递归，空间复杂度为 O(1)
 *   构造一个堆：如果是一个一个的给数据进行堆的构造，则时间复杂度为O(n log n)
 *             如果所有数据已经给了，则时间复杂度可以为O(n).
 *   如果给定的数组差不多有序，最终数据排完序之后移动的位置不超过k，则可以利用堆排序(堆的大小可维持在小于等于k+1)高效的排序（时间复杂度可以到  O(n log k)）
 * @author: HongZhenSi
 * @date: 2020/12/17
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class MaxHeap<T extends Comparable<T>>
        extends MyCBAbstractTree<T>{

    public MaxHeap(int capacity){

        super(capacity);
    }


    public MaxHeap(int capacity, T[] data){

        super(capacity, data);
    }
    /**
     *  相对完全二叉树来说，插入node之后，需要处理siftUp(大堆时，大于父节点需要和父节点交换，直到不大于父节点或者到达root节点)
     * @param node
     */
    @Override
    public void addNode(T node) {

        int insIndex = super.getSize();
        super.addNode(node);
        while (biggerThanParent(insIndex)){
            super.swapWithParent(insIndex);
            insIndex = (insIndex - 1) / 2;
        }
    }

    @Override
    public boolean offerNode(T node) {

        if (!super.offerNode(node)){
           return false;
        }
        int size = super.getSize();
        while (biggerThanParent(size)){
            super.swapWithParent(size);
            size = (size - 1) / 2;
        }
        return false;
    }

    /**
     *  相对于完全二叉树来说，在移除根节点的时候，需要进行siftDown（比左右子节点最大值小时和对应的节点交换位置，直到不比他们小或者该节点已经到达叶子节点）
     *
     *  注意：如果有右节点则一定会有左节点，有左节点不一定有右节点
     * @return
     */
    @Override
    public T removeRoot() {


        T root = super.removeRoot();
        int size = getSize();
        if (size == 0){
            return root;
        }
        T left, right;
        int curIndex = 0;
        while ((left = super.leftChild(curIndex)) != null){

            right = super.rightChild(curIndex);

            boolean leftBigger = right == null || left.compareTo(right) > 0;
            if (leftBigger){
                if (left.compareTo(getCurrent(curIndex)) > 0) {
                    swapWithLeftChild(curIndex);
                    curIndex = getLeftChildMaybeIndex(curIndex);
                    continue;
                }
            }else{
                if (right.compareTo(getCurrent(curIndex)) > 0){
                    swapWithRightChild(curIndex);
                    curIndex = getRightMaybeIndex(curIndex);
                    continue;
                }
            }
            break;
        }

        return root;
    }


    private boolean biggerThanParent(int index){

        int paIndex = super.getParentMaybeIndex(index);
        if (index == paIndex) return false;
        return getCurrent(index).compareTo(getCurrent(paIndex)) > 0;
    }


    /**
     *  给定一批数据生成堆。时间复杂度优化为 O(n)。如果是一个个给，则还是为O(n log n）
     *  从最后一位开始，大于 parent 则与其交换， 然后 再与（可能是交换之后的新值）子节点比较（最大的大于他则与对应的交换，否则不交换）
     *   每一位的处理都是常数级时间。因此时间复杂度为 O(n)
     * @param data
     * @param capacity
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> MaxHeap<T> generate(T[] data, int capacity){


        MaxHeap<T> heap = new MaxHeap<>(capacity, data);
        if (data == null ||data.length == 0) return heap;

        for (int i = data.length - 1; i > 0 ; i--){

            if (data[i].compareTo(heap.getParent(i)) >  0){
                heap.swapWithParent(i);
            }
            T left = heap.leftChild(i);
            if (left == null) continue;
            T right = heap.rightChild(i);
            boolean leftBigger = right == null || left.compareTo(right) > 0;
            if (leftBigger){
                if (left.compareTo(data[i]) > 0){
                    heap.swapWithLeftChild(i);
                }
                continue;
            }
            if (right.compareTo(data[i]) > 0){
                heap.swapWithRightChild(i);
            }

        }

        return heap;
    }


    public static void test1(){



        MaxHeap<Integer> maxHeap =
                MaxHeap.generate(NumberUtil.generateRandomIntegerArray(30, 1000), 20);

        while (maxHeap.getRoot() != null){

            System.out.print(" " + maxHeap.removeRoot());
        }
    }

    /**
     * 对数器。利用了java的优先队列（注意java的优先队列是小堆，和实现的大堆进行处理比较时，要将插入元素取反）。
     *
     */
    public static void test(){

        int capacity = NumberUtil.randomData(10000);
        MaxHeap<Integer> maxHeap = new MaxHeap<>(capacity);
        int maxSize = NumberUtil.randomData(10000);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Math.max(1, capacity));
        for (int i = 0 ; i < Math.min(capacity, maxSize); i++){
            int data = NumberUtil.randomData(10000);
            maxHeap.addNode(data);
            queue.add(-data);
            if (!queue.peek().equals(-maxHeap.getRoot())){

                System.out.println("add head not the same. \n");
            }

        }
        while (maxHeap.getRoot() != null){

            Integer integer1 = maxHeap.removeRoot();
            Integer integer2 = queue.poll();
            if (!integer1.equals(-integer2)){
                System.out.println("poll head not the same. \n");
            }
//            System.out.print("integer1:" + integer1 + " integer2:" + integer2);
        }


    }

    public static void main(String[] args) {

//        for (int  i  = 0; i  < 10000; i++) {
//            test();
//        }
        for (int i = 0; i < 100;  i++) {
            test1();
            System.out.println("");
        }
    }


}
