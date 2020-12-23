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
        MyNode<T> cur = nextNode;
        MyNode<T> tail = this;
        while (cur != null){
            builder.append(cur.getData()).append(":");
            cur = cur.nextNode;
            tail = tail.nextNode;
        }
        builder.append(" 反 ");
        while(tail != null){

            builder.append(tail.getData()).append(":");
            tail = tail.preNode;
        }
        return builder.toString();
    }

    public MyNode<T> deleteSpecifyDataNode(T data){

        if (data == null) throw new RuntimeException("data cannot be null");
        MyNode<T> cur = this;
        MyNode<T> temp = new MyNode<T>();
        temp.setNextNode(this);
        this.preNode = temp;
        while(cur != null){

            if(data.equals(cur.data)){

                if(cur.nextNode != null){
                    cur.nextNode.preNode = cur.preNode;
                }
                cur.preNode.nextNode = cur.nextNode;
            }
            cur = cur.nextNode;
        }
        if(temp.nextNode != null){
            temp.nextNode.preNode = null;
        }
        return temp.nextNode;
    }


    public static void main(String[] args) {



    }
}
