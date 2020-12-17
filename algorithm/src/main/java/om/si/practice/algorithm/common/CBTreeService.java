package om.si.practice.algorithm.common;

/**
 * @author: HongZhenSi
 * @date: 2020/12/17
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public interface CBTreeService<T extends Comparable<T>> {

    T getRoot();

    T removeRoot();

    T leftChild(int index);

    T rightChild(int index);

    T getParent(int index);

    T getCurrent(int index);

    boolean swapWithParent(int index);

    boolean swapWithLeftChild(int index);

    boolean swapWithRightChild(int index);

    int getSize();

    void addNode(T node);

    boolean offerNode(T node);

}
