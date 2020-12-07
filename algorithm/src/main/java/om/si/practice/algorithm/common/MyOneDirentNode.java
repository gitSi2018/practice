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
