import java.util.Arrays;

/**
 * @Author: yuisama
 * @Date: 2019-03-07 19:23
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int n = 1000000;
        int[] data = SortHelper.generateArray(n,0,1000000);
//        insertSort(SortHelper.copyArray(data));
//        binaryInsertSort(SortHelper.copyArray(data));
//        selectionSort(SortHelper.copyArray(data));
        mergeSort(SortHelper.copyArray(data));
        quickSort(SortHelper.copyArray(data));
        quickSort3(SortHelper.copyArray(data));
    }
    public static void bubbleSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            // 控制冒泡排序的次数
            // 一次冒泡只能确保一个元素到达最终位置
            for (int i = 0 ;i < n;i++) {
                boolean flag = false;
                for (int j = 0;j < n-i-1;j++) {
                    if (array[j] > array[j+1]) {
                        flag = true;
                        // 交换两个相邻元素
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序总耗时为:"+(end-start)+"毫秒");
    }
    public static void insertSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            // 将待排序数据集看为两个区间
            for (int i = 1;i < n;i++) {
                // 当前待排序数组中第一个元素
                int value = array[i];
                int j = i - 1;
                for (;j >= 0;j--) {
                    if (array[j] > value) {
                        // 搬移元素
                        array[j + 1] = array[j];
                    }
                }
                // 已找到待插入位置,j + 1
                array[j + 1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("直接插入排序共耗时:"+(end - start)+"毫秒");
    }
    public static void binaryInsertSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            for (int i = 1;i < n;i++) {
                // 已排序空间的第一个元素
                int value = array[i];
                int low = 0;
                int high = i - 1;
                int j = i - 1;
                // 寻找待插入的位置
                while (low <= high) {
                    // 寻找中间位置
                    int mid = (low + high) / 2;
                    if (array[mid] < value) {
                        low = mid + 1;
                    }else {
                        high = mid - 1;
                    }
                }
                // 已找到插入位置 high + 1;
                for (;j >= high + 1;j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("折半插入排序耗时:"+(end - start)+"毫秒");
    }
    public static void shellSort(int[] array) {
        long start = System.currentTimeMillis();
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
                        if (array[j] > value) {
                            array[j + step] = array[j];
                        }else {
                            break;
                        }
                    }
                    array[j + step] = value;
                }
                step = step / 2;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("希尔排序耗时:"+(end-start)+"毫秒");
    }
    public static void selectionSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }else {
            // 从未排序空间中选出最小值
            for (int i = 0;i < n - 1;i++) {
                int minIndex = i;
                for (int j = i + 1;j < n;j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                // 此时minIndex对应的元素一定是当前未排序区间的最小值
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("选择排序共耗时:"+(end-start)+"毫秒");
    }
    public static void mergeSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }
        int mid = n / 2;
        mergeInternal(array,0,n-1);
        long end = System.currentTimeMillis();
        System.out.println("归并排序共耗时:"+(end-start)+"毫秒");
    }
    public static void quickSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }
        quickSortInternal(array,0,n-1);
        long end = System.currentTimeMillis();
        System.out.println("快速排序总耗时为:"+(end-start)+"毫秒");
    }
    public static void quickSort3(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        }
        quickSortInternal3(array,0,n-1);
        long end = System.currentTimeMillis();
        System.out.println("三路快排共耗时:"+(end-start)+"毫秒");
    }
    private static void quickSortInternal3(int[] array,int l,int r) {
        if (r - l <= 15) {
            // 调用直接插入排序
            insertSort(array,l,r);
            return;
        }
        int randomIndex = (int) ((Math.random() * (r - l + 1)) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        // arr[l+1...lt] < v
        int lt = l;
        // arr[gt...r] > v
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (array[i] < v) {
                swap(array,lt+1,i);
                lt ++;
                i ++;
            }else if (array[i] > v) {
                swap(array,gt-1,i);
                gt--;
            }else {
                i++;
            }
        }
        swap(array,l,lt);
        quickSortInternal3(array,l,lt-1);
        quickSortInternal3(array,gt,r);
    }
    private static void quickSortInternal(int[] array,int l,int r) {
        if (l >= r) {
            return;
        }
        int q = partition2(array,l,r);
        quickSortInternal(array,l,q-1);
        quickSortInternal(array,q+1,r);
    }

    /**
     * 分区点选取方法
     * @param array 待排序的数组
     * @param l 数组最左元素
     * @param r 数组最右元素
     * @return 返回已经到达最终位置的分区点下标
     */
    private static int partition(int[] array,int l,int r) {
        // 返回下标为l..r的任意一个数
        int randomIndex = (int) ((Math.random() * (r-l+1)) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        // array[l+1..j] < v
        int j = l;
        // array[j+1...i-1] > v
        int i = l + 1;
        for (;i <= r;i++) {
            if (array[i] < v) {
                // 交换i与j+1元素
                swap(array,j+1,i);
                j++;
            }
        }
        // 交换l与j的元素
        swap(array,l,j);
        return j;
    }
    private static int partition2(int[] array,int l,int r) {
        int randomIndex = (int) ((Math.random() * (r - l + 1)) + l);
        swap(array,l,randomIndex);
        int v = array[l];
        // array[l+1..i-1] < v
        int i = l + 1;
        // array[j+1...r] > v
        int j = r;
        while (true) {
            // 从前向后扫描
            while (i <= r && array[i] < v) i++;
            // 从后向前扫描
            while (j >= l+1 && array[j] > v) j--;
            if (i > j) {
                break;
            }
            swap(array,i,j);
            i++;
            j--;
        }
        swap(array,l,j);
        return j;
    }
    private static void mergeInternal(int[] array,int low,int high) {
        if (high - low <= 15) {
            insertSort(array,high,low);
            return;
        }
        int mid = (low + high) / 2;
        // 左边小数组
        mergeInternal(array,low,mid);
        // 右边小数组
        mergeInternal(array,mid+1,high);
        // 合并
        if (array[mid] >= array[mid+1]) {
            merge(array,low,mid,high);
        }
    }
    private static void merge(int[] array,int p,int q,int r) {
        int i = p;
        int j = q + 1;
        int[] temp = new int[r-p+1];
        int k = 0;
        // 此时两个数组中均有元素
        while (i <= q && j <= r) {
            if (array[i] <= array[j]) {
                // 第一个数组中的相同位置元素最小
                temp[k++] = array[i++];
            }else {
                temp[k++] = array[j++];
            }
        }
        // 判断当前还有哪个数组元素没有走完
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        // 把剩余元素直接放置在temp数组后即可
        while (start <= end) {
            temp[k++] = array[start++];
        }
        // 将临时空间中已经合并好的元素拷贝回原数组
        for (i = 0;i < r-p+1;i++) {
            array[p+i] = temp[i];
        }
    }
    private static void swap(int[] array,int indexA,int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
    private static void insertSort(int[] array,int l,int r) {
        if (l >= r) {
            return;
        }else {
            for (int i = l + 1;i <= r;i++) {
                int value = array[i];
                int j = i;
                for (;j > l && array[j-1] > value;j--) {
                    array[j] = array[j-1];
                }
                array[j] = value;
            }
        }
    }
}
