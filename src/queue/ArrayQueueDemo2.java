package queue;

import java.util.Scanner;

/**
 * @author Joshua.H.Brooks
 * @description 解决前一个demo中的可复用的问题
 * 对首位元素做变更：
 * front： 模拟队列第一个元素， 默认初始值 为 0
 * rear：  模拟队列最后一个元素的下一位置 默认初始值 为 0
 * 这个变更会导致:
 * 队列满的条件变成了： (rear+1) % maxSize = front
 * 队列空依旧是： front == rear
 * <p>
 * 队列中有效元素的个数: （rear-front+maxSize）% maxSize
 * （之所以要先+maxSize再% 是因为怕rear环形过了一个周期后，会变得比front小， 会导致负数）
 * 关于队列中有效元素个数为什么是这个公式， 可以参考：
 * https://www.cs.usfca.edu/~galles/visualization/QueueArray.html
 * 手动enqueue再dequeue就知道。
 * @date 2022-05-23 15:27
 */
public class ArrayQueueDemo2 {
    public static void main(String[] args) {
        CircleQueue cq = new CircleQueue(5);
        //
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("t(traverse): 遍历队列\th(head): 显示队列首个元素\ta(add): 添加一个元素到队列\te(exit): 退出程序队列\tg(get): 获取队列首个元素");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.print("请输入一个数:");
                    int i = scanner.nextInt();
                    cq.addElement(i);
                    break;
                case 't':
                    cq.traverse();
                    break;
                case 'g':
                    try {
                        int element = cq.getElement(true);
                        System.out.printf("取回队列头元素: %d\t", element);
                        System.out.println();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int element = cq.getElement(false);
                        System.out.printf("查看队列头元素: %d\t", element);
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 'e':
                    System.out.println("您选择了退出， 程序即将退出");
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("成功退出了");
    }
}

/**
 * 模拟队列的实现， 注意⚠️ front为队列的首元素的前一位置， rear为队列尾部。
 */
class CircleQueue {
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

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
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
        return ((rear + 1) % maxSize) == front;
    }

    /**
     * 向队列添加元素
     *
     * @param element
     */
    public void addElement(int element) {
        if (this.isFull()) {
            System.out.println("队列已满 不可加入数据");
            return;
        }
        arr[rear] = element;
        rear = (rear + 1) % maxSize;
    }

    /**
     * @return 获取的元素
     */
    public int getElement(boolean getFlag) {
        if (this.isEmpty()) {
            throw new RuntimeException("queue is empty, cannt return or print any element");
        }
        //如果getFlag 是 true表示获取首元素， 如果getFlag为false 则是打印首元素。
        int headElement = 0;
        if (getFlag) {
            headElement = arr[front];
            front = (front+1) % maxSize;
        }
        return getFlag? headElement: arr[front];
    }

    public void traverse() {
        if (this.isEmpty()) {
            System.out.println("queue is empty, traverse meaningless");
            return;
        }
        //此处打印的是有效元素
        for (int i = front; i < front + ((rear+maxSize-front) % maxSize); i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }

}
