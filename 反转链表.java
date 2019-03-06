反转链表:
时间复杂度:O(n) 空间复杂度:O(n)
    public ListNode reverseList(ListNode head) {
        ListNode fir = new ListNode(-1);
        // 从前到后遍历原链表
        for(ListNode temp = head;temp!=null;temp=temp.next) {
            // 使用头插法插入新链表
            ListNode newNode = new ListNode(temp.val);
            newNode.next = fir.next;
            fir.next = newNode;
        }
        return fir.next;
    }

空间复杂度O(1)       

public ListNode reverseList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        if(head == null || head.next == null) {
            return head;
        }else {
            ListNode f = dummyHead.next;
            ListNode s = f.next;
            while(s!=null) {
                // 下一次迭代的位置
                f.next = s.next;
                s.next = dummyHead.next;
                dummyHead.next = s;
                s = f.next;
            }
            return dummyHead.next;
        }
    }

1234 n/2 + 1
12345 n/2 + 1


1234
倒数k节点
public ListNode FindKthToTail(ListNode head,int k) {
        if(head!=null) {
             ListNode first = head;
             ListNode second = head;
        for(int i = 0;i < k;i++) {
            if(first != null) {
                first = first.next;
            }else {
                return null;
            }
        }
        while(first != null) {
            second = second.next;
            first = first.next;
        }
        return second;
        }
       return null;
    }

f = 1  f = 3 f = null
l = 1  l = 2 l = 3
12345
f = 1  f = 3 f = 5 f = null
l = 1  l = 2 l = 3

1 2 3 4 5 k=2
    l   f

1 2 3 4 5 6 k=2
      l   f

快慢指针:找中间位置,找倒数k节点,找是否带环.



