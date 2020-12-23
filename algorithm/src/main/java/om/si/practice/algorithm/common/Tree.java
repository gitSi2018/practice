package om.si.practice.algorithm.common;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

/**
 * @author: HongZhenSi
 * @date: 2020/12/21
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class Tree {

    private int val;

    private Tree left;

    private Tree right;


    public Tree(int val){
        this.val = val;
    }


    // 用递归实现二叉树的遍历非常简单。内部蕴含着递归栈。使用非递归方式的话，可采用合适的压栈的方式实现

    public static void preOrder(Tree root){

        if(root == null) return;
        // 访问理当前节点
        System.out.print(" " + root.val);
        vals.add(root.val);
        // 访问左子树
        preOrder(root.left);
        // 访问右子树
        preOrder(root.right);
    }

    /**
     *  使用栈的方式实现前序遍历。
     *  只有单向指针的树的遍历本质上是要用栈（递归实际上也是递归栈）的，因为遍历到叶子节点之后无法通过指针回到父节点，
     *  通过栈的话，可以通过pop来实现跳到其他节点（如父节点、右节点等）。
     *  前序实现的核心：先压root，然后在while循环里面实现遍历，实现方式为，如果栈不为空 则进入循环
     *     每次循环都弹出一个节点最为当前被遍历到的节点，且如果该节点有右孩子则将其压栈，如果有左孩子再将左孩子压栈。此次循环结束
     * @param root
     */
    public static void preOrderCom(Tree root){

        if(root == null) return;

        Stack<Tree> stack = new Stack<>();
        stack.push(root);
        while(stack.size() > 0){

            Tree cur = stack.pop();
            valsCom.add(cur.val);
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     *  递归实现中序遍历。递归栈，在这里一个节点实际上会被访问3次
     * @param root
     */
    public static void inOrder(Tree root){

        if(root == null) return;
        // 先访问左子树
        inOrder(root.left);
//        System.out.print( " " + root.val);
        // 访问当前节点
        vals.add(root.val);
        // 访问右子树
        inOrder(root.right);
    }


    /**
     *  中序用栈实现。
     *    实现方式：首先将root压栈，定义一个当前节点作为辅助节点，初始时指向root
     *        如果栈的空间大于0 则进入while循环
     *          在循环中，如果当前节点有左孩子，则需要将左孩子压栈，当前节点指向左孩子
     *                  如果当前节点没有左孩子，则弹出节点，并且访问弹出节点。将当前节点指向弹出节点，如果当前节点有右孩子，则将右孩子压栈，当前节点指向右孩子。
     *                  此次循环结束
     * @param root
     */
    public static void inOrderCom(Tree root){

        if(root == null) return;
        Tree cur = root;
        Stack<Tree> stack = new Stack<>();
        stack.push(root);
        while(stack.size() > 0){

            if(cur != null && cur.left != null){
                stack.push(cur.left);
                cur = cur.left;
                continue;
            }
            cur = stack.pop();
            valsCom.add(cur.val);
            cur = cur.right;
            if(cur != null){
                stack.push(cur);
            }
        }
    }

    public static void sufOrder(Tree root){

        if(root == null) return;
        sufOrder(root.left);
        sufOrder(root.right);
        vals.add(root.val);
//        System.out.print(" " + root.val);
    }

    public static void sufOrderCom(Tree root){

        if(root == null) return;
        Stack<Tree> stack = new Stack<>();
        Tree help = root;
        stack.push(root);
        // 如果栈还有数据，进入循环
        //  栈peek为当前访问节点
        //  当前节点第一次访问（左右节点都不是上次遍历的节点）的话，压栈。进行下一次循环
        //  如果左孩子为空： 右孩子为空或者上次遍历到的是右孩子则弹出栈，并且将help指向弹出的节点。 负责则将右孩子压栈。 进行下一次循环
        //  如果左孩子不为空：
        //     上次遍历到的既不是左节点也不是右节点，则压栈。进行下一次循环
        //     上次遍历到的是左孩子，如果右孩子不为空则压栈。进行下一次循环
        //     剩下的情况（上次遍历到左节点且右孩子为空 || 上次遍历到右孩子）就是弹出该节点并且help指向弹出节点。进行下一次循环
        while(stack.size() > 0){


            Tree cur = stack.peek();
            if(cur.left == null){
                if(cur.right == null || help == cur.right){
                    //需要弹出当前节点
                    cur = stack.pop();
                    valsCom.add(cur.val);
                    help = cur;
                    continue;
                }
                //需要压入右节点
                stack.push(cur.right);
                continue;
            }
            //左节点不为空
            if(help != cur.left && help != cur.right){
                //第一次访问
                stack.push(cur.left);
                continue;
            }
            if(help == cur.left && cur.right != null){
                //压右节点
                stack.push(cur.right);
                continue;
            }
            // 只有没有右节点或者 help 等于右节点，弹出当前节点
            cur = stack.pop();
            valsCom.add(cur.val);
            help = cur;

        }
    }

    private static AtomicInteger count = new AtomicInteger(20);

    private static List<Integer> vals = new ArrayList<>();

    private static List<Integer> valsCom = new ArrayList<>();

    private static Tree init(){

        Tree root = new Tree(0);
        root = initRandom(root);
//        root = init(root, new AtomicInteger(10));
        root = init(root);
        // pre compare
//        preOrder(root);
//        preOrderCom(root);
        // in compare
//        inOrder(root);
//        inOrderCom(root);

        sufOrder(root);
        sufOrderCom(root);
        int size = vals.size();
//        System.out.println("\n vals size:" + size);
//        System.out.println("    vals:" + vals);
//        System.out.println(" valsCom:" + valsCom);
        for(int i = 0; i < size; i++){
            if(!vals.get(i).equals(valsCom.get(i))){
                System.out.print(" error. not the same. ");
                break;
            }
        }
        vals.clear();
        valsCom.clear();
        count = new AtomicInteger(20);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        }catch(Exception e){
//
//        }
        return root;
    }


    private static void duiShuQi(int size){

        while(size-- > 0){

            init();
        }
    }

    private static Tree init(Tree node){


        if (node.left != null) {

            count.getAndDecrement();
            initRandom(node.left);
            init(node.left);
        }
        if (node.right != null) {

            count.getAndDecrement();
            initRandom(node.right);
            init(node.right);
        }
        return node;
    }

    private static Tree initRandom(Tree node){

        if(count.intValue() < 0) return node;
        double d = Math.random();
        if(d <= 0.15){
            node.left = new Tree((int)(Math.random() * 1000));
            return node;
        }
        if(d <= 0.3){
            node.right = new Tree((int)(Math.random() * 1000));
            return node;
        }
        if(d <= 0.5){
            return node;
        }
        node.left = new Tree((int)(Math.random() * 1000));
        node.right = new Tree((int)(Math.random() * 1000));
        return node;
    }

    public static void main(String... args){

//        Tree root = init();
        long start = System.currentTimeMillis();
        duiShuQi(100);
        System.out.print("running time:" + (System.currentTimeMillis() - start) / 1000);
    }
}
