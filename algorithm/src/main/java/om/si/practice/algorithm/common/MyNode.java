package om.si.practice.algorithm.common;

import lombok.Data;

/**
 * @author: HongZhenSi
 * @date: 2020/11/29
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Data
public class  MyNode<T> {

    private T data;

    private MyNode<T> nextNode;

    private MyNode<T> preNode;


    @Override
    public String toString(){

        StringBuilder builder = new StringBuilder();
        builder.append(data).append(":");
        MyNode tailNode = nextNode;
        while (tailNode != null){
            builder.append(tailNode.getData()).append(":");
            tailNode = tailNode.getNextNode();
        }
        return builder.toString();
    }

    public MyNode deleteSpecifyDataNode(T data){

        if (data == null) throw new RuntimeException("data cannot be null");
        MyNode head = this;
        MyNode delete = new MyNode();
        delete.setNextNode(head);
        while (delete !=null && delete.getNextNode() != null){
            MyNode nextNode = delete.getNextNode();
            if (data.equals(nextNode.getData())){

                if (head == nextNode){
                    head = nextNode.getNextNode();
                }
            }else {
                delete = nextNode;
            }
            delete.setNextNode(nextNode.getNextNode());
        }
        return head;
    }

    public static void main(String[] args) {



    }
}
