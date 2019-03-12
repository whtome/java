package www.sort;

public class Text {
    public static void main(String[] args) {
        int[] data = new int[] {1,5,7,9,8,2,4,5};
//        int[] data = SortHelper.generateArray(10000,0,100000);
//
//        bubbleSort(SortHelper.copyArray(data));
//        insertsort(SortHelper.copyArray(data));
//        binarySort(SortHelper.copyArray(data));
//        selectSort(SortHelper.copyArray(data));
        mergeSort(data);
        SortHelper.printArray(data);
    }

    public static void bubbleSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if (n <= 1) {
            return;
        } else {
            //控制排序的次数
            //一次冒泡只能确保一个元素到达最终位置
            for (int i = 0; i < n; i++) {
                boolean flag = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j+1]) {
                        flag = true;
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
                if(!flag) {
                    System.out.println("当前遍历到第"+i+"数据已经有序");
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡算法总耗时为："+(end-start));
    }


    public static void insertsort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1) {
            return;
        }else {
            //待排序
            for(int i = 1; i < n; i++) {
                //待排序集合的第一个元素
                int value = array[i];
                //已排序集合的最后一个元素
                int j = i-1;
                //找到要插入的位置
                for(; j >= 0; j--) {
                    if(array[j] > value) {
                        array[j+1] = array[j];
                    }
//                    else{
//                        break;    //这三行代码加上，耗时比折半查找少
//                    }
                }
                //找到要插入的位置
                array[j+1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("插入算法总耗时为："+(end-start));
    }

    public static void binarySort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1) {
            return;
        }else {
            for(int i = 1; i < n; i++) {
                int value = array[i];
                int low = 0;
                int high = i - 1;
                int j = i - 1;
                while (low <= high) {
                    int mid = (low + high)/2;
                    if(value > array[mid]) {
                        low = mid + 1;
                    }else {
                        high = mid - 1;
                    }
                }
                for(; j >= high+1; j--) {
                    array[j+1] = array[j];
                }
                array[j+1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("折半插入算法总耗时为："+(end-start));
    }

    public static void selectSort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1) {
            return;
        }else {
            //从排序空间中选出最小值
            for(int i = 0; i < n; i++) {
                int minIndex = i;
                for(int j = i+1; j < n; j++) {
                    if(array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                //此时minIndex对应的元素一定是当前未排序区间的最小值
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("选择排序共耗时"+(end - start)+"毫秒");
    }

    public static void mergeSort(int[] array) {
        int n = array.length;
        if (n <= 1) {
            return;
        }
        mergeInternal(array,0,n-1);
    }
    public static void mergeInternal(int[] array, int low, int high) {
        if(low >= high) {
            return;
        }
        int mid = (low + high)/2;
        //左边小数组
        mergeInternal(array,low,mid);
        //右边小数组
        mergeInternal(array,mid+1,high);
        //合并
        merge(array,low,mid,high);
    }
    private static void merge(int[] array,int p,int q,int r) {
        int i = p;
        int j = q + 1;
        int[] temp = new int[r-p+1];
        int k = 0;
        //此时两个数组中均有元素
        while (i <=q && j<= r) {
            if(array[i] < array[j]) {
                //第一个数组中相同位置元素最小
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }
        //判断当前还有哪个上数组元素没有走完
        int start = i;
        int end = q;
        if(j <= r) {
            start = j;
            end = r;
        }
        //把剩余元素直接放置在temp数组后即可
        while (start <= end) {
            temp[k++] = array[start++];
        }
        //将临时空间中以已经合并好的元素拷贝会原数组
        for(i = 0;i < r-p+1; i++) {
            array[p+i] = temp[i];
        }
    }
}

