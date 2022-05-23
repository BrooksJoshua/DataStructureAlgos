package linkedlist;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-23 18:10
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "孙悟空", "孙行者");
        HeroNode heroNode2 = new HeroNode(2, "猪八戒", "天蓬元帅");
        HeroNode heroNode3 = new HeroNode(3, "沙悟净", "老沙");
        HeroNode heroNode4 = new HeroNode(4, "唐三藏", "御弟哥哥");

        SingleLinkedList sll = new SingleLinkedList();
        sll.add(heroNode1);
        sll.add(heroNode4);
        sll.add(heroNode3);
        sll.add(heroNode2);
        /**
         * 打印输出结果， 因为没有按其他额外要求排序加入， 所以结果是按加入单链表顺序输出的。
         * HeroNode{no=1, name='孙悟空', nickName='孙行者'}
         * HeroNode{no=4, name='唐三藏', nickName='御弟哥哥'}
         * HeroNode{no=3, name='沙悟净', nickName='老沙'}
         * HeroNode{no=2, name='猪八戒', nickName='天蓬元帅'}
         */
        sll.print();
        System.out.println("############                        按英雄排名加入                 ################");
        SingleLinkedList sll2 = new SingleLinkedList();
        sll2.addByOrder(heroNode1);
        sll2.addByOrder(heroNode4);
        sll2.addByOrder(heroNode3);
        sll2.addByOrder(heroNode2);

        sll2.print();

    }
}

/**
 * 定义单链表管理英雄
 */
class SingleLinkedList{
    /**
     * 先初始化一个头节点， 并且不要动
     */
    private final HeroNode head = new HeroNode(0,"","");

    /**
     * 添加节点到单向链表, 添加都是往最后一个节点。 所以思路是
     * 1: 先找到当前列表的最后一个节点
     * 2: 将最后一个节点的next执行即将要加入的这个新节点
     */
    public void add(HeroNode node){
        HeroNode temp = head;
        //遍历链表找到最后节点
        while(true){
            //是最后节点， 则退出
            if(temp.next == null){
                break;
            }
            //否则遍历节点后移
            temp = temp.next;
        }
        //所以当退出while循环时就只想了链表最后， 而且一定为走到最后
        //再将temp的next指向node也就把node连在了原有链表最后了。
        temp.next = node;
    }


    /**
     *  第二种添加方式， 添加英雄时按照排名插入到指定位置。
     */
    public void addByOrder(HeroNode node){
        //还是因为头节点不能动， 因此我们仍然通过一个辅助指针变量来帮助找到遍历添加的位置
        //因为是单链表， 所以我们找的temp是位于要加入位置的前一个节点。
        HeroNode temp  = head;
        //flag用来标识要添加的英雄是否已经存在。   默认为false。
        boolean flag = false;
        while(true){
            //不管有没有找到， 只要到最后了， 就要
            if(temp.next == null){
                break;
            }
            //因为是按照英雄排名插入， 所以最后的排序结果应该是: temp.no < node.no  < temp.next.no
            if(temp.next.no > node.no){
                break ;
            }else if(temp.next.no == node.no){
                //说明编号已经存在
                flag = true;
                break;
            }
            //如果上诉三个条件都不满足， 则继续找下一个
            temp = temp.next;
        }

        if(flag){
            System.out.println("该编号的英雄已存在添加失败");
        }
        else{
            node.next = temp.next;
            temp.next = node;
        }



    }



    /**
     * 显示链表 (遍历输出)
     */
    public void print(){
        //头节点的next为空说明链表为空。
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动， 所以需要一个辅助变量来遍历
        HeroNode temp =  head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp.toString());
            //遍历完后要将当前节点后移， 方便下次循环判断
            temp = temp.next;
        }

    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;

    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
