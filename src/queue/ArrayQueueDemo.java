package queue;

import java.util.Scanner;

/**
 * @author Joshua.H.Brooks
 * @description  单向链表， 用一次就不能用了， 没有达到复用的效果。
 * @date 2022-05-23 10:00
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(5);
        //
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("t(traverse): 遍历队列\th(head): 显示队列首个元素\ta(add): 添加一个元素到队列\te(exit): 退出程序队列\tg(get): 获取队列首个元素");
            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    System.out.println("请输入一个数");
                    int i = scanner.nextInt();
                    aq.addElement(i);
                    break;
                case 't':
                    aq.traverse();
                    break;
                case 'g':
                    try{
                        int element = aq.getElement(true);
                        System.out.printf("取回队列头元素: %d\t",element);
                        System.out.println();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try{
                        int element = aq.getElement(false);
                        System.out.printf("查看队列头元素: %d\t",element);
                        System.out.println();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                case 'e':
                    System.out.println("您选择了退出， 程序即将退出");
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("成功退出了");
/*
        System.out.println(aq.isEmpty());
        System.out.println(aq.isFull());
        aq.addElement(1);
        aq.addElement(2);
        aq.addElement(3);
        aq.addElement(4);
        aq.addElement(5);
        aq.addElement(6);
        aq.traverse();
        System.out.println(aq.getElement(true));
        System.out.println(aq.getElement(true));
        System.out.println(aq.getElement(true));
        System.out.println(aq.getElement(true));
        System.out.println(aq.getElement(true));
       // System.out.println(aq.getElement(true));

 */

    }
}

/**
 * 模拟队列的实现， 注意⚠️ front为队列的首元素的前一位置， rear为队列尾部。
 */
class ArrayQueue {
    /**
     * 数组最大容量
     */
    private int maxSize;

    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 模拟队列 用于存放数据
     */
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 向队列添加元素
     * @param element
     */
    public void addElement(int element){
        if(this.isFull()){
            System.out.println("队列已满 不可加入数据");
            return;
        }
        rear++;
        arr[rear] = element;
    }

    /**
     * @return 获取的元素
     */
    public int getElement(boolean getFlag){
        if(this.isEmpty()){
            throw new RuntimeException("queue is empty, cannt return or print any element");
        }
        //如果getFlag 是 true表示获取首元素， 如果getFlag为false 则是打印首元素。
        if(getFlag) front++;
        return arr[front];
    }

    public void traverse(){
        if(this.isEmpty()){
            System.out.println("queue is empty, traverse meaningless");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

}
