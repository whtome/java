冒泡:
空间复杂度:O(1)
时间复杂度:O(n^2)
稳定性:是

插入:
直接插入:
空间复杂度:O(1)
时间复杂度:
最好情况:O(n)
最坏情况:O(n^2)
稳定性:是

折半插入排序:
优化:寻找插入位置，从中间值开始比较(二分查找)
二分查找:在一个有序的数据集上，将待比较元素与中间位置元素比较，若小于中间位置元素，则要插入的位置一定在中间位置前面；否则在中间位置之后。

希尔排序:
优化:原先找到插入位置后，元素是一个一个向后搬移，损耗较大。
能否在搬移元素时多走几步，即一次多搬移几个元素。

原先:一次走一步
一次多走几步

空间复杂度:O(1)
稳定性:稳定

选择排序:
时间复杂度:O(n^2)
空间复杂度:O(1)
稳定性:不稳定排序
eg:5 8 5 2 9

插入还是选择?
在相同数据集下，推荐使用插入排序，由于选择排序的不稳定性

在近乎有序的数据下，直接插排、选择排序、希尔排序、折半插排

归并排序:O(nlogn)

归并与快排都应用分治思想,如何在O(n)内寻找一个无序数组的第K大元素？

分治思想:将一个大的问题分为若干个足够小的问题，当所有小问题解决后，将结果合并起来就是整个问题的求解。

所有使用分治思想解决的问题均可利用递归的技巧完美解决。

public static boolean isPalindrome(Node head) {
        if(head == null || head.next == null) {
            return true;
        }
        Node low,fast;
        low = fast = head;
        while(fast != null && low!=null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;
        }
        Node mid = low;
        // 反转中间节点后的链表
        Node dummyHead = new Node(-1);
        dummyHead.next = mid;
        Node cur = dummyHead.next;
        Node sec = cur.next;
        while(sec != null) {
            cur.next = sec.next;
            sec.next = dummyHead.next;
            dummyHead.next = sec;
            sec = cur.next;
        }
        fast = head;
        // 从前开始遍历两个链表，若遇到节点值不同的直接返回false
        while(fast != null && dummyHead.next != null) {
            if(fast.val != dummyHead.next.val){
                return false;
            }
            fast = fast.next;
            dummyHead.next = dummyHead.next.next;
        }
        return true;
    }







