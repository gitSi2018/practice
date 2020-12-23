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
public class MyOneDirentNode<T> {

    private T data;

    private MyOneDirentNode<T> nextNode;



    public MyOneDirentNode<T> delete(T data){
        if (data == null) throw new RuntimeException("data cannot be null");
        MyOneDirentNode<T> temp = new MyOneDirentNode<T>();
        temp.nextNode = this;
        MyOneDirentNode<T> pre = temp;
        MyOneDirentNode<T> cur = this;
        while(cur != null){

            if(data.equals(cur.data)){

                pre.nextNode = cur.nextNode;
            }else{
                pre = pre.nextNode;
            }
            cur = cur.nextNode;

        }
        return temp.nextNode;
    }

    @Override
    public String toString(){

        MyOneDirentNode<T> tempNode = this;
        StringBuilder builder = new StringBuilder();
        while (tempNode != null){

            builder.append(":").append(tempNode.getData());
            tempNode = tempNode.nextNode;
        }
        return builder.toString();
    }
}
