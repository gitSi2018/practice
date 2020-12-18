package om.si.practice.algorithm.tree;


/**
 * @author: HongZhenSi
 * @date: 2020/12/17
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public abstract class MyCBAbstractTree<T extends Comparable<T>> implements CBTreeService<T>{

//    private static final int DEFAULT_CAPACITY  = 1 <<

    private int size  = 0;

    private int capacity;

    private Object[] nodes;


//    public MyCBAbstractTree(){
//
//    }

    public MyCBAbstractTree(int capacity){

        if (capacity <= 0) throw new IllegalArgumentException("capacity must great than 0");
        this.capacity = capacity;
        this.nodes = new Object[capacity];

    }

    public MyCBAbstractTree(int capacity, T[] nodes){

        if (capacity <= 0) throw new IllegalArgumentException("capacity must great than 0");
        this.capacity = capacity;
        this.nodes = nodes;
        this.size = nodes.length;
    }

    public void addNode(T node){

        if (node ==  null) throw new IllegalArgumentException("node cannot be null");
        if (size  == capacity){
            throw new IllegalArgumentException("Tree is full.");
        }
        nodes[size++] = node;
    }

    public boolean offerNode(T node){

        if (node ==  null) throw new IllegalArgumentException("node cannot be null");
        if (size == capacity){
            return false;
        }
        nodes[size++] = node;
        return true;
    }

    public int getSize(){

        return size;
    }


    public T getRoot(){

        if (size == 0) return null;
        return elementData(0);
    }

    public T removeRoot(){

        if (size == 0) return null;
        T root = elementData(0);
        if (--size > 0) {

            nodes[0] = nodes[size];
        }
        return root;
    }

    public T leftChild(int index){

        if (index < 0)  throw new IllegalArgumentException("index cannot less than 0");
        if (index >= size)  throw new IllegalArgumentException("index cannot equal or bigger than size");
        int lcIndex = getLeftChildMaybeIndex(index);
        if (lcIndex > size - 1) return null;
        return elementData(lcIndex);
    }

    public T rightChild(int index){

        if (index < 0)  throw new IllegalArgumentException("index cannot less than 0");
        if (index >= size)  throw new IllegalArgumentException("index cannot equal or bigger than size");
        int rcIndex = getRightMaybeIndex(index);
        if (rcIndex > size - 1) return null;
        return elementData(rcIndex);
    }

    public T getParent(int index){

        if (index < 0)  throw new IllegalArgumentException("index cannot less than 0");
        if (index >= size)  throw new IllegalArgumentException("index cannot equal or bigger than size");
        int parentIndex = getParentMaybeIndex(index);
        return elementData(parentIndex);
    }

    public T getCurrent(int index){

        if (index < 0)  throw new IllegalArgumentException("index cannot less than 0");
        if (index >= size)  throw new IllegalArgumentException("index cannot equal or bigger than size");
        return elementData(index);
    }

    public boolean swapWithParent(int index){

        return swap(index, getParentMaybeIndex(index));
    }

    public boolean swapWithLeftChild(int index){

        return swap(index, getLeftChildMaybeIndex(index));
    }

    public boolean swapWithRightChild(int index){

        return swap(index, getRightMaybeIndex(index));
    }

    private boolean swap(int idx1, int idx2){

        if (idx1 > size - 1 || idx2 > size - 1) return false;
        if (idx1 == idx2) return true;
        T temp = elementData(idx1);
        nodes[idx1] = nodes[idx2];
        nodes[idx2] = temp;
        return true;
    }

    public int getParentMaybeIndex(int index){

        return (index - 1) / 2;
    }

    public int getLeftChildMaybeIndex(int index){

        return (index * 2) + 1;
    }

    public int getRightMaybeIndex(int index){

        return (index * 2) + 2;
    }


    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) nodes[index];
    }
}
