package www.ds;

//火车类
public class LinkedList {
    //火车头节点
    private Node head;
    private int size;
    public LinkedList() {
        head = null;
        size = 0;
    }

    //每个节点类
    private class Node {
        Object data;
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this.data = data;
        }
    }

    //将节点头插进入火车
    public void addFirst(Object data) {
        //创建新节点存放数据
//        Node newNode = new Node(data);
//        //将当前节点指向原来火车头节点
//        newNode.next = head.next;
//        head.next = newNode;
        head = new Node(data,head);
        size++;
    }
    //在任意位置插入
    public void add(int index,Object data) {
        //判断index是否合法
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index Illegal!");
        }
        if (index == 0) {
            addFirst(data);
        }
        //找到index的前驱节点
        Node prev = head;
        for (int i = 0; i < index-1; i++) {
            prev = prev.next;
        }
//        Node newNode = new Node(data);
//        newNode.next = prev.next;
//        prev.next = newNode;
        prev.next = new Node(data,prev.next);
        size++;
    }
}
