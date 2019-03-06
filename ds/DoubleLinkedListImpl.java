package www.ds;

public class DoubleLinkedListImpl implements Sequence {
    private Node head;
    private Node tail;
    private int size;


    private class Node {
        Node prev;
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 尾插法
     * @param data 要存储的元素
     */
    @Override
    public void add(Object data) {
        // 产生新节点，尾插入链表中
        Node newNode = new Node(tail,data,null);
        if (head == null) {
            head = newNode;
        }else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public boolean remove(int index) {
        rangeCheck(index);
        // 要删除的节点
        Node cur = node(index);
        Node prev = cur.prev;
        Node next = cur.next;
        // 要删除的是头结点
        if (prev == null) {
            head = cur.next;
        }
        // 存在前驱节点
        else {
            prev.next = cur.next;
            cur.prev = null;
        }
        // 要删的是尾节点
        if (next == null) {
            tail = cur.prev;
        }else {
            next.prev = cur.prev;
            cur.prev = null;
        }
        size--;
        return false;
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        // 取得指定位置Node
        return node(index).data;
    }

    @Override
    public boolean contains(Object data) {
        return false;
    }

    @Override
    public Object set(int index, Object newData) {
        rangeCheck(index);
        // 取得指定位置Node
        Node node = node(index);
        Object oldData = node.data;
        node.data = newData;
        return oldData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (Node temp = head;temp!=null;) {
            Node next = temp.next;
            temp.prev = temp.next = null;
            temp.data = null;
            temp = next;
            size--;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] data = new Object[size];
        int i = 0;
        for (Node temp = head;temp != null;temp = temp.next) {
            data[i++] = temp.data;
        }
        return data;
    }
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal Index!");
        }
    }
    private Node node(int index) {
        // 此时要查找的节点在中间位置左边
        if (index < (size >> 1)) {
            Node temp = head;
            for (int i = 0;i < index;i++) {
                temp = temp.next;
            }
            return temp;
        }else {
            Node temp = tail;
            for (int i = size - 1;i > index;i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }
}
