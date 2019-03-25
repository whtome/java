package my_sort;

import www.bittech.util.Message;

/**
 * @Author: yuisama
 * @Date: 2019-03-10 00:04
 * @Description:
 */
public class TestSort {
    public static void main(String[] args) {
        Message message = new Message();
        message.fun();
    }
    public static void binaryInsertSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            for (int i = 1;i < n;i++) {
                // 要比较的元素
                int value = array[i];
                // 有序数组的第一个元素
                int low = 0;
                // 有序数组的最后一个元素
                int high = i - 1;
                int j = 0;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (array[mid] < value) {
                        low = mid + 1;
                    }else {
                        high = high - 1;
                    }
                }
                // 此时待插入的元素位置为high + 1;
                for (j = i - 1;j > high;j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = value;
            }
        }
    }
    public static void selectionSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            for (int i = 0;i < n - 1;i++) {
                int minIndex = i;
                for (int j = i + 1;j < n;j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    public static void shellSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            int step = n / 2;
            while (step >= 1) {
                for (int i = step;i < n;i++) {
                    int value = array[i];
                    int j = i - step;
                    for (;j >= 0;j-=step) {
                        if (value < array[j]) {
                            array[j + step] = array[j];
                        }else {
                            break;
                        }
                    }
                    array[j+step] = value;
                }
                step = step / 2;
            }
        }
    }
    public static void mergeSort(int[] array) {
        long start = System.currentTimeMillis();
        mergeSortIntern(array,0,array.length-1);
        long end = System.currentTimeMillis();
        System.out.println("归并排序共耗时:"+(end-start)+"毫秒");
    }
    public static void quickSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            SortInternal(array,0,n-1);
        }
        long end = System.currentTimeMillis();
        System.out.println("快速排序共耗时:"+(end - start)+"毫秒");
    }
    public static void quickSort2(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            quickSortInternal2(array,0,n-1);
        }
        long end = System.currentTimeMillis();
        System.out.println("二路快速排序共耗时:"+(end - start)+"毫秒");
    }
    public static void quickSort3(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            quickSortInternal3(array,0,n-1);
        }
        long end = System.currentTimeMillis();
        System.out.println("三路快速排序共耗时:"+(end - start)+"毫秒");
    }

    /**
     * 快速排序递归版本
     * @param array 待排序的数组
     * @param p 数组起始位置
     * @param r 数组终止位置
     */
    private static void SortInternal(int[] array,int p,int r) {
        if (p >= r) {
            return;
        }
        // 获取分区点
        int q = partition(array,p,r);
        SortInternal(array,p,q-1);
        SortInternal(array,q+1,r);
    }

    /**
     *  对array[l...r]部分进行partition操作
     *  返回p, 使得array[l...p-1] < array[p] ; array[p+1...r] > array[p]
     * @param array 待排序数组
     * @param l 数组开始点
     * @param r 数组结束点
     * @return 分区点下标
     */
    private static int partition(int[] array,int l,int r) {
        // 随机选取待排序数组中的任意一个元素
        int randomIndex = (int) (Math.random()*(r-l+1) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        int j = l;
        for (int i = l + 1;i <= r;i++) {
            // 每当碰到小于比较元素的值与j+1位置交换，小于区间长度加一
            if (array[i] < v) {
                swap(array,j+1,i);
                j++;
            }
        }
        // 当for循环走完只需要将array[l]位置与array[j]位置交换即可保证索引小于j的元素均小于v，
        // 索引大于j的元素均大于j
        swap(array,l,j);
        return j;
    }

    /**
     * 归并排序递归函数
     * @param array 要排序的集合
     * @param p 开始位置
     * @param r 结束位置
     */
    private static void mergeSortIntern(int[] array,int p,int r) {
        // 终止条件
        if(p >= r) {
            return;
        }
        int mid = (p + r) / 2;
        mergeSortIntern(array,p,mid);
        mergeSortIntern(array,mid+1,r);
        // 将array[p...mid]与array[mid+1...r]合并为array[p...r]
        merge(array,p,mid,r);
    }

    /**
     * 合并函数
     * @param array
     * @param p 开始位置
     * @param mid 中间位置
     * @param r 结束位置
     */
    private static void merge(int[] array,int p,int mid,int r) {
        int i = p;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[r-p+1];
        // 两部分数组都还有数据
        while (i <= mid && j <= r) {
            // 小于等于来保证有序性
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            }else {
                temp[k++] = array[j++];
            }
        }
        // 判断两个数组中哪个还有元素
        int start = i;
        int end = mid;
        // 剩下第二个数组
        if (j <= r) {
            start = j;
            end = r;
        }
        // 将剩余数据拷贝回temp数组
        while (start <= end) {
            temp[k++] = array[start++];
        }
        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i<= r-p;i++) {
            array[p+i] = temp[i];
        }
    }

    private static void quickSortInternal2(int[] arr,int p,int r) {
        if (p >= r) {
            return;
        }
        int q = partition2(arr,p,r);
        quickSortInternal2(arr,p,q-1);
        quickSortInternal2(arr,q+1,r);
    }
    private static int partition2(int[] array,int l,int r) {
        // 随机选取待排序数组中的任意一个元素
        int randomIndex = (int) (Math.random()*(r-l+1) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        int i = l+1,j = r;
        while (true) {
            while (i <= r && array[i] < v) i++;
            while (j >= l+1 && array[j] > v) j--;
            if (i > j) {
                break;
            }
            swap(array,i,j);
            i++;
            j--;
        }
        // 循环走完后，j索引下标为分区点位置
        swap(array,l,j);
        return j;
    }
    private static void quickSortInternal3(int[] arr,int l,int r) {
        if (l >= r) {
            return;
        }
        // 随机选取待排序数组中的任意一个元素
        int randomIndex = (int) (Math.random()*(r-l+1) + l);
        swap(arr,l,randomIndex);
        int v = arr[l];
        // arr[l+1...lt] < v
        int lt = l;
        // arr[lt+1...i-1] == v
        int i = l + 1;
        // arr[gt...r] > v
        int gt = r + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr,i,lt+1);
                lt++;
                i++;
            }else if (arr[i] > v) {
                swap(arr,i,gt-1);
                gt--;
            }else {
                i++;
            }
        }
        // 循环走完只需要将l位置的元素与lt交换即为分区点
        swap(arr,l,lt);
        quickSortInternal3(arr,l,lt-1);
        quickSortInternal3(arr,gt,r);
    }
    /**
     * 交换数组中两个索引下标的元素
     * @param array
     * @param x
     * @param y
     */
    private static void swap(int[] array,int x,int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
